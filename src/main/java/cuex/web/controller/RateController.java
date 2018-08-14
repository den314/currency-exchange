package cuex.web.controller;

import cuex.web.model.dto.RatesDto;
import cuex.web.service.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/latest")
public class RateController {

    private RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public RatesDto latestRates(@RequestParam(value = "base", defaultValue = "EUR") String base) {
        System.out.println("Base: " + base);
        return rateService.allLatestRatesAsDto(base);
    }
}
