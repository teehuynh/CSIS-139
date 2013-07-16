import java.io.Serializable;


public class Account implements Serializable{
	protected String name;
	protected double balance;
	
	public Account(String acctName, double initBalance) {
		balance = initBalance;
		name = acctName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
