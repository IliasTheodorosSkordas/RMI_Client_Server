
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface Operations  extends Remote {

    public String kratisi(String flight_code,int seat_number,String name,String lastname) throws RemoteException;
    
    public String available_seats_of_the_flight(String flight_code) throws RemoteException; 

    public String availability_check(String from, String to, Date flight_date) throws RemoteException;//done

    public String emfanisi_stoixeiwn_kratisis(String name, String last_name, String flight_code) throws RemoteException;

}
