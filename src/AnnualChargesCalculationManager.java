public class AnnualChargesCalculationManager implements Runnable {
	private Bank bank;
	public AnnualChargesCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}
	@Override
	public void run() {
		try {
			bank.annualCharges();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
