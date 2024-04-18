package la.decrypto.challenges.stats.domain;

import la.decrypto.challenges.account.domain.Country;

import java.util.List;

public record Stats(Country country, List<MarketStats> markets) {
}
