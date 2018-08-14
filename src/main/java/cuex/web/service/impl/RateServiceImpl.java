package cuex.web.service.impl;

import cuex.web.model.Rate;
import cuex.web.model.dto.RatesDto;
import cuex.web.repository.RateRepository;
import cuex.web.service.RateService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {

    private RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public RatesDto allLatestRatesAsDto(String base) {
        final Map<String, Double> currencyRate = rateRepository.findAll().stream()
                .collect(Collectors.toMap(Rate::getCode, Rate::getValue));

        return new RatesDto(currencyRate, base);
    }
}
