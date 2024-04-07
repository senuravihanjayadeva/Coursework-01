package lk.gugsi.ConcurrentProgramming.Assignment;

import java.util.List;
import java.util.ArrayList;

// simulation

public class Client {

	public static void main(String[] args) {
		Bank bank = new Bank();
		Customer customer = new Customer("C001", "Guhanathan", "Poravi", "077", "Colombo 06", "XXX");
		List<Customer> customers = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();
		customers.add(customer);
		// multiple account and multiple customer with different account types must be created
		BankAccount account1 = bank.createAccount("201864747", customers, AccountType.REGULAR, false, 0, 0);
		
		ThreadGroup bankManager = new ThreadGroup("BankManagers");
		bankManager.setMaxPriority(8);
		Thread annualChargesCalculationManagerThread = new Thread(bankManager, new AnnualChargesCalculationManager(bank), "Annual Charges Calculation Manager");
		Thread incomeTaxCalculationManagerThread = new Thread(bankManager, new IncomeTaxCalculationManager(bank), "Income Tax Calculation Manager");
		Thread interestCalculationManagerThread = new Thread(bankManager, new InterestCalculationManager(bank), "Interest Calculation Manager");
		
		ThreadGroup customerVIP = new ThreadGroup("VIP Customer");
		ThreadGroup regularCustomer = new ThreadGroup("Regular Customer");
		customerVIP.setMaxPriority(10);
		regularCustomer.setMaxPriority(5);
		
		Thread depositorThread1 = new Thread(regularCustomer, new Depositor(account1, 10000), "Guhanathan P");
		Thread depositorThread2 = new Thread(regularCustomer, new Depositor(account1, 10000), "Guhanathan P");
		Thread depositorThread3 = new Thread(regularCustomer, new Depositor(account1, 10000), "Guhanathan P");
		
		Thread receiverThread1 = new Thread(regularCustomer, new Receiver(customer, account1, 5000), "Guhanathan P");
		Thread receiverThread2 = new Thread(regularCustomer, new Receiver(customer, account1, 15000), "Guhanathan P");
		Thread receiverThread3 = new Thread(regularCustomer, new Receiver(customer, account1, 25000), "Guhanathan P");
		
		threads.add(depositorThread1);
		threads.add(depositorThread2);
		threads.add(depositorThread3);
		threads.add(interestCalculationManagerThread);
		threads.add(incomeTaxCalculationManagerThread);
		threads.add(annualChargesCalculationManagerThread);
		threads.add(receiverThread1);
		threads.add(receiverThread2);
		threads.add(receiverThread3);
		
		for (Thread thread: threads) {
			
			thread.start();
		}

	}

}
