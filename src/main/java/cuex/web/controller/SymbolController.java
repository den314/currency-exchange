package cuex.web.controller;

import cuex.web.model.dto.SymbolsDto;
import cuex.web.service.SymbolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/symbols")
public class SymbolController {

    private SymbolService symbolService;

    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @GetMapping
    public SymbolsDto allAvailableSymbols() {
        return symbolService.allSymbolsAsDto();
    }
}
