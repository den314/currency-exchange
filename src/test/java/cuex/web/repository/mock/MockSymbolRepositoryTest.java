package cuex.web.repository.mock;

import cuex.web.config.RootConfig;
import cuex.web.model.Symbol;
import cuex.web.repository.impl.MockSymbolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("mock")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
class MockSymbolRepositoryTest {

    @Autowired
    private MockSymbolRepository repo;

    @Test
    void whenSymbolExist_thenThrowException() {
        Assertions.assertTrue(repo.exists("AED"));
        Assertions.assertThrows(IllegalStateException.class,
                () -> repo.save(new Symbol("AED", "abc")));
    }

    @Test
    void whenNewSymbol_thenSave() {
        final Symbol saved = repo.save(new Symbol("XYZ", "Xxx Yyy Zzz"));
        assertThat(saved).isNotNull();
        assertThat(saved.getCode()).isEqualTo("XYZ");
        assertThat(saved.getName()).isEqualTo("Xxx Yyy Zzz");
    }

    @Test
    void givenSymbolList_thenSave() {
        final int actualLength = repo.findAll().size();

        final ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.add(new Symbol("QQQ", "QQQ desc"));
        symbols.add(new Symbol("WWW", "WWW desc"));

        repo.saveAll(symbols);
        assertThat(repo.findAll()).hasSize(actualLength+2);

        final Symbol q = repo.find("QQQ");
        final Symbol w = repo.find("WWW");

        assertThat(q.getCode()).isEqualTo("QQQ");
        assertThat(q.getName()).isEqualTo("QQQ desc");
        assertThat(w.getCode()).isEqualTo("WWW");
        assertThat(w.getName()).isEqualTo("WWW desc");
    }

    @Test
    void givenSymbolList_whenOneSymbolExist_thenThrowExceptionButSaveNewSymbols() {
        final int actualLength = repo.findAll().size();
        Assertions.assertTrue(repo.exists("AFN"));

        final ArrayList<Symbol> symbols = new ArrayList<>();
        final Symbol existing = new Symbol("AFN", "new AFN desc");
        final Symbol newSymbol = new Symbol("AAB", "AAB desc");
        symbols.add(existing);
        symbols.add(newSymbol);

        repo.saveAll(symbols);
        assertThat(repo.findAll()).hasSize(actualLength+1);
    }

    @Test
    void whenSymbolExist_thenUpdate() {
        Assertions.assertTrue(repo.exists("AED"));
        final Symbol updated = repo.update(new Symbol("AED", "new desc for AED"));
        assertThat(updated.getCode()).isEqualTo("AED");
        assertThat(updated.getName()).isEqualTo("new desc for AED");
    }

    @Test
    void givenNotExistSymbol_whenUpdate_thenThrowException() {
        Assertions.assertFalse(repo.exists("x10b"));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repo.update(new Symbol("x10b", "new desc")));
    }

    @Test
    void givenExistSymbol_thenDelete() {
        final int actualLength = repo.findAll().size();
        Assertions.assertTrue(repo.exists("AED"));
        repo.delete("AED");
        assertThat(repo.findAll()).hasSize(actualLength-1);
    }
}
