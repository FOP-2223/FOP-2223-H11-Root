package h11;

import java.util.List;
import java.util.stream.Stream;

/**
 * Strait forward implementation of an {@link LSystemGrower}
 * using an instance of {@link LSystem}.
 *
 * @param <T> The token type as used in {@link LSystemGrower}.
 */
public class LSystemGrowerImpl<T> implements LSystemGrower<T> {

    /**
     * The underlying {@link LSystem}.
     */
    private final LSystem<T> lSystem;

    /**
     * @param lSystem The underlying {@link LSystem}.
     */
    public LSystemGrowerImpl(LSystem<T> lSystem) {
        this.lSystem = lSystem;
    }

    @Override
    public Stream<List<T>> grow() {
        var axiom = List.of(lSystem.getAxiom());
        return Stream.iterate(axiom, this::singleStep);
    }

    /**
     * Project a single sequence according to lSystem.
     * This replaces all tokens in the input list
     * with the corresponding sequence
     * as returned by {@link LSystem#project(Object)}.
     *
     * @param l The input sequence as a {@link List}.
     * @return A {@link List} representing the projection of the input sequence.
     */
    private List<T> singleStep(List<T> l) {
        return l.stream()
                .flatMap(lSystem::project)
                .toList();
    }
}
