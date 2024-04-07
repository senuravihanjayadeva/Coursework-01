public class IncomeTaxCalculationManager implements Runnable {
	
	private Bank bank;

	public IncomeTaxCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {
		try {
			bank.deductIncomeTax();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
