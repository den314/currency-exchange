package cuex.web.service;

import cuex.web.model.dto.RatesDto;

public interface RateService {

    RatesDto allLatestRatesAsDto(String base);
}
