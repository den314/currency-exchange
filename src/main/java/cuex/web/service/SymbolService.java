package cuex.web.service;

import cuex.web.model.Symbol;
import cuex.web.model.dto.SymbolsDto;

import java.util.Collection;
import java.util.List;

public interface SymbolService {
    List<Symbol> findAll();

    SymbolsDto allSymbolsAsDto();

    void saveAll(Collection<Symbol> newSymbols);
}
