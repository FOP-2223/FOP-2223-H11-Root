package h11.h5;

import h11.AbstractRandom;
import h11.TestCaseUtils;
import h11.tutor.TutorRandom;
import org.tudalgo.algoutils.student.io.PropertyUtils;

import java.io.IOException;
import java.nio.file.Paths;

public class RandomChoicesTestCaseGenerator {

    private static final int MAX_INPUT_LENGTH =
        PropertyUtils.getIntProperty("h11/h11-grader.properties", "MAX_CHOICES_INPUT_LENGTH");

    private static final int MAX_ELEMENT_LENGTH =
        PropertyUtils.getIntProperty("h11/h11-grader.properties", "MAX_CHOICES_ELEMENT_LENGTH");

    private static final AbstractRandom random = new TutorRandom();

    public static void main(String[] args) throws IOException {
        TestCaseUtils.generateTestCases(Paths.get("random-choices-test.json"), () -> {
            var inputLength = random.nextInt(MAX_INPUT_LENGTH);
            var input = random
                .ints(inputLength, 1, MAX_ELEMENT_LENGTH)
                .mapToObj(random::latin)
                .distinct()
                .toArray(String[]::new);

            var outputLength = 10 * inputLength;
            var seed = random.nextLong();
            random.setSeed(seed);
            var output = random
                .choices(input)
                .limit(outputLength)
                .toList();

            return new RandomChoicesTestCase(seed, input, output);
        });
    }

}
