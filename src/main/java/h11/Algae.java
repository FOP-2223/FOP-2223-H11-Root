package h11;

import java.util.stream.Stream;

public class Algae implements LSystem<Algae.Variable> {

    public enum Variable { A, B }

    @Override
    public Variable getAxiom() {
        return Variable.A;
    }

    @Override
    public Stream<Variable> project(Variable v) {
        return switch (v) {
            case A -> Stream.of(Variable.A, Variable.B);
            case B -> Stream.of(Variable.A);
        };
    }
}
