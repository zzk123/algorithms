package cn.zzk.test.Sort3;

import java.util.Date;

/**
 * 3.4.25
 * @author Administrator
 *
 */
public class Transaction {

	private final String who;
	private final Date when;
	private final double amount;
	private int hashCode;


	public int hashCode(){
		if(hashCode!=0)
			return hashCode;
		int hash = 17;
		hash = 31 * hash + who.hashCode();
		hash = 31 * hash + when.hashCode();
		hash = 31 * hash + ((Double)amount).hashCode();
		hashCode = hash;
		return hash;
	}
	public Transaction(String who, Date when, double amount){
		this.who = who;
		this.when = when;
		this.amount = amount;
	}
}
