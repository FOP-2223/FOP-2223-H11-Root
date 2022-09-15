package h11.h1;

import h11.Algae;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static h11.Algae.Variable.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlgaeTest {

    private final Algae algae = new Algae();

    @Test
    @Tag("H1")
    void testThat_aIsAxiom() {
        assertEquals(A, algae.getAxiom());
    }

    @Test
    @Tag("H1")
    void testProjectOfA() {
        var actual = algae.project(A).toList();
        assertEquals(List.of(A, B), actual);
    }

    @Test
    @Tag("H1")
    void testProjectOfB() {
        var actual = algae.project(B).toList();
        assertEquals(List.of(A), actual);
    }

}
