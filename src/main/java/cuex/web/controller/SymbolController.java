package cuex.web.controller;

import cuex.util.FileUtil;
import cuex.web.model.Symbol;
import cuex.web.model.dto.SymbolsDto;
import cuex.web.service.SymbolService;
import cuex.web.service.impl.CSVProcessorImpl;
import cuex.web.service.impl.SymbolServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @PostMapping
    public void addNewSymbols(@RequestParam("file") MultipartFile file) throws IOException {

        FileUtil.saveToTmpDir(file.getBytes(), file.getOriginalFilename());

        final List<Symbol> symbols = toSymbols(file);
        symbolService.saveAll(symbols);
    }

    /* BAD! Only for showcase purpose */
    @GetMapping("/synchronize")
    public void synchroWithRemoteServer(HttpServletResponse response) throws IOException {
        ((SymbolServiceImpl)symbolService).synchronizeWithRemoteServer();
        response.sendRedirect("/api/symbols");
    }

    private List<Symbol> toSymbols(@RequestParam("file") MultipartFile file) {
        final CSVProcessorImpl csv = new CSVProcessorImpl(FileUtil.TMP_DIR + File.separator + file.getOriginalFilename());
        final List<Symbol> symbols = csv.getSymbols();

        for (Symbol symbol : symbols) {
            System.out.println(symbol);
        }
        return symbols;
    }
}
