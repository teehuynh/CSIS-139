import java.io.Serializable;
import java.util.ArrayList;

public class CheckingAccount extends Account {

	private ArrayList<Transaction> transList = new ArrayList<Transaction>();
	private int transCount = 0;
	private double totalServiceCharge;
	
	public CheckingAccount(String acctName, double initialBalance) {
		super(acctName, initialBalance);
		totalServiceCharge = 0;
	}
	public void setBalance(double transAmt, int tCode) {
		if(tCode == 1)
			balance -= transAmt;
		else if(tCode == 2)
			balance += transAmt;
	}
	public double getServiceCharge() {
		return totalServiceCharge;
	}
	public void setServiceCharge(double currentServiceCharge) {
		totalServiceCharge += currentServiceCharge;
	}
	public void addTrans(Transaction newTrans) {
		transList.add(newTrans);
		transCount++;
	}
	public int getTransCount() {
		return transCount;
	}
	public Transaction getTrans(int i) {
		return transList.get(i);
	}
}
