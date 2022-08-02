package h11;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LSystemParserImpl implements LSystemParser {

    @Override
    public List<Projection> parse(Stream<String> lines) {
        return lines
                .map(this::removeComments)
                .filter(Predicate.not(String::isBlank))
                .map(this::parseProjection)
                .toList();
    }

    private Projection parseProjection(String line) {
        var parts = line.split("\\s+->\\s+");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Line does not have the correct pattern: " + line);
        }

        return new Projection(parts[0], parts[1]);
    }

    private String removeComments(String line) {
        return line.split("#")[0].trim();
    }
}
