
public class Check extends Transaction {
	
	private int checkNumber;
	
	public Check(int tId, double tAmt, int tCount, int checkNumber) {
		super(tCount, tId, tAmt);
		this.checkNumber = checkNumber;
	}
	
	public int getCheckNumber() {
		return checkNumber;
	}
	
	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}
}
