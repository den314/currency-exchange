package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Profile("mongo")
@Repository
public class MongoSymbolRepository implements SymbolRepository {

    @Override
    public List<Symbol> findAll() {
        return null;
    }

    @Override
    public Symbol find(String symbolCode) {
        return null;
    }

    @Override
    public Symbol find(Symbol existing) {
        return null;
    }

    @Override
    public Symbol save(Symbol newSymbol) {
        return null;
    }

    @Override
    public void saveAll(Collection<Symbol> newSymbols) {

    }

    @Override
    public Symbol update(Symbol toUpdate) {
        return null;
    }

    @Override
    public boolean delete(String symbolCode) {
        return false;
    }

    @Override
    public boolean delete(Symbol toDelete) {
        return false;
    }

    @Override
    public boolean exists(String symbolCode) {
        return false;
    }

    @Override
    public boolean exists(Symbol symbol) {
        return false;
    }
}
