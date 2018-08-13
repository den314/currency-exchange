package cuex.web.repository;

import cuex.web.model.Symbol;

import java.util.List;

public interface SymbolRepository {

    List<Symbol> findAll();
}
