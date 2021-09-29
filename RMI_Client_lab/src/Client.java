
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Client extends JFrame implements ActionListener {

    //primitive 
    private JPanel panel1, panel2, panel3;
    private JTabbedPane tabbedPane;
    private JButton emfanisi, kratisi, check_bt, check_for_available_seats;
    private JTextField seat_number_tf, from_tf, flight_code_2_tf, last_name_tf, to_tf, name_tf, username_tf, flight_code_tf, insert_seat_number_tf;
    private JDatePickerImpl datePicker1, datePicker2;
    static String server_reply_1;
    static Operations op;

    //constructor
    public Client() {
        setTitle("GUI");
        setSize(600, 500);
        setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        createPage1();
        createPage2();
        createPage3();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Elegxos diathesimotitas ptisis", panel1);
        tabbedPane.addTab("kratisi eisitiriou", panel2);
        tabbedPane.addTab("Emfanisi stoixeiwn kratisis", panel3);
        topPanel.add(tabbedPane, BorderLayout.CENTER);

        emfanisi.addActionListener((ActionListener) this);
        kratisi.addActionListener((ActionListener) this);
        check_bt.addActionListener((ActionListener) this);
        check_for_available_seats.addActionListener((ActionListener) this);
    }

    public void createPage1() {

        panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel label1 = new JLabel("Elegxos diathesimotitas :");
        label1.setBounds(10, 10, 179, 20);
        panel1.add(label1);

        JLabel from = new JLabel("From: ");
        from.setBounds(10, 35, 100, 20);
        panel1.add(from);

        from_tf = new JTextField();
        from_tf.setBounds(90, 35, 130, 20);
        panel1.add(from_tf);

        JLabel to = new JLabel("Το: ");
        to.setBounds(10, 65, 100, 20);
        panel1.add(to);

        to_tf = new JTextField();
        to_tf.setBounds(90, 65, 130, 20);
        panel1.add(to_tf);

        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        datePicker1 = new JDatePickerImpl(datePanel1);
        datePicker1.setBounds(10, 95, 150, 30);
        panel1.add(datePicker1);

        check_bt = new JButton("Elegxos");
        check_bt.setBounds(10, 200, 150, 20);
        panel1.add(check_bt);

    }

    //kratisi ptisis
    public void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel label1 = new JLabel("Kratisi eisitiriou:");
        label1.setBounds(10, 15, 179, 20);
        panel2.add(label1);

        JLabel flight_code = new JLabel("Flight code: ");
        flight_code.setBounds(10, 45, 100, 20);
        panel2.add(flight_code);

        flight_code_tf = new JTextField();
        flight_code_tf.setBounds(90, 45, 130, 20);
        panel2.add(flight_code_tf);

        check_for_available_seats = new JButton("Check for available seats ");
        check_for_available_seats.setBounds(10, 85, 250, 20);
        panel2.add(check_for_available_seats);

        //name
        JLabel name_lb = new JLabel("onoma: ");
        name_lb.setBounds(10, 120, 250, 20);
        panel2.add(name_lb);

        name_tf = new JTextField(50);
        name_tf.setBounds(150, 120, 130, 20);
        panel2.add(name_tf);

        //last name
        JLabel last_name_lb = new JLabel("epitheto: ");
        last_name_lb.setBounds(10, 150, 250, 20);
        panel2.add(last_name_lb);

        last_name_tf = new JTextField(50);
        last_name_tf.setBounds(150, 150, 130, 20);
        panel2.add(last_name_tf);

        //seat number
        JLabel seat_number_lb = new JLabel("Seat number: ");
        seat_number_lb.setBounds(10, 180, 250, 20);
        panel2.add(seat_number_lb);

        seat_number_tf = new JTextField();
        seat_number_tf.setBounds(150, 180, 50, 20);
        panel2.add(seat_number_tf);

        kratisi = new JButton("Kratisi");
        kratisi.setBounds(10, 210, 150, 20);
        panel2.add(kratisi);

    }

    public void createPage3() {
        panel3 = new JPanel();
        panel3.setLayout(null);

        JLabel label1 = new JLabel("Emfanisi twn stoixeiwn tis kratisis mou:");
        label1.setBounds(10, 15, 500, 20);
        panel3.add(label1);

        JLabel flight_code_2 = new JLabel("Flight code: ");
        flight_code_2.setBounds(10, 55, 100, 20);
        panel3.add(flight_code_2);

        flight_code_2_tf = new JTextField();
        flight_code_2_tf.setBounds(90, 55, 130, 20);
        panel3.add(flight_code_2_tf);

        //name
        JLabel name_lb = new JLabel("onoma: ");
        name_lb.setBounds(10, 85, 250, 20);
        panel3.add(name_lb);

        name_tf = new JTextField(50);
        name_tf.setBounds(150, 85, 130, 20);
        panel3.add(name_tf);

        //last name
        JLabel last_name_lb = new JLabel("epitheto: ");
        last_name_lb.setBounds(10, 120, 250, 20);
        panel3.add(last_name_lb);

        last_name_tf = new JTextField(50);
        last_name_tf.setBounds(150, 120, 130, 20);
        panel3.add(last_name_tf);

        emfanisi = new JButton("emfanisi");
        emfanisi.setBounds(10, 190, 150, 20);
        panel3.add(emfanisi);

    }

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {

        RMISecurityManager security = new RMISecurityManager();
        System.setSecurityManager(security);
        op = (Operations) Naming.lookup("//localhost//flight_directory");
        Client c = new Client();
        c.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        //evresi diathesimwn ptisewn
        if (ae.getSource() == check_bt) {
            try {
                server_reply_1 = op.availability_check(from_tf.getText(), to_tf.getText(), (Date) datePicker1.getModel().getValue());
                System.out.println((Date) datePicker1.getModel().getValue());
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(panel1, server_reply_1, "Available flights", JOptionPane.INFORMATION_MESSAGE);

        }
        if (ae.getSource() == check_for_available_seats) {
            String available_seats = null;
            try {
                available_seats = op.available_seats_of_the_flight(flight_code_tf.getText());
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            //emfanizw ta apotelesmata se ena joptionpane
            JOptionPane.showMessageDialog(panel2, available_seats, "Available seats", JOptionPane.INFORMATION_MESSAGE);
        }
        if (ae.getSource() == kratisi) {
            String reply2 = null;
            try {
                reply2 = op.kratisi(flight_code_tf.getText(), Integer.parseInt(seat_number_tf.getText()), name_tf.getText(), last_name_tf.getText());
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            //an epistrepsei o server ok
            if (reply2.equals("OK")) {
                //emfanizw ta apotelesmata se ena joptionpane
                JOptionPane.showMessageDialog(panel2, "Info", "Your booking has recorded", JOptionPane.INFORMATION_MESSAGE);

            } else {
                //emfanizw ta apotelesmata se ena joptionpane
                JOptionPane.showMessageDialog(panel2, "Error while handling your booking", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (ae.getSource() == emfanisi) {
            String reply3 = null;

            try {
                reply3 = op.emfanisi_stoixeiwn_kratisis(flight_code_2_tf.getText(), name_tf.getText(), last_name_tf.getText());
            } catch (RemoteException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(panel3, "Stoixeia kratisis", reply3, JOptionPane.INFORMATION_MESSAGE);

        }

    }

}
