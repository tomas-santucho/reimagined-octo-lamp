package la.decrypto.challenges.account.data;

import la.decrypto.challenges.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
