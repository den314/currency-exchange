package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Profile("h2-jdbc")
@Repository
public class H2JdbcSymbolRepository implements SymbolRepository {

    private JdbcTemplate jdbcTemplate;

    public H2JdbcSymbolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Symbol> findAll() {
        return jdbcTemplate.query("SELECT * FROM CURRENCY",
                (resultSet, i) ->
                        new Symbol(resultSet.getString("code"), resultSet.getString("description")));
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
        return null;
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
        return null;
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
