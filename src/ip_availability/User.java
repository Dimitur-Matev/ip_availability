package ip_availability;

import 	java.lang.String;

public class User {
	private String _name;
	private Boolean _loggedIn;
	private Integer _numberOfLogins;
	
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
	
	
}
