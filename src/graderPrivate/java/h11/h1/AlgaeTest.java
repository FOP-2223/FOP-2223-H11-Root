package h11.h1;

import h11.Algae;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;

import java.util.List;

import static h11.Algae.Variable.A;
import static h11.Algae.Variable.B;

@TestForSubmission
public class AlgaeTest {

    private final Algae algae = new Algae();

    @Test
    @Tag("H1")
    public void testThat_aIsAxiom() throws Exception {
        var context = Assertions2
            .contextBuilder()
            .subject(Algae.class.getMethod("getAxiom"))
            .build();

        Assertions2.assertEquals(A, algae.getAxiom(), context, result ->
            "`A` was not returned as the axiom");
    }

    @Test
    @Tag("H1")
    public void testProjectOfA() throws NoSuchMethodException {
        assertProject(List.of(A, B), A);
    }

    @Test
    @Tag("H1")
    public void testProjectOfB() throws NoSuchMethodException {
        assertProject(List.of(A), B);
    }

    private void assertProject(List<Algae.Variable> expected, Algae.Variable variable) throws NoSuchMethodException {
        var context = Assertions2
            .contextBuilder()
            .subject(Algae.class.getMethod("project", Algae.Variable.class))
            .add("v", variable)
            .build();

        var actual = algae.project(variable).toList();
        Assertions2.assertEquals(expected, actual, context, result ->
            String.format("`%s` was not projected correctly", variable));
    }
}
