
import java.io.*;
import java.net.MalformedURLException;

import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMI_Server extends UnicastRemoteObject implements Operations {

    static boolean available = true;
    private Flight f1, f2, f3, f4, f5;
    static ObjectOutputStream out_file;
    static ObjectInputStream in_file;

    public RMI_Server() throws RemoteException, ParseException, IOException {
        super();
        ArrayList<Flight> flight_directory = new ArrayList<Flight>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        //ftiaxnw merikes ptiseis gia na mporesw na ergastw eukolotera
        f1 = new Flight("1", "ATH", "SKG", sdf.parse("01-01-2016 00:00:00"), 100, 50);
        f2 = new Flight("2", "ATH", "SKG", sdf.parse("02-01-2016 00:00:00"), 100, 50);
        f3 = new Flight("3", "ATH", "SKG", sdf.parse("03-01-2016 00:00:00"), 100, 50);
        f4 = new Flight("4", "ATH", "SKG", sdf.parse("04-01-2016 00:00:00"), 100, 50);
        f5 = new Flight("5", "ATH", "SKG", sdf.parse("05-01-2016 00:00:00"), 100, 50);
        out_file = new ObjectOutputStream(new FileOutputStream("Flights.txt"));
        flight_directory.add(f1);
        flight_directory.add(f2);
        flight_directory.add(f3);
        flight_directory.add(f4);
        flight_directory.add(f5);
        //grafw mia lista ptisewn se ena arxeio
        out_file.writeObject(flight_directory);
        out_file.close();

    }

    @Override
    @SuppressWarnings("empty-statement")
    //auti i methodos tah xrisimopoiithei gia tin leitourgia tis kratisis
    public synchronized String available_seats_of_the_flight(String flight_code) throws RemoteException {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        available = false;
        ArrayList<Flight> flights_arraylist;
        String str = null;
        //diavasma sugkekrimenis ptisis apo to arxeio
        FileInputStream fin;
        int num = -1;
        int i, j;
        try {
            fin = new FileInputStream("Flights.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            Flight f;
            flights_arraylist = (ArrayList<Flight>) ois.readObject();
            for (i = 0; i <= flights_arraylist.size(); i++) {
                if (((Flight) flights_arraylist.get(i)).code_exist(flight_code)) {
                    str = flights_arraylist.get(i).get_available_seats();
                    ois.close();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        available = true;
        notifyAll();

        return str;

    }

    @Override
    //thewrw oti mporei na kanei kratisi gia 1 mono thesi
    public synchronized String kratisi(String flight_code, int seat_number, String name, String lastname) throws RemoteException {
        String str = null;
        ArrayList<Flight> flights_arraylist;
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        available = false;
        //diavasma sugkekrimenis ptisis apo to arxeio
        FileInputStream fin;
        int i;
        int num = -1;
        try {
            fin = new FileInputStream("Flights.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            flights_arraylist = (ArrayList<Flight>) ois.readObject();
            Flight f;
            for (i = 0; i <= flights_arraylist.size(); i++) {
                if (((Flight) flights_arraylist.get(i)).code_exist(flight_code)) {
                    kratisi k = new kratisi(flight_code, seat_number, name, lastname);
                    flights_arraylist.get(i).free_seats.set(seat_number, 1);

                    //ksanagrafw ti lista twn ptisewn sto arxeio mou me tis allages
                    FileOutputStream fos = new FileOutputStream("Flights.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(flights_arraylist);
                    oos.close();
                    ois.close();

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        available = true;
        notifyAll();
        return str = "OK";

    }

    @Override
    //elegxos diathesimotitas ptisis
    //dineis proorismo, topothesia kai imerominia kai sou epistrefei se String typo tis ptiseis
    public synchronized String availability_check(String from, String to, Date flight_date) throws RemoteException {
        int i;
        ArrayList<Flight> flights_arraylist;
        FileInputStream fin;
        String str = null;
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        available = false;

        try {
            fin = new FileInputStream("Flights.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            flights_arraylist = (ArrayList<Flight>) ois.readObject();
            for (i = 0; i <= flights_arraylist.size(); i++) {
                if (flights_arraylist.get(i).get_from().equals(from)
                        && flights_arraylist.get(i).get_to().equals(to)
                        && flights_arraylist.get(i).get_flight_date().equals(flight_date)) {
                    str += "\n\n" + flights_arraylist.get(i);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        available = true;
        notifyAll();
        return str;
    }

    @Override
    public String emfanisi_stoixeiwn_kratisis(String name, String last_name, String flight_code) throws RemoteException {
        int i;
        for (i = 0; i <= kratisi.arr.size(); i++) {
            if (kratisi.arr.get(i).get_flight_code().equals(flight_code)
                    && kratisi.arr.get(i).get_name().equals(name)
                    && kratisi.arr.get(i).get_last_name().equals(last_name)) {
                return kratisi.arr.get(i).toString();
            }
        }
        return null;
    }

    public static void main(String[] args) throws ParseException, RemoteException, MalformedURLException, IOException {
        RMI_Server server = new RMI_Server();
        Naming.rebind("//localhost//flight_directory", server);
        System.out.println("Server up and running....");
    }

}
