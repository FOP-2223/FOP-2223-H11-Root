package h11.tutor;

import h11.Algae;
import h11.LSystem;

import java.util.stream.Stream;

public class TutorAlgae implements LSystem<Algae.Variable> {

    @Override
    public Algae.Variable getAxiom() {
        return Algae.Variable.A;
    }

    @Override
    public Stream<Algae.Variable> project(Algae.Variable v) {
        if (v == Algae.Variable.A) {
            return Stream.of(Algae.Variable.A, Algae.Variable.B);
        }
        return Stream.of(Algae.Variable.A);
    }
}
