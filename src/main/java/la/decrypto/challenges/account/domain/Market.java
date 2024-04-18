package la.decrypto.challenges.account.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Market {
    @Id
    private Long id;
    private String code;
    private String description;

    private Country country;

    @ManyToMany
    @JoinTable(
            name = "market_account",
            joinColumns = @JoinColumn(name = "market_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;
}