package cuex.web.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SymbolsDto {

    @JsonProperty("symbols")
    private final Map<String, String> currencySymbols;

    public SymbolsDto(Map<String, String> currencySymbols) {
        Objects.requireNonNull(currencySymbols);
        this.currencySymbols = currencySymbols;
    }

    public Map<String, String> getCurrencySymbols() {
        return new HashMap<>(currencySymbols);
    }
}
