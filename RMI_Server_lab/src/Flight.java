
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Flight implements Serializable {

    private int i;
    private String flight_code;
    private String from;
    private String to;
    private Date flight_date;
    private float ticket_cost;
    private int free_seats_num;
    protected ArrayList<Integer> free_seats = new ArrayList<>(50);

    //kathe ptisi krataei afetria proorismo imerominia, timi eisitiriou kai ton arithmo  twn diathesimwn thesewn
    
    public Flight(String flight_code,
            String from, //afethria
            String to, //proorismos
            Date flight_date,
            float ticket_cost,
            int free_seats_num) {
        this.flight_code = flight_code;
        this.flight_date = flight_date;
        this.free_seats_num = free_seats_num;
        this.from = from;
        this.to = to;
        this.ticket_cost = ticket_cost;
        for (i = 0; i <= 5; i++) {
            free_seats.add(null);
            //otan mia thesi einai dithesimi exei timi null
        }
    }

    public boolean code_exist(String flight_code) {
        if (flight_code == this.flight_code) {
            return true;
        } else {
            return false;
        }

    }

    public int number_of_seats() {
        return this.number_of_seats();
    }
    public String get_available_seats()
    {
        String str = null;
        for (i = 0; i <= 5; i++) {
            if(free_seats.get(i).equals(null))
            str += " "+i;
        }
        
        return str;
    }
    
    public String get_from()
    {
        
        return this.from;
        
    }
    public String get_to()
    {
        
        return this.to;
        
    }
     public Date get_flight_date()
    {
        
        return this.flight_date;
        
    }
     public String toString()
     {
         return "Flight with code: "+this.flight_code+"\nFrom: "+this.from+"\nTO: "+this.to+"\nFlight Date: "+this.flight_date+"\n Available seats: "+this.number_of_seats();
     }

}
