package h11.parse;

import h11.LSystem;

import java.util.List;
import java.util.stream.Stream;

/**
 * One can utilize this class to
 * use the {@link List} of {@link Projection} parsed
 * by the {@link LSystemParser} to implement an {@link LSystem}.
 */
class ParsedLSystem implements LSystem<Character> {

    /**
     * The projections of the L-System this instance represents.
     */
    private final List<Projection> projections;

    /**
     * @param projections The projections of the L-System this instance represents.
     */
    public ParsedLSystem(List<Projection> projections) {
        if (projections.isEmpty()) {
            throw new IllegalArgumentException("Need at least on projection rule");
        }

        this.projections = projections;
    }

    @Override
    public Character getAxiom() {
        return projections.get(0).source();
    }

    @Override
    public Stream<Character> project(Character ch) {
        var projection = projections
            .stream()
            .filter(p -> p.source() == ch)
            .map(Projection::destination)
            .findFirst();

        if (projection.isEmpty()) {
            return Stream.of(ch);
        }

        return projection.get()
            .chars()
            .mapToObj(c -> (char) c);
    }
}
