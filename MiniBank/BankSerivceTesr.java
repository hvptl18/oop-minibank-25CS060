import model.Account;
import repository.Repository;
import service.BankService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    @Test
    void depositIncreasesBalance() {
        Repository<Account> repository = new Repository<>();
        BankService service = new BankService(repository);

        service.openAccount("AC1001", "Amit", 1000);
        service.deposit("AC1001", 500);

        assertEquals(
                1500,
                service.listAccounts().get(0).getBalance()
        );
    }

    @Test
    void withdrawalBeyondBalanceFails() {
        Repository<Account> repository = new Repository<>();
        BankService service = new BankService(repository);

        service.openAccount("AC1002", "Neha", 1000);

        assertThrows(
                RuntimeException.class,
                () -> service.withdraw("AC1002", 2000)
        );
    }
}
