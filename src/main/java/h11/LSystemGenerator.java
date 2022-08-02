package h11;

import java.util.List;
import java.util.stream.Stream;

public interface LSystemGenerator<T> {

    Stream<List<T>> generate();
}
