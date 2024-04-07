package lk.gugsi.ConcurrentProgramming.Assignment;

public class IncomeTaxCalculationManager implements Runnable {
	
	private Bank bank;
	
	

	public IncomeTaxCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}



	@Override
	public void run() {
		// exception handling must be written
		bank.deductIncomeTax();

	}

}
