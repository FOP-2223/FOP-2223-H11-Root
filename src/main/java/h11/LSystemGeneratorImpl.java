package h11;

import java.util.List;
import java.util.stream.Stream;

public class LSystemGeneratorImpl<T> implements LSystemGenerator<T> {

    private final LSystem<T> lSystem;

    public LSystemGeneratorImpl(LSystem<T> lSystem) {
        this.lSystem = lSystem;
    }

    @Override
    public Stream<List<T>> generate() {
        var axiom = List.of(lSystem.getAxiom());
        return Stream.iterate(axiom, this::singleStep);
    }

    private List<T> singleStep(List<T> l) {
        return l.stream()
                .flatMap(lSystem::project)
                .toList();
    }
}
