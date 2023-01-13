package h11.parse;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * An implementation of the {{@link LSystemParser}
 * implementing the following file format:
 * <pre>
 *     # Comments begin with a `#`
 *     # source of the first projection is the axiom
 *     A -> AB
 *     # More rules follow with each new line
 *     A -> AB
 * </pre>
 */
public class LSystemParserImpl implements LSystemParser {

    @Override
    public List<Projection> parse(Stream<String> lines) {
        return lines
                .map(this::removeComments)
                .filter(Predicate.not(String::isBlank))
                .map(this::parseProjection)
                .toList();
    }

    /**
     * Parses the given line into an {@link Projection}.
     *
     * @param line The line to parse.
     * @return The parsed {@link Projection}.
     */
    private Projection parseProjection(String line) throws IllegalArgumentException {
        var parts = line.split("\\s*->\\s*");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Line does not have the correct pattern: " + line);
        }

        return new Projection(parts[0], parts[1]);
    }

    /**
     * Remove everything after and including the first <code>#</code>
     * of the given line.
     *
     * @param line The line to strip.
     * @return The given line without a comment.
     */
    private String removeComments(String line) {
        var parts = line.split("#");
        return parts.length == 0 ? "" : parts[0].trim();
    }
}
