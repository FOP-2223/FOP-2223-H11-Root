package h11;

import java.util.stream.Stream;

public class Random extends java.util.Random {

    @SafeVarargs
    public final <T> Stream<T> choices(T... values) {
        return Stream
                .generate(() -> nextInt(values.length))
                .map(i -> values[i]);
    }
}
