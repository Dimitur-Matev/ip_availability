package ip_availability;

import java.util.Date;

public class Interval {
	private Date from;
	private Date to;
	
	public Interval(Date from, Date to){
		this.from = from;
		this.to = to;
	}
	
	public Date getFrom(){
		return this.from;
	}
	
	public Date getTo() {
		return this.to;
	}
	
}
