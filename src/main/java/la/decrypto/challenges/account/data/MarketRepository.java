package la.decrypto.challenges.account.data;


import la.decrypto.challenges.account.domain.Country;
import la.decrypto.challenges.account.domain.Market;
import la.decrypto.challenges.stats.domain.Stats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends CrudRepository<Market, Long> {

    @Query("SELECT m.country AS country, m.code AS marketCode, COUNT(ma) AS accountCount " +
            "FROM Market m JOIN m.accounts ma GROUP BY m.country, m.code")
    List<Object[]> findMarketDistributionRaw();
}