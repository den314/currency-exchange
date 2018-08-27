package cuex.web.repository;

import cuex.web.model.Symbol;

import java.util.Collection;
import java.util.List;

public interface SymbolRepository {

    List<Symbol> findAll();
    Symbol save(Symbol newSymbol);
    Symbol saveAll(Collection<Symbol> newSymbols);
    Symbol update(Symbol toUpdate);
    Symbol delete(Symbol toDelete);
}
