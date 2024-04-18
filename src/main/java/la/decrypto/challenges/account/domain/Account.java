package la.decrypto.challenges.account.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Account {
    @Id
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "accounts")
    private List<Market> markets;
}