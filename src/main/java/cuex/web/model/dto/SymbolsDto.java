package cuex.web.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SymbolsDto {

    @JsonProperty("symbols")
    private final Map<String, String> currencySymbols;

    @JsonCreator
    public SymbolsDto(@JsonProperty("symbols") Map<String, String> currencySymbols) {
        Objects.requireNonNull(currencySymbols);
        this.currencySymbols = currencySymbols;
    }

    public Map<String, String> getCurrencySymbols() {
        return new HashMap<>(currencySymbols);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SymbolsDto{");
        sb.append("currencySymbols=").append(currencySymbols);
        sb.append('}');
        return sb.toString();
    }
}
