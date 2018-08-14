package cuex.web.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class RatesDto {

    private final long timestamp = System.currentTimeMillis();

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private final LocalDate date = LocalDate.now();
    private final String base;

    @JsonProperty("rates")
    private Map<String, Double> currencyRates;

    public RatesDto(Map<String, Double> currencyRates, String base) {
        this.currencyRates = currencyRates;
        this.base = base;
    }

    public Map<String, Double> getCurrencyRates() {
        return new HashMap<>(currencyRates);
    }

    public String getBase() {
        return base;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public LocalDate getDate() {
        return date;
    }
}
