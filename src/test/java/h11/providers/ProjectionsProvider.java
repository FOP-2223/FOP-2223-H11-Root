package h11.providers;

import h11.Random;
import h11.parse.Projection;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

/**
 * Provide a JUnit test with a random L-System.
 */
public class ProjectionsProvider implements ArgumentsProvider {

    /**
     * The maximum stream size.
     */
    protected static final long MAX_STREAM_SIZE = 100;

    /**
     * The seed to use when generating the system.
     * If this is zero, a unique seed is used.
     */
    private static final int SEED = 0;

    /**
     * The {@link Random} to use with the given seed.
     */
    protected final Random random = SEED != 0 ? new Random() : new Random(SEED);

    /**
     * The {@link RandomLSystemGenerator} to use with the given random instance.
     */
    protected final RandomLSystemGenerator generator = new RandomLSystemGenerator(random);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return generateProjections()
            .map(Arguments::of);
    }

    /**
     * Generate <code>MAX_STREAM_SIZE</code> random L-Systems,
     * each represented as a {@link List} of {@link Projection}s.
     *
     * @return A {@link Stream} of random L-System.
     */
    protected Stream<List<Projection>> generateProjections() {
        return Stream
            .generate(generator::generate)
            .limit(MAX_STREAM_SIZE);
    }
}
