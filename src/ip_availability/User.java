package ip_availability;

import 	java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
	private String name;
	private Boolean loggedIn;
	private Integer numberOfLogins;
	private String from;
	private String to;
	private List<Interval> wasThere = new LinkedList<Interval>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy­MM­dd'T'HH'_'mm'_'ss.SSSZ");
	
	User(String name){
		this.loggedIn = true;
		this.numberOfLogins = 0;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public Integer getNumberOfLogins() {
		return numberOfLogins;
	}
	public void increaseNumberOfLogins() {
		this.numberOfLogins += 1;
	}
	public void setFrom(Date from){
		this.from = dateFormat.format(from);
	}
	public void setTo(Date to){
		this.to = dateFormat.format(to);
		addInterval();
	}
	public void addInterval(){
		wasThere.add(new Interval(this.from,this.to));
	}
	public String getTimeIntervals(){
		
		String result = new String();
		for(Interval it : wasThere){
			result += ":" + it.getFrom();
			result += ":" + it.getTo();
		}
		if(loggedIn){
			result += ":" + this.from;
		}
		return result;
	}
	
}
