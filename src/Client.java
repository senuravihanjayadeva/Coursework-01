import java.util.List;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) {
		Bank bank = new Bank();

		Customer customer = new Customer("C001", "Guhanathan", "Poravi", "077", "Colombo 06", "XXX");
		List<Customer> customers = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();
		customers.add(customer);
		BankAccount account1 = bank.createAccount("201864747", customers, AccountType.REGULAR, false, 0, 0);

		// multiple account and multiple customer with different account types must be created
		Customer customer2 = new Customer("C002", "Saman", "Perera", "0771234567", "123 Main St", "200112345V");
		Customer customer3 = new Customer("C003", "Nimal", "Fernando", "0712345678", "456 Park Ave", "870987654M");
		Customer customer4 = new Customer("C004", "Kamala", "Silva", "0783456789", "789 Beach Rd", "935678901P");
		Customer customer5 = new Customer("C005", "Anula", "Rajapaksa", "0764567890", "321 Lake View", "720456789R");
		Customer customer6 = new Customer("C006", "Sunil", "Bandara", "0725678901", "654 Hillside Dr", "610234567S");
		Customer customer7 = new Customer("C007", "Malini", "Jayawardena", "0796789012", "135 River Rd", "840789012T");
		Customer customer8 = new Customer("C008", "Ranjan", "Samaraweera", "0757890123", "246 Forest Ln", "740123456U");
		Customer customer9 = new Customer("C009", "Ishara", "Gamage", "0738901234", "579 Mountain View", "810567890V");
		Customer customer10 = new Customer("C010", "Chathura", "Rathnayake", "0709012345", "864 Valley Rd", "920345678W");
		Customer customer11 = new Customer("C011", "Madhavi", "De Silva", "0770123456", "753 Garden Ave", "690123456X");

		List<Customer> customers2 = new ArrayList<>();
		customers2.add(customer2);
		BankAccount account2 = bank.createAccount("202012345", customers2, AccountType.VIP, true, 5000, 2.5);

		List<Customer> customers3 = new ArrayList<>();
		customers3.add(customer3);
		BankAccount account3 = bank.createAccount("202134567", customers3, AccountType.REGULAR, false, 0, 3.0);

		List<Customer> customers4 = new ArrayList<>();
		customers4.add(customer4);
		BankAccount account4 = bank.createAccount("202245678", customers4, AccountType.REGULAR, true, 1500, 3.0);

		List<Customer> customers5 = new ArrayList<>();
		customers5.add(customer5);
		BankAccount account5 = bank.createAccount("202356789", customers5, AccountType.REGULAR, true, 1000, 2.0);

		List<Customer> customers6 = new ArrayList<>();
		customers6.add(customer6);
		BankAccount account6 = bank.createAccount("202467890", customers6, AccountType.REGULAR, false, 0, 2.0);

		List<Customer> customers7 = new ArrayList<>();
		customers7.add(customer7);
		customers7.add(customer8);
		customers7.add(customer9);
		customers7.add(customer10);
		BankAccount account7 = bank.createAccount("202578901", customers7, AccountType.VIP, true, 2000, 3.5);

		ThreadGroup bankManager = new ThreadGroup("BankManagers");
		bankManager.setMaxPriority(8);
		Thread annualChargesCalculationManagerThread = new Thread(bankManager, new AnnualChargesCalculationManager(bank), "Annual Charges Calculation Manager");
		Thread incomeTaxCalculationManagerThread = new Thread(bankManager, new IncomeTaxCalculationManager(bank), "Income Tax Calculation Manager");
		Thread interestCalculationManagerThread = new Thread(bankManager, new InterestCalculationManager(bank), "Interest Calculation Manager");
		Thread overdraftCalculationManagerThread = new Thread(bankManager, new OverdraftCalculationManager(bank), "Overdraft Calculation Manager");
		
		ThreadGroup vipCustomer = new ThreadGroup("VIP Customer");
		ThreadGroup regularCustomer = new ThreadGroup("Regular Customer");
		vipCustomer.setMaxPriority(10);
		regularCustomer.setMaxPriority(5);
		
		Thread depositorThread1 = new Thread(regularCustomer, new Depositor(account1, 10000), "Guhanathan P");
		Thread depositorThread2 = new Thread(regularCustomer, new Depositor(account1, 10000), "Guhanathan P");
		Thread depositorThread3 = new Thread(regularCustomer, new Depositor(account1, 10000), "Guhanathan P");
		Thread depositorThread4 = new Thread(vipCustomer, new Depositor(account2,500000), "Senura Jayadeva");
		Thread depositorThread5 = new Thread(vipCustomer, new Depositor(account2,90000), "Supun Perera");
		Thread depositorThread6 = new Thread(regularCustomer, new Depositor(account3,75800), "Eshan naveen");
		Thread depositorThread7 = new Thread(regularCustomer, new Depositor(account3,12000), "Rusiru Abisheak");
		Thread depositorThread8 = new Thread(regularCustomer, new Depositor(account4,67000), "Kusal Perera");


		Thread receiverThread1 = new Thread(regularCustomer, new Receiver(customer, account1, 5000), "Guhanathan P");
		Thread receiverThread2 = new Thread(regularCustomer, new Receiver(customer, account1, 15000), "Guhanathan P");
		Thread receiverThread3 = new Thread(regularCustomer, new Receiver(customer, account1, 25000), "Guhanathan P");
		Thread receiverThread4 = new Thread(vipCustomer, new Receiver(customer3,account2,20000), "Ayodya Banuka");
		Thread receiverThread5 = new Thread(regularCustomer, new Receiver(customer4,account3,3455), "Lahiru Jayasinghe");
		Thread receiverThread6 = new Thread(regularCustomer, new Receiver(customer8,account4,45673), "Kalana Gayanga");
		Thread receiverThread7 = new Thread(regularCustomer, new Receiver(customer9,account5,23400), "Lasal Hettiarchchi");

		threads.add(depositorThread1);
		threads.add(depositorThread2);
		threads.add(depositorThread3);

		threads.add(depositorThread4);
		threads.add(depositorThread5);
		threads.add(depositorThread6);
		threads.add(depositorThread7);
		threads.add(depositorThread8);

		threads.add(interestCalculationManagerThread);
		threads.add(incomeTaxCalculationManagerThread);
		threads.add(annualChargesCalculationManagerThread);
		threads.add(overdraftCalculationManagerThread);

		threads.add(receiverThread1);
		threads.add(receiverThread2);
		threads.add(receiverThread3);
		threads.add(receiverThread4);
		threads.add(receiverThread5);
		threads.add(receiverThread6);
		threads.add(receiverThread7);


		for (Thread thread: threads) {
			thread.start();
		}

	}

}
