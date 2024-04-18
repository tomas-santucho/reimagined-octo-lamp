package la.decrypto.challenges.account.core;

import la.decrypto.challenges.account.data.AccountRepository;
import la.decrypto.challenges.account.domain.Account;
import la.decrypto.challenges.account.core.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void createAccountSuccessfully() {
        Account account = new Account(1L, "description", null);
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.create(account);

        assertEquals(account, result);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void updateAccountSuccessfully() {
        Account account = new Account(1L, "description", null);
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.update(account);

        assertEquals(account, result);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void deleteAccountSuccessfully() {
        Long id = 1L;
        doNothing().when(accountRepository).deleteById(id);

        accountService.delete(id);

        verify(accountRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAccountSuccessfully() {
        Long id = 1L;
        Account account = new Account(1L, "description", null);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.get(id);

        assertEquals(Optional.of(account), result);
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    public void getAccountNotFound() {
        Long id = 1L;
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Account> result = accountService.get(id);

        assertEquals(Optional.empty(), result);
        verify(accountRepository, times(1)).findById(id);
    }
}