package cuex.web.controller;

import cuex.web.model.Symbol;
import cuex.web.model.dto.SymbolsDto;
import cuex.web.symbol.SymbolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/symbols")
public class SymbolController {

    private SymbolService symbolService;

    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @GetMapping
    public SymbolsDto allAvailableSymbols() {
        final Map<String, String> codeNameMap = symbolService.findAll().stream()
                .collect(Collectors.toMap(Symbol::getCode, Symbol::getName));
        return new SymbolsDto(codeNameMap);
    }
}
