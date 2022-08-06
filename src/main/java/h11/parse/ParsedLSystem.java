package h11.parse;

import h11.LSystem;

import java.util.List;
import java.util.stream.Stream;

class ParsedLSystem implements LSystem<Character> {

    private final List<Projection> projections;

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
