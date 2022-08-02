package h11;

import java.util.List;
import java.util.stream.Stream;

public interface LSystemParser {

    List<Projection> parse(Stream<String> lines);
}
