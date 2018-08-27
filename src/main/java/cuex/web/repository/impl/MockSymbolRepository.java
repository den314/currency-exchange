package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Profile(value = "mock")
@Repository
public class MockSymbolRepository implements SymbolRepository {

    private static final Logger log = LoggerFactory.getLogger(MockSymbolRepository.class);

    private static final Supplier<IllegalArgumentException> SYMBOL_NOT_FOUND =
            () -> new IllegalArgumentException("symbol not found");

    private List<Symbol> symbols;

    @PostConstruct
    private void init() {
        this.symbols = new ArrayList<>();
        symbols.add(new Symbol("AED", "United Arab Emirates Dirham"));
        symbols.add(new Symbol("AFN", "Afghan Afghani"));
        symbols.add(new Symbol("ALL", "Albanian Lek"));
        symbols.add(new Symbol("AMD", "Armenian Dram"));
    }

    @Override
    public List<Symbol> findAll() {
        return symbols;
    }

    @Override
    public Symbol find(String symbolCode) {
        Objects.requireNonNull(symbolCode);
        return symbols.stream()
                .filter(symbol -> symbol.getCode().equals(symbolCode))
                .findFirst().orElseThrow(SYMBOL_NOT_FOUND);
    }

    @Override
    public Symbol save(Symbol newSymbol) {
        if (exists(newSymbol)) {
            throw new IllegalStateException("Symbol already exist");
        }
        symbols.add(newSymbol);
        return newSymbol;
    }

    @Override
    public void saveAll(Collection<Symbol> newSymbols) {
        for (Symbol newSymbol : newSymbols) {
            if (symbols.contains(newSymbol)) {
                log.info("Cannot add symbol, already exist: " + newSymbol);
                continue;
            }
            symbols.add(newSymbol);
        }
    }

    @Override
    public Symbol update(Symbol toUpdate) {
        if (exists(toUpdate)) {
            final Symbol symbol = find(toUpdate);
            delete(symbol);
            symbol.setName(toUpdate.getName());
            save(symbol);
        } else {
            throw new IllegalArgumentException("Symbol to update does not exist.");
        }
        return toUpdate;
    }

    @Override
    public boolean delete(String symbolCode) {
        if (exists(symbolCode)) {
            symbols.remove(find(symbolCode));
            return true;
        }
        return false;
    }

    @Override
    public Symbol find(Symbol existing) {
        Objects.requireNonNull(existing);
        return symbols.stream()
                .filter(symbol -> symbol.getCode().equals(existing.getCode()))
                .findFirst().orElseThrow(SYMBOL_NOT_FOUND);
    }

    @Override
    public boolean delete(Symbol toDelete) {
        if (exists(toDelete)) {
            symbols.remove(find(toDelete));
            return true;
        }
        return false;
    }

    @Override
    public boolean exists(String symbolCode) {
        try {
            find(symbolCode);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    @Override
    public boolean exists(Symbol symbol) {
        try {
            find(symbol);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public void deleteAll() {
        symbols.clear();
    }
}
