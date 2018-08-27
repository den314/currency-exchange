package cuex.web.repository;

import cuex.web.model.Symbol;

import java.util.Collection;
import java.util.List;

public interface SymbolRepository {

    List<Symbol> findAll();
    Symbol find(String symbolCode);
    Symbol find(Symbol existing);
    Symbol save(Symbol newSymbol);
    void saveAll(Collection<Symbol> newSymbols);
    Symbol update(Symbol toUpdate);
    boolean delete(String symbolCode);
    boolean delete(Symbol toDelete);
    boolean exists(String symbolCode);
    boolean exists(Symbol symbol);
}
