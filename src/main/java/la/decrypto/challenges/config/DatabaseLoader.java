package la.decrypto.challenges.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import la.decrypto.challenges.account.domain.Account;
import la.decrypto.challenges.account.domain.Country;
import la.decrypto.challenges.account.domain.Market;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DatabaseLoader implements CommandLineRunner {


    @PersistenceContext
    private EntityManager entityManager;

    public DatabaseLoader(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (isDatabaseEmpty()) {
            loadDemoData();
        }
    }

    private boolean isDatabaseEmpty() {
        return ((Long) entityManager.createQuery("SELECT COUNT(a) FROM Account a").getSingleResult()) == 0;
    }

    private void loadDemoData() {
        var marketAR1 = new Market(IdGenerator.generateId(), "MAE", "Mercado Argentino", Country.ARGENTINA, new ArrayList<>());
        var marketAR2 = new Market(IdGenerator.generateId(), "ROFEX", "Rosario Futures Exchange", Country.ARGENTINA, new ArrayList<>());
        var marketUY1 = new Market(IdGenerator.generateId(), "UFEX", "Uruguay Futures Exchange", Country.URUGUAY, new ArrayList<>());

        entityManager.persist(marketAR1);
        entityManager.persist(marketAR2);
        entityManager.persist(marketUY1);

        var rand = new Random();
        var markets = List.of(marketAR1, marketAR2, marketUY1);
        for (int i = 1; i <= 100; i++) {
            Market chosenMarket = markets.get(rand.nextInt(markets.size()));
            var account = new Account(IdGenerator.generateId(), "Demo Account " + i, List.of(chosenMarket));
            chosenMarket.getAccounts().add(account);
            entityManager.persist(account);
        }
    }
}