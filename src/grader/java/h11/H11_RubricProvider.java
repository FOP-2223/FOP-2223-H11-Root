package h11;

import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

@RubricForSubmission("h11")
public class H11_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H11")
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
