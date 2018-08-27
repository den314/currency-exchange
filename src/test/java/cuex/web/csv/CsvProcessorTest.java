package cuex.web.csv;

import cuex.web.model.Symbol;
import cuex.web.service.impl.CSVProcessorImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvProcessorTest {

    @Test
    void givenCsv_thenParseSymbols() throws IOException {
        final CSVProcessorImpl csv = new CSVProcessorImpl("src/test/resources/symbols.csv");
        final List<Symbol> symbols = csv.getSymbols();

        assertThat(symbols).isNotNull();
        assertThat(symbols.size()).isEqualTo(2);
        assertThat(symbols.get(0)).isNotNull();
        assertThat(symbols.get(0).getCode()).isEqualTo("ABC");
        assertThat(symbols.get(0).getName()).isEqualTo("ABC description");
        assertThat(symbols.get(1)).isNotNull();
        assertThat(symbols.get(1).getCode()).isEqualTo("TRE");
        assertThat(symbols.get(1).getName()).isEqualTo("TRE description");
    }
}
