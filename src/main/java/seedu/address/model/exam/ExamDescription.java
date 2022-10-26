package seedu.address.model.exam;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * ExamDescription class represents the description of the exam.
 */
public class ExamDescription {
    public static final String DESCRIPTION_CONSTRAINTS =
            "The description of the exam should not be empty";

    public final String description;

    /**
     * The constructor of the ExamDescription class. Sets the
     * description of the class.
     *
     * @param description The description of the class.
     */
    public ExamDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), DESCRIPTION_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValidDescription(String description) {
        return description.length() > 0;
    }

    @Override
    public boolean equals(Object otherDescription) {
        return otherDescription == this || (otherDescription instanceof ExamDescription
                && description.equalsIgnoreCase((((ExamDescription) otherDescription).description)));
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return description;
    }
}


