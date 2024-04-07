public class OverdraftCalculationManager implements Runnable{
    private Bank bank;

    public OverdraftCalculationManager(Bank bank) {
        super();
        this.bank = bank;
    }
    @Override
    public void run() {
        try {
            bank.overdraftCharges();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
