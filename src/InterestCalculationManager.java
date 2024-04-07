public class InterestCalculationManager implements Runnable {
	
	private Bank bank;

	public InterestCalculationManager(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {
		try {
			bank.addInterest();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
