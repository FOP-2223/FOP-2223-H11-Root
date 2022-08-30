package h11.parse;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

class LSystemParserImpl implements LSystemParser {

    @Override
    public List<Projection> parse(Stream<String> lines) {
        return lines
                .map(this::removeComments)
                .filter(Predicate.not(String::isBlank))
                .map(this::parseProjection)
                .toList();
    }

    private Projection parseProjection(String line) {
        var parts = line.split("\\s*->\\s*");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Line does not have the correct pattern: " + line);
        }

        return new Projection(parts[0], parts[1]);
    }

    private String removeComments(String line) {
        var parts = line.split("#");
        return parts.length == 0 ? "" : parts[0].trim();
    }
}
