package h11.tutor;

import h11.AbstractRandom;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TutorRandom extends AbstractRandom {

    public TutorRandom() {
    }

    public TutorRandom(long seed) {
        super(seed);
    }

    @Override
    public <T> Stream<T> choices(T[] values) {
        return ints(0, values.length)
            .mapToObj(i -> values[i]);
    }

    @Override
    public String latin(int length) {
        return ints(length, 'A', 'Z'+1)
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }
}
