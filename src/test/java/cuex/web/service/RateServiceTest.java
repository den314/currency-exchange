package cuex.web.service;

import cuex.web.model.Rate;
import cuex.web.model.Symbol;
import cuex.web.model.dto.RatesDto;
import cuex.web.model.dto.SymbolsDto;
import cuex.web.repository.RateRepository;
import cuex.web.service.impl.RateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;


class RateServiceTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RateServiceImpl rateService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenRatesList_thenReturnDto() {
        List<Rate> rateList = Arrays.asList(
                new Rate("PLN", 0.233086722d),
                new Rate("USD", 0.875541741d),
                new Rate("GBP", 1.12126253d),
                new Rate("BTC", 0.000189d)
        );
        when(rateRepository.findAll()).thenReturn(rateList);

        RatesDto ratesDto = rateService.allLatestRatesAsDto("EUR");

        assertThat(ratesDto).isNotNull();
        assertThat(ratesDto.getBase()).isEqualTo("EUR");
        assertThat(ratesDto.getCurrencyRates())
                .contains(
                        entry("PLN", 0.233086722d), entry("USD", 0.875541741d),
                        entry("GBP", 1.12126253d), entry("BTC", 0.000189d)
                );
        verify(rateRepository, times(1)).findAll();
    }
}