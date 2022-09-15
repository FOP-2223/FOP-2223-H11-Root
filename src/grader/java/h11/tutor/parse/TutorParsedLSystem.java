package h11.tutor.parse;

import h11.LSystem;
import h11.parse.Projection;

import java.util.List;
import java.util.stream.Stream;

public record TutorParsedLSystem(List<Projection> projections) implements LSystem<Character> {

    @Override
    public Character getAxiom() {
        return projections.get(0).source();
    }

    @Override
    public Stream<Character> project(Character ch) {
        for (var p : projections) {

            if (p.source() == ch) {
                return p.destination()
                    .chars()
                    .mapToObj(c -> (char) c);
            }
        }

        return Stream.of(ch);
    }

}
