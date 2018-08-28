package cuex.web.service.impl;

import cuex.web.model.Symbol;
import cuex.web.model.dto.SymbolsDto;
import cuex.web.repository.SymbolRepository;
import cuex.web.service.SymbolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SymbolServiceImpl implements SymbolService {

    private static final Logger log = LoggerFactory.getLogger(SymbolServiceImpl.class);

    private SymbolRepository symbolRepository;

    @Value("${fixer.symbols.url}")
    private String url;

    private RestTemplate restTemplate;

    public SymbolServiceImpl(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    @Autowired(required = false)
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Symbol> findAll() {
        return symbolRepository.findAll();
    }

    @Override
    public SymbolsDto allSymbolsAsDto() {
        final Map<String, String> codeNameMap = symbolRepository.findAll().stream()
                .collect(Collectors.toMap(Symbol::getCode, Symbol::getName));
        
        return new SymbolsDto(codeNameMap);
    }

    @Override
    public void saveAll(Collection<Symbol> newSymbols) {
        symbolRepository.saveAll(newSymbols);
    }

    public void synchronizeWithRemoteServer() {
        if (restTemplate == null) {
            throw new IllegalStateException("RestTemplate not available, can't process request.");
        }

        final ResponseEntity<SymbolsDto> responseEntity = restTemplate.getForEntity(url, SymbolsDto.class);
        final SymbolsDto body = responseEntity.getBody();

        log.info("Queried remote server, found {} symbols", body.getCurrencySymbols().size());
        final List<Symbol> remoteSymbols = toSymbolList(body);

        symbolRepository.saveAll(remoteSymbols);
    }

    private List<Symbol> toSymbolList(SymbolsDto body) {
        final Map<String, String> currencySymbols = body.getCurrencySymbols();
        return currencySymbols.entrySet()
                .stream()
                .map((entry) -> new Symbol(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
