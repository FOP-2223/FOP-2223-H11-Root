package h11;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Random extends java.util.Random {

    public Random() {
    }

    public Random(long seed) {
        super(seed);
    }

    @SafeVarargs
    public final <T> Stream<T> choices(T... values) {
        return ints(0, values.length)
                .mapToObj(i -> values[i]);
    }


    public String latin(int length) {
        return ints(length, 'A', 'Z' + 1)
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }
}
