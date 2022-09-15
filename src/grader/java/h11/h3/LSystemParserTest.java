package h11.h3;

import h11.parse.LSystemParser;
import h11.parse.LSystemParserImpl;
import h11.parse.Projection;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class LSystemParserTest {

    private final LSystemParser parser = new LSystemParserImpl();

    @Test
    @Tag("H3")
    void testThat_parserParsesProjections() {
        assertParser(
            List.of(
                "A -> AB",
                "B -> A"
            ),
            List.of(
                new Projection("A", "AB"),
                new Projection("B", "A")
            ));
    }

    @Test
    @Tag("H3")
    void testThat_parserIgnoresInlineCommentsAndWhitespace() {
        assertParser(
            List.of(
                "   A     ->   AB # aiwdjaw awpdijawid a ",
                "   B   ->   A  # aiwdja jawoidj a ja"
            ),
            List.of(
                new Projection("A", "AB"),
                new Projection("B", "A")
            ));
    }

    @Test
    @Tag("H3")
    void testThat_parserIgnoresLinesWithCommentsAndEmptyLines() {
        assertParser(
            List.of(
                "   # auiwoda aiwdjiawd aiwodj",
                "   ",
                "A -> AB",
                "B -> A",
                "     # auiwoda awdwdawdawda iwdjiawd aiwj",
                "C -> D",
                "  # auiwoda awdawdiwj",
                "   "
            ),
            List.of(
                new Projection("A", "AB"),
                new Projection("B", "A"),
                new Projection("C", "D")
            ));
    }

    private void assertParser(List<String> lines, List<Projection> projections) {
        var actual = parser.parse(lines.stream());
        assertIterableEquals(projections, actual);
    }
}
