package ip_availability;

//import java.util.Date;

public class Interval {
	private String from;
	private String to;
	
	public Interval(String from2, String to2){
		this.from = from2;
		this.to = to2;
	}
	
	public String getFrom(){
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
}
