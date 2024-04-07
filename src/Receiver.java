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
		boolean isCustomerPart = account.getCustomers().contains(customer);
		if (isCustomerPart) {
			try {
				account.withdraw(amount);
			} catch (Exception e) {
				System.out.println("Error in withdrawal: " + e.getMessage());
			}
		} else {
			System.out.println(customer.getCustomerID() + " is not a part of  account " + account.getAccountNumber());
		}

	}

}
