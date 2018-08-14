package cuex.web.repository.impl;

import cuex.web.model.Rate;
import cuex.web.repository.RateRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Profile(value = {"mock", "default"})
@Repository
public class MockRateRepository implements RateRepository {

    @Override
    public List<Rate> findAll() {
        return Arrays.asList(
                new Rate("PLN", 0.233086722d),
                new Rate("USD", 0.875541741d),
                new Rate("GBP", 1.12126253d),
                new Rate("BTC", 0.000189d)
        );
    }
}
