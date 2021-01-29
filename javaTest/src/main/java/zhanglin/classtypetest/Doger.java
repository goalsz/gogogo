package zhanglin.classtypetest;

import java.beans.Transient;

public class Doger extends Abcd<Doger, Integer> {


    String name;

    @Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
    
    
}
