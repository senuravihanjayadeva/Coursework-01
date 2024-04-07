import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankTest {

    private Bank bank;
    private BankAccount account;
    private Customer customer;

    @Before
    public void setUp() {
        bank = new Bank();
        customer = new Customer("C001", "Guhanathan", "Poravi", "077", "Colombo 06", "XXX");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        account = bank.createAccount("201864747", customers, AccountType.REGULAR, false, 0, 0);
    }

    @Test
    public void testCreateAccount() {
        assertNotNull(account);
        assertEquals("201864747", account.getAccountNumber());
        assertTrue(account.getCustomers().contains(customer));
        assertEquals(AccountType.REGULAR, account.getAccountType());
    }

    @Test
    public void testRemoveAccount() {
        assertTrue(bank.removeAccount(account.getAccountNumber()));
        assertNull(bank.getAccount(account.getAccountNumber()));
    }

    @Test
    public void testGetAccount() {
        BankAccount retrievedAccount = bank.getAccount(account.getAccountNumber());
        assertNotNull(retrievedAccount);
        assertEquals(account, retrievedAccount);
    }

    @Test
    public void testDeposit() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance(), 0);
    }

    @Test
    public void testWithdraw() {
        account.deposit(1000);
        account.withdraw(500);
        assertEquals(500, account.getBalance(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawWithInsufficientFunds() {
        account.withdraw(1000); // Should throw IllegalArgumentException
    }

    @Test
    public void testAddInterest() {
        account.setBalance(1000);
        bank.addInterest();
        assertEquals(1000.0 + (1000.0 * 0.0 / 100 / 12), account.getBalance(), 0.01);
    }

    @Test
    public void testDeductIncomeTax() {
        account.setBalance(1000);
        bank.deductIncomeTax();
        assertEquals(1000 - (1000 * 0.36), account.getBalance(), 0.01);
    }

    @Test
    public void testApplyAnnualCharges() {
        account.setBalance(1500);
        bank.annualCharges();
        assertEquals(1500 - 500, account.getBalance(), 0);
    }

    @Test
    public void testOverdraftCharges() {
        account.setBalance(-200);
        account.setOverdraftAvailable(true);
        account.setOverdraftLimit(100);
        bank.overdraftCharges();
        assertEquals(-200 - 100, account.getBalance(), 0);
    }
}
