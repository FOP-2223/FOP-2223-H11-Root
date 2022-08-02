package h11;

import h11.providers.LSystemProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LSystemParserImplTest {

    private final LSystemParser parser = new LSystemParserImpl();

    @ParameterizedTest
    @ArgumentsSource(LSystemProvider.class)
    void testThat_projectionsMatch(List<Projection> projections, Stream<String> lines) {
        var actual = parser.parse(lines);
        assertIterableEquals(projections, actual);
    }
}
