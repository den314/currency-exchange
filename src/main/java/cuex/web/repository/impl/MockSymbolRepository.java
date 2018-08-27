package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Profile(value = {"mock", "default"})
@Repository
public class MockSymbolRepository implements SymbolRepository {

    private List<Symbol> symbols;

    @PostConstruct
    private void init() {
        this.symbols = Arrays.asList(
                new Symbol("AED", "United Arab Emirates Dirham"),
                new Symbol("AFN", "Afghan Afghani"),
                new Symbol("ALL", "Albanian Lek"),
                new Symbol("AMD", "Armenian Dram")
        );
    }

    @Override
    public List<Symbol> findAll() {
        return symbols;
    }

    @Override
    public Symbol save(Symbol newSymbol) {
        return null;
    }

    @Override
    public Symbol saveAll(Collection<Symbol> newSymbols) {
        return null;
    }

    @Override
    public Symbol update(Symbol toUpdate) {
        return null;
    }

    @Override
    public Symbol delete(Symbol toDelete) {
        return null;
    }
}
