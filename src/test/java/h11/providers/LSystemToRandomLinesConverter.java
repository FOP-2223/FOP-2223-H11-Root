package h11.providers;

import h11.parse.Projection;
import h11.Random;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LSystemToRandomLinesConverter {

    private static final int MAX_LINE_NEXT_TO_PROJECTION = 2;
    private static final int MAX_SPACES_SIZE = 3;
    private static final int MAX_COMMENT_SIZE = 10;

    private final Random random;

    public LSystemToRandomLinesConverter(Random random) {
        this.random = random;
    }

    public Stream<String> lSystemAsLines(List<Projection> lSystem) {
        return lSystem
            .stream()
            .flatMap(this::projectionAsLines);
    }

    private Stream<String> projectionAsLines(Projection projection) {
        return Stream.of(
            generateLinesNextToProjection(),
            projectionAsLine(projection),
            generateLinesNextToProjection()
        ).flatMap(Function.identity());
    }

    private Stream<String> projectionAsLine(Projection projection) {
        var s = generateSpaces()
            + projection.source()
            + generateSpaces()
            + "->"
            + generateSpaces()
            + projection.destination()
            + generateComment();
        return Stream.of(s);
    }

    private Stream<String> generateLinesNextToProjection() {
        var size = random.nextInt(MAX_LINE_NEXT_TO_PROJECTION);
        return Stream
            .generate(this::generateLineNextToProjection)
            .limit(size);
    }

    private String generateLineNextToProjection() {
        return generateSpaces() + generateComment() + generateSpaces();
    }

    private String generateComment() {
        if (random.nextBoolean()) {
            return "";
        }

        var size = random.nextInt(MAX_COMMENT_SIZE);
        return '#' + generateSpaces() + random.latin(size);
    }

    private String generateSpaces() {
        var size = random.nextInt(MAX_SPACES_SIZE);
        return random
            .choices(" ", "\t")
            .limit(size)
            .collect(Collectors.joining());
    }
}
