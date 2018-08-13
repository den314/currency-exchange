package cuex.web.symbol;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymbolService {

    private SymbolRepository symbolRepository;

    public SymbolService(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public List<Symbol> findAll() {
        return symbolRepository.findAll();
    }
}
