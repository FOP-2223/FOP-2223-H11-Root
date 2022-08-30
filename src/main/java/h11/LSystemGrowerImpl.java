package h11;

import java.util.List;
import java.util.stream.Stream;

public class LSystemGrowerImpl<T> implements LSystemGrower<T> {

    private final LSystem<T> lSystem;

    public LSystemGrowerImpl(LSystem<T> lSystem) {
        this.lSystem = lSystem;
    }

    @Override
    public Stream<List<T>> grow() {
        var axiom = List.of(lSystem.getAxiom());
        return Stream.iterate(axiom, this::singleStep);
    }

    private List<T> singleStep(List<T> l) {
        return l.stream()
                .flatMap(lSystem::project)
                .toList();
    }
}
