package lk.gugsi.ConcurrentProgramming.Assignment;

public class Receiver implements Runnable {
	
	private Customer customer;
	private BankAccount account;
	private double amount;
	
	

	public Receiver(Customer customer, BankAccount account, double amount) {
		super();
		this.customer = customer;
		this.account = account;
		this.amount = amount;
	}



	@Override
	public void run() {
		// Write the logic to verify if customer is part of the account
		// if it is true
		try {
			account.withdraw(amount);
		}catch(Exception e) {
			System.out.println("Error in withdrawal "+e.getMessage());
		}
		

	}

}
