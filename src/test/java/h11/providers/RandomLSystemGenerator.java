package h11.providers;

import h11.parse.Projection;
import h11.Random;

import java.util.List;
import java.util.stream.Stream;

public class RandomLSystemGenerator {

    private static final int MAX_SYSTEM_SIZE = 10;
    private static final int MAX_PROJECTION_DESTINATION_SIZE = 5;

    private final Random random;

    public RandomLSystemGenerator(Random random) {
        this.random = random;
    }

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

    private Projection makeProjection(String src) {
        var size = random.nextInt(1, MAX_PROJECTION_DESTINATION_SIZE);
        var dest = random.latin(size);
        return new Projection(src, dest);
    }
}
