package ip_availability;

import 	java.lang.String;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
	private String _name;
	private Boolean _loggedIn;
	private Integer _numberOfLogins;
	private Date from;
	private Date to;
	private List<Interval> wasThere = new LinkedList<Interval>();
	
	User(String name){
		this._loggedIn = true;
		this._numberOfLogins = 0;
		this._name = name;
	}
	
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	public Boolean getLoggedIn() {
		return _loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this._loggedIn = loggedIn;
	}
	public Integer getNumberOfLogins() {
		return _numberOfLogins;
	}
	public void increaseNumberOfLogins() {
		this._numberOfLogins += 1;
	}
	public void setFrom(Date from){
		this.from = from;
	}
	public void setTo(Date to){
		this.to = to;
	}
	public void addInterval(){
		wasThere.add(new Interval(this.from,this.to));
	}
	
}
