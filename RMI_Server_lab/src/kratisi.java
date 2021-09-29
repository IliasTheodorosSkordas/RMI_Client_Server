
import java.io.Serializable;
import java.util.ArrayList;


public class kratisi  implements Serializable{
    private String name;
    private String lastname;
    private String flight_code;
    private int seat_number;
    public static ArrayList<kratisi> arr;
    
    public kratisi(String flight_code, int seat_number, String name, String lastname)
    {
        this.flight_code=flight_code;
        this.name=name;
        this.seat_number=seat_number;
        this.lastname=lastname;
        add_to_arraaylist();
        
    }
    private void add_to_arraaylist()
    {
        arr.add(this);
    }
    public String toString()
    {
        return "Kratisi gia ton\tin : "+this.name+" "+this.lastname+"\nkodikos ptisis: "+this.flight_code+"\n thesi: "+this.seat_number;
        
    }
    public String get_name()
    {
        return this.name;
    }
     public String get_last_name()
    {
        return this.lastname;
    }
     public String get_flight_code()
    {
        return this.flight_code;
    }
    
}
