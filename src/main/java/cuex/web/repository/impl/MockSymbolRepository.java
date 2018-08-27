package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Profile(value = {"mock", "default"})
@Repository
public class MockSymbolRepository implements SymbolRepository {

    private static final Supplier<IllegalArgumentException> SYMBOL_NOT_FOUND =
            () -> new IllegalArgumentException("symbol not found");

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
    public Symbol find(String symbolCode) {
        Objects.requireNonNull(symbolCode);
        return symbols.stream()
                .filter(symbol -> symbol.getCode().equals(symbolCode))
                .findFirst().orElseThrow(SYMBOL_NOT_FOUND);
    }

    @Override
    public Symbol save(Symbol newSymbol) {
        symbols.add(newSymbol);
        return newSymbol;
    }

    @Override
    public void saveAll(Collection<Symbol> newSymbols) {
        symbols.addAll(newSymbols);
    }

    @Override
    public Symbol update(Symbol toUpdate) {
        if (exists(toUpdate)) {
            save(toUpdate);
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
                .filter(symbol -> symbol.equals(existing))
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
}
