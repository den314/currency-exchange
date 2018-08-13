package cuex.web.service.impl;

import cuex.web.model.Symbol;
import cuex.web.model.dto.SymbolsDto;
import cuex.web.repository.SymbolRepository;
import cuex.web.service.SymbolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SymbolServiceImpl implements SymbolService {

    private SymbolRepository symbolRepository;

    public SymbolServiceImpl(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    @Override
    public List<Symbol> findAll() {
        return symbolRepository.findAll();
    }

    @Override
    public SymbolsDto allSymbolsAsDto() {
        final Map<String, String> codeNameMap = symbolRepository.findAll().stream()
                .collect(Collectors.toMap(Symbol::getCode, Symbol::getName));
        
        return new SymbolsDto(codeNameMap);
    }
}
