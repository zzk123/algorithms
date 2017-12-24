package cn.zzk.test;

public class Book {
	boolean checkedOut = false;
	Book(boolean checkedout){
		checkedout = checkedout;
	}
	void checkIn(){
		checkedOut = false;
	}
	protected void finalize(){
		if(checkedOut)
			System.out.println("error: checked out");
	}
}

