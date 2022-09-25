package h11;

import org.tudalgo.algoutils.student.io.PropertyUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestCaseUtils {

    private static final long NUMBER_OF_TEST_CASES =
        PropertyUtils.getIntProperty("h11/h11-grader.properties", "NUMBER_OF_TEST_CASES_PER_METHOD");

    public static <T> void generateTestCases(Path path, Supplier<T> generateTestCase) throws IOException {
        var testCases = Stream
            .generate(generateTestCase)
            .limit(NUMBER_OF_TEST_CASES)
            .toList();

        JsonUtils.writePojo(path, testCases);
    }
}
