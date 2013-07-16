//Tuan Huynh
//CSIS 139
//Assignment 4

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class main {
	
	public static Vector<Account> acc = new Vector<Account>();
	public static CheckingAccount ca;
	public static boolean firstTime = true;
	public static DecimalFormat fmt;
	public static Check chk;
	public static String filename = "C:\\student\\elements.dat";
	public static EOptionsFrame frame;
	public static JTextArea ta;
	
	public static void main (String[] args) {
		
		fmt = new DecimalFormat("$0.00");
		
		ta = new JTextArea(10,52);
		ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		frame = new EOptionsFrame("Bank Account Options");
		
		frame.getContentPane().add(ta);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public static void addAccount() {
		
		String name, initialBalStr;
		double initialBal;
		
		name = JOptionPane.showInputDialog("Enter the account name:");
		
		initialBalStr = JOptionPane.showInputDialog("Enter the initial balance:");
		initialBal = Double.parseDouble(initialBalStr);

		ca = new CheckingAccount(name, initialBal);
		acc.addElement(ca);
	}
	public static void findAccount() {
		String name;
		
		name = JOptionPane.showInputDialog("Enter the account name:");
		
		for (int i = 0; i != acc.size(); i++) {
			Account accCheck = acc.elementAt(i);
			
			if(name.equals(accCheck.getName())) {
				ta.setText("Account found for " + name + "!");
				ca = (CheckingAccount)accCheck;
			}
		}
	}
	public static void addTran() {
		
		String message;
		int transCode, checkNum;
		double amount = 0;
		frame.setVisible(false);
	
	do 
	{
		transCode = getTransCode();
		
		switch(transCode) {
		
		case 1:
			checkNum = getCheckNum();
			amount = getCheckAmt();
			chk = new Check (transCode, amount, ca.getTransCount(), checkNum);
			ca.addTrans(chk);
			processCheck(amount);
			break;
		case 2:
			amount = getDepositAmt();
			ca.addTrans(new Transaction (ca.getTransCount(), transCode, amount));
			processDeposit(amount);
			break;
		case 0:
			message = ca.getName() + "'s account"
					  + "\nTransaction: End"
					  + "\nCurrent Balance: " + fmt.format(ca.getBalance())
					  + "\nTotal service charge: " + fmt.format(ca.getServiceCharge())
					  + "\nFinal Balance: " + fmt.format(ca.getBalance() - ca.getServiceCharge());
			JOptionPane.showMessageDialog(null, message);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Invalid code, please try again");
		}
	}while(transCode != 0);
		
		frame.setVisible(true);
	}
	public static int getTransCode() {
		
		String codeStr;
		codeStr = JOptionPane.showInputDialog("0->End\n1->Check\n2->Deposit\nEnter trans code:");
		return Integer.parseInt(codeStr);
	}
	public static double getCheckAmt() {
		
		String transAmtStr;
		
		transAmtStr = JOptionPane.showInputDialog("Please enter the check amount");
		return Double.parseDouble(transAmtStr);
	}
	public static double getDepositAmt() {
		
		String transAmtStr;
		
		transAmtStr = JOptionPane.showInputDialog("Please enter the deposit amount");
		return Double.parseDouble(transAmtStr);
	}
	public static void processCheck(double transAmt) {
		
		ca.setBalance(transAmt, 1);
		ca.setServiceCharge(.15);
		ca.addTrans(new Transaction(ca.getTransCount(), 3, .15));
		
		String message;
		
			message = ca.getName() + "'s account\n" 
					  + "Transaction: Check #" +  chk.getCheckNumber() + " in amount of " + fmt.format(transAmt) 
					  + "\nCurrent Balance: " + fmt.format(ca.getBalance())
				      + "\nService Charge: Check --- charge $0.15";
		
		if(ca.getBalance() < 500 && firstTime) {
			ca.setServiceCharge(5);
			ca.addTrans(new Transaction(ca.getTransCount(), 3, 5));
			
			firstTime = false;
			
			message += "\nService Charge: Below $500 --- charge $5.00";
		}
		
		if (ca.getBalance() < 50) {
			message += "\nWarning: Balance below $50";
		}
		
		if (ca.getBalance() < 0) {
			ca.setServiceCharge(10);
			ca.addTrans(new Transaction(ca.getTransCount(), 3, 10));
			
			message += "\nService Charge: Below $0 --- charge $10.00";
		}
			message += "\nTotal Service Charge: " + fmt.format(ca.getServiceCharge());
			
			JOptionPane.showMessageDialog(null, message);	
	}
	public static void processDeposit(double transAmt) {
	
		ca.setBalance(transAmt, 2);
		ca.setServiceCharge(.10);
		ca.addTrans(new Transaction(ca.getTransCount(), 3, .10));
		
		String message;
	
		message = ca.getName() + "'s account"
				  + "\nTransaction: Check in amount of " + fmt.format(transAmt) 
				  + "\nCurrent Balance: " + fmt.format(ca.getBalance())
				  + "\nService Charge: Check --- charge $0.10";
		
		if(ca.getBalance() < 500 && firstTime) {
			ca.setServiceCharge(5);
			ca.addTrans(new Transaction(ca.getTransCount(), 3, 5));
			firstTime = false;
			
			message += "\nService Charge: Below $500 --- charge $5.00";
		}
	
		if (ca.getBalance() < 50) {
			message += "\nWarning: Balance below $50";
		}
	
		if (ca.getBalance() < 0) {
			ca.setServiceCharge(10);
			ca.addTrans(new Transaction(ca.getTransCount(), 3, 10));
			message += "\nService Charge: Below $0 --- charge $10.00";
		}
			message += "\nTotal Service Charge: " + fmt.format(ca.getServiceCharge());
		
			JOptionPane.showMessageDialog(null, message);
	}
	public static void listChecks() {
		
		String message;
		frame.setVisible(false);
	
		message = "Listing all Checks for " + ca.getName() + "\n";
		message += "ID" + String.format("%25s%25s\n", "Chk #", "Amount");
		message += "----------------------------------------------------\n";
		
		for(int i = 0; i < ca.getTransCount(); i++) {
			if(ca.getTrans(i).getTransId() == 1){
				String amount = fmt.format(ca.getTrans(i).getTransAmount());
				Check c = (Check)(ca.getTrans(i));
				message += String.format("%-15s%11s%26s\n", ca.getTrans(i).getTransNumber(),c.getCheckNumber(), amount);	
			}
		}
		ta.setText(message);
		frame.setVisible(true);
	}
	public static void listDeposits() {
		
		String message;
		frame.setVisible(false);
		
		message = "Listing all Deposits for " + ca.getName() + "\n";
		message += "ID" + String.format("%25s%25s\n", "Type", "Amount");
		message += "----------------------------------------------------\n";
		
		for(int i = 0; i < ca.getTransCount(); i++) {
			if(ca.getTrans(i).getTransId() == 2) {
				String amount = fmt.format(ca.getTrans(i).getTransAmount());
				message += String.format("%-15s%11s%26s\n", ca.getTrans(i).getTransNumber(), "Deposit", amount);	
			}
		}
		ta.setText(message);
		frame.setVisible(true);
	}
	public static void listTran(){
		
		String message, type = "";
		
		frame.setVisible(false);
		
		message = "Account: " + ca.getName() + "\n";
		message += "Balance: " + fmt.format(ca.getBalance()) + "\n";
		message += "Service Charge: " + fmt.format(ca.getServiceCharge()) + "\n\n";
		message += "List of all transactions:\n\n";
		message += String.format("TransNumber" + String.format("%16s%25s\n", "Type", "Amount"));
		
		for(int i = 0; i < ca.getTransCount(); i++) {

			switch(ca.getTrans(i).getTransId()) {
			case 1:
				type = "Check";
				break;
			case 2:
				type = "Deposit";
				break;
			case 3:
				type = "Service";
				break;
			}
			String amount = fmt.format(ca.getTrans(i).getTransAmount());
			message += String.format("%-20s%-15s%17s\n", ca.getTrans(i).getTransNumber(), type , amount); 
		}
	
		ta.setText(message);
		
		frame.setVisible(true);
}
	public static int getCheckNum() {
		String checkNumStr;
		
		checkNumStr = JOptionPane.showInputDialog("Enter the check number:");
		return Integer.parseInt(checkNumStr);
	}
	public static void chooseFile(int ioOption) 
	   {  
	      int status, confirm;       
	                
	      String  message = "Would you like to use the current default file: \n" +
	                          filename;
	      confirm = JOptionPane.showConfirmDialog (null, message);
	      if (confirm == JOptionPane.YES_OPTION)
	          return;
	      JFileChooser chooser = new JFileChooser();
	      if (ioOption == 1)
	          status = chooser.showOpenDialog (null);
	      else
	          status = chooser.showSaveDialog (null);
	      if (status == JFileChooser.APPROVE_OPTION)
	      {
	          File file = chooser.getSelectedFile();
	          filename = file.getPath();
	      }
	   }
	public static void readFile() {
		chooseFile(1);
		try
		{
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fis);
			
			acc = (Vector<Account>)in.readObject();
			
			in.close();

		}catch(ClassNotFoundException e) { 
              System.out.println(e);
        }catch (IOException e) { 
              System.out.println(e);
        }
	}
	public static void writeFile() {
		chooseFile(2);
		try
		{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fos);
		
			out.writeObject(acc);
			out.close();
			
		}catch(IOException e) { 
				System.out.println(e);
        }
	}
}
	
