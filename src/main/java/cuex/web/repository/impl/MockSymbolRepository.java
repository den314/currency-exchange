package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Profile(value = {"mock", "default"})
@Repository
public class MockSymbolRepository implements SymbolRepository {

    @Override
    public List<Symbol> findAll() {
        return Arrays.asList(
                new Symbol("AED", "United Arab Emirates Dirham"),
                new Symbol("AFN", "Afghan Afghani"),
                new Symbol("ALL", "Albanian Lek"),
                new Symbol("AMD", "Armenian Dram")
        );
    }
}
