import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {

    private BankAccount account;
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("C001", "Guhanathan", "Poravi", "077", "Colombo 06", "XXX");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        account = new BankAccount("201864747", customers, 1000, AccountType.REGULAR, false, 0, 0);
    }

    @Test
    public void testDeposit() {
        account.deposit(500);
        assertEquals(1500, account.getBalance(), 0);
    }

    @Test
    public void testWithdraw() {
        account.withdraw(500);
        assertEquals(500, account.getBalance(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawWithInsufficientFunds() {
        account.withdraw(2000); // Should throw IllegalArgumentException
    }

    @Test
    public void testAddInterest() {
        account.addInterest(10);
        assertEquals(1010, account.getBalance(), 0);
    }

    @Test
    public void testDeductIncomeTax() {
        account.deductIncomeTax(100);
        assertEquals(900, account.getBalance(), 0);
    }

    @Test
    public void testDeductAnnualCharges() {
        account.deductAnnualCharges(50);
        assertEquals(950, account.getBalance(), 0);
    }

    @Test
    public void testDeductOverdraftCharge() {
        account.deductOverdraftCharge(20);
        assertEquals(980, account.getBalance(), 0);
    }

    @Test
    public void testAddCustomer() {
        Customer newCustomer = new Customer("C002", "John", "Doe", "123", "New York", "YYY");
        account.addCustomers(newCustomer);
        assertTrue(account.getCustomers().contains(newCustomer));
    }
}
