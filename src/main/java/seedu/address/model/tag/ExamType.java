package seedu.address.model.tag;

import java.util.HashMap;
import java.util.Map;

public enum ExamType {
    CA1, CA2, SA1, SA2;

    /**
     * Mapping of string input to its exam type.
     */
    public static final HashMap<String, ExamType> EXAM_TYPE_MAP = new HashMap<>(
            Map.of(
                    "CA1", CA1,
                    "CA2", CA2,
                    "SA1", SA1,
                    "SA2", SA2
            ));
}
