package longtimenosee.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import longtimenosee.commons.exceptions.IllegalValueException;
import longtimenosee.model.policy.Coverage;

/**
 * Jackson-friendly version of {@link Coverage}.
 */
public class JsonAdaptedCoverage {

    private final String coverageName;

    /**
     * Constructs a {@code JsonAdaptedCoverage} with the given {@code coverageName}.
     */
    @JsonCreator
    public JsonAdaptedCoverage(String coverageName) {
        this.coverageName = coverageName;
    }

    /**
     * Converts a given {@code coverageName} into this class for Jackson use.
     */
    public JsonAdaptedCoverage(Coverage coverage) {
        coverageName = coverage.coverageType;
    }

    @JsonValue
    public String getCoverageName() {
        return coverageName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Coverage toModelType() throws IllegalValueException {
        if (!Coverage.isValidCoverageName(coverageName)) {
            throw new IllegalValueException(Coverage.MESSAGE_CONSTRAINTS);
        }
        return new Coverage(coverageName);
    }
}
