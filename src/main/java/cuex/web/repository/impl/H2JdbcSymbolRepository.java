package cuex.web.repository.impl;

import cuex.web.model.Symbol;
import cuex.web.repository.SymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Profile("h2-jdbc")
@Repository
public class H2JdbcSymbolRepository implements SymbolRepository {

    private static final Logger log = LoggerFactory.getLogger(H2JdbcSymbolRepository.class);

    private final RowMapper<Symbol> symbolRowMapper = (rs, numRow) ->
            new Symbol(rs.getString("code"), rs.getString("name"));

    private final ResultSetExtractor<Symbol> symbolExtractor = rs ->
            new Symbol(rs.getString("code"), rs.getString("name"));

    private JdbcTemplate jdbcTemplate;

    public H2JdbcSymbolRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Symbol> findAll() {
        return jdbcTemplate.query("SELECT * FROM CURRENCY", symbolRowMapper);
    }

    @Override
    public Symbol find(String symbolCode) {
        return jdbcTemplate.query("SELECT name, code FROM CURRENCY WHERE name = ?",
                new Object[]{symbolCode}, symbolExtractor);
    }

    @Override
    public Symbol find(Symbol existing) {
        return jdbcTemplate.query("SELECT name, code FROM CURRENCY WHERE code = ? AND name = ?",
                new Object[]{existing.getCode(), existing.getName()}, symbolExtractor);
    }

    @Override
    public Symbol save(Symbol newSymbol) {
        try {
            jdbcTemplate.update("INSERT INTO CURRENCY (code, name) VALUES (?, ?)", newSymbol.getCode(), newSymbol.getName());
        } catch (DataAccessException ex) {
            log.info("Symbol already exist: " + newSymbol);
        }
        return newSymbol;
    }

    @Override
    public void saveAll(Collection<Symbol> newSymbols) {
        for (Symbol newSymbol : newSymbols) {
            save(newSymbol);
        }
    }

    @Override
    public Symbol update(Symbol toUpdate) {
        int rowsAffected = jdbcTemplate.update("UPDATE CURRENCY SET code = ?, name = ? WHERE code = ?",
                toUpdate.getCode(), toUpdate.getName(), toUpdate.getCode());
        return rowsAffected == 1 ? toUpdate : null;
    }

    @Override
    public boolean delete(String symbolCode) {
        final int rowsAffected = jdbcTemplate.update("DELETE FROM CURRENCY WHERE code = ?", symbolCode);
        return rowsAffected == 1;
    }

    @Override
    public boolean delete(Symbol toDelete) {
        return delete((toDelete.getCode()));
    }

    @Override
    public boolean exists(String symbolCode) {
        final int rowsCount = jdbcTemplate.update("SELECT COUNT(code) FROM CURRENCY");
        return rowsCount == 1;
    }

    @Override
    public boolean exists(Symbol symbol) {
        return exists(symbol.getCode());
    }
}
