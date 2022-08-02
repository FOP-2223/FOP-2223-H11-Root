package h11.providers;

import h11.Projection;
import h11.Random;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LSystemProvider implements ArgumentsProvider {

    private static final long MAX_STREAM_SIZE = 10;
    private static final int MAX_SYSTEM_SIZE = 50;
    private static final int MAX_PROJECTION_DESTINATION_SIZE = 5;
    private static final int MAX_LINE_NEXT_TO_PROJECTION = 2;
    private static final int MAX_SPACES_SIZE = 3;
    private static final int MAX_COMMENT_SIZE = 10;

    private final Random random = new Random();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream
                .generate(this::generateLSystem)
                .map(s ->
                        Arguments.of(s, lSystemAsLines(s)))
                .limit(MAX_STREAM_SIZE);
    }

    private Stream<String> lSystemAsLines(List<Projection> lSystem) {
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
                + " -> "
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
        return '#' + generateSpaces() + randomLatin(size);
    }

    private String generateSpaces() {
        var size = random.nextInt(MAX_SPACES_SIZE);
        return random
                .choices(" ", "\t")
                .limit(size)
                .collect(Collectors.joining());
    }

    private List<Projection> generateLSystem() {
        var size = random.nextInt(1, MAX_SYSTEM_SIZE);
        return Stream
                .generate(this::generateProjection)
                .limit(size)
                .toList();
    }

    private Projection generateProjection() {
        var src = randomLatin(1);
        var dest = randomLatin(random.nextInt(1, MAX_PROJECTION_DESTINATION_SIZE));
        return new Projection(src, dest);
    }

    private String randomLatin(int size) {
        return random
                .ints(size, 'A', 'Z' + 1)
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }
}
