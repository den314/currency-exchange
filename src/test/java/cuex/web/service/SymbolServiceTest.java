package cuex.web.service;

import cuex.web.model.Symbol;
import cuex.web.model.dto.SymbolsDto;
import cuex.web.repository.SymbolRepository;
import cuex.web.service.impl.SymbolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.*;


class SymbolServiceTest {

    @Mock
    private SymbolRepository symbolRepository;

    @InjectMocks
    private SymbolServiceImpl symbolService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenSymbolsList_thenFindAll() {
        List<Symbol> symbolsList = Arrays.asList(
                new Symbol("ABC", "ABC currency"),
                new Symbol("DEF", "DEF currency")
        );
        when(symbolRepository.findAll()).thenReturn(symbolsList);

        assertThat(symbolService.findAll()).hasSize(2);
        verify(symbolRepository, times(1)).findAll();
    }

    @Test
    void givenSymbolsList_thenReturnDto() {
        List<Symbol> symbolsList = Arrays.asList(
                new Symbol("ABC", "ABC currency"),
                new Symbol("DEF", "DEF currency")
        );
        when(symbolRepository.findAll()).thenReturn(symbolsList);

        SymbolsDto symbolsDto = symbolService.allSymbolsAsDto();

        assertThat(symbolsDto).isNotNull();
        assertThat(symbolsDto.getCurrencySymbols())
                .contains(entry("ABC", "ABC currency"), entry("DEF", "DEF currency"));
        verify(symbolRepository, times(1)).findAll();
    }
}