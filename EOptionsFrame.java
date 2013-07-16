import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EOptionsFrame extends JFrameL {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	private JMenu fileMenu, accountMenu, transMenu;
	private JMenuItem readFile, writeFile, addTrans, listTrans, listChecks, listDep, findAcc, addAcc;
	private JMenuBar bar;
	
	public EOptionsFrame (String title) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuListener ml = new MenuListener();
		
		fileMenu = new JMenu ("File");
		
		readFile = new JMenuItem("Read from file");
		readFile.addActionListener(ml);
		fileMenu.add(readFile);
		
		writeFile = new JMenuItem("Write to file");
		writeFile.addActionListener(ml);
		fileMenu.add(writeFile);
		
		accountMenu = new JMenu("Accounts");
		
		addAcc = new JMenuItem("Add new account");
		addAcc.addActionListener(ml);
		accountMenu.add(addAcc);
		
		listTrans = new JMenuItem("List accounts transaction");
		listTrans.addActionListener(ml);
		accountMenu.add(listTrans);
		
		listChecks = new JMenuItem("List all Checks");
		listChecks.addActionListener(ml);
		accountMenu.add(listChecks);
		
		listDep = new JMenuItem("List all Deposits");
		listDep.addActionListener(ml);
		accountMenu.add(listDep);
		
		findAcc = new JMenuItem("Find an account");
		findAcc.addActionListener(ml);
		accountMenu.add(findAcc);
		
		transMenu = new JMenu("Transaction");
		
		addTrans = new JMenuItem("Add a transaction");
		addTrans.addActionListener(ml);
		transMenu.add(addTrans);
		
		bar = new JMenuBar();
		bar.add(fileMenu);
		bar.add(accountMenu);
		bar.add(transMenu);
		setJMenuBar(bar);
	}
	private class MenuListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			String source = event.getActionCommand();
			
			if(source.equals("Read from file"))
				main.readFile();
			else if(source.equals("Write to file"))
				main.writeFile();
			else if(source.equals("List accounts transaction"))
				main.listTran();
			else if(source.equals("List all Checks"))
				main.listChecks();
			else if(source.equals("List all Deposits"))
				main.listDeposits();
			else if(source.equals("Add a transaction"))
				main.addTran();
			else if(source.equals("Add new account"))
				main.addAccount();
			else if(source.equals("Find an account"))
				main.findAccount();
		}
		
	}
}
