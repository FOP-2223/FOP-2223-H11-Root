package h11.providers;

import h11.parse.Projection;
import h11.Random;

import java.util.List;
import java.util.stream.Stream;

/**
 * To test the LSystemParser this
 * class can generate random {@link List}s
 * of {@link Projection}s representing an LSystem.
 */
public class RandomLSystemGenerator {

    /**
     * The maximum number of {@link Projection}
     * in a generated LSystem.
     */
    private static final int MAX_SYSTEM_SIZE = 10;

    /**
     * The maximum size of the destination
     * in a generated projection.
     */
    private static final int MAX_PROJECTION_DESTINATION_SIZE = 5;

    /**
     * {@link Random} being used.
     */
    private final Random random;

    /**
     * @param random {@link Random} being used.
     */
    public RandomLSystemGenerator(Random random) {
        this.random = random;
    }

    /**
     * Generate a random LSystem
     * represented as a {@link List} of {@link Projection}s.
     *
     * @return A {@link List} of random {@link Projection}s.
     */
    public List<Projection> generate() {
        var size = random.nextInt(1, MAX_SYSTEM_SIZE);
        return Stream
            .generate(this::generateSrc)
            .distinct()
            .limit(size)
            .map(this::makeProjection)
            .toList();
    }

    private String generateSrc() {
        return random.latin(1);
    }

    /**
     * Generate a {@link Projection} with
     * the given source and a random destination.
     *
     * @param src The source of the {@link Projection}
     * @return A random {@link Projection} with the given source.
     */
    public Projection makeProjection(String src) {
        var size = random.nextInt(1, MAX_PROJECTION_DESTINATION_SIZE);
        var dest = random.latin(size);
        return new Projection(src, dest);
    }
}
