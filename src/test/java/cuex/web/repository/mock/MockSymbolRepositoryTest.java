package cuex.web.repository.mock;

import cuex.web.model.Symbol;
import cuex.web.repository.impl.MockSymbolRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MockSymbolRepositoryTest {

    @Test
    void test() {
        final MockSymbolRepository repo = new MockSymbolRepository();

        final Symbol save = repo.save(new Symbol("AED", "abc"));
        assertThat(save).isNotNull();
    }
}
