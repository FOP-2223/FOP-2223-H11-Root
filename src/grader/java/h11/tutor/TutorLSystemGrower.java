package h11.tutor;

import h11.LSystem;
import h11.LSystemGrower;

import java.util.List;
import java.util.stream.Stream;

public record TutorLSystemGrower<T>(LSystem<T> lSystem) implements LSystemGrower<T> {

    @Override
    public Stream<List<T>> grow() {
        var axiom = List.of(lSystem.getAxiom());
        return Stream.iterate(axiom, this::step);
    }

    private List<T> step(List<T> list) {
        return list
            .stream()
            .flatMap(lSystem::project)
            .toList();
    }
}
