package seedu.address.testutil;

import seedu.address.model.person.Assignment;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {
    public static final String DEFAULT_NAME = "Assignment";
    public static final String DEFAULT_GRADE = "0/0";
    public static final String DEFAULT_WEIGHTAGE = "0";

    private String name;
    private String grade;
    private String weightage;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        name = DEFAULT_NAME;
        grade = DEFAULT_GRADE;
        weightage = DEFAULT_WEIGHTAGE;
    }

    /**
     * Sets the {@code Name} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withGrade(String grade) {
        this.grade = grade;
        return this;
    }

    /**
     * Sets the {@code Weightage} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withWeightage(String weightage) {
        this.weightage = weightage;
        return this;
    }

    public Assignment build() {
        return new Assignment(name, grade, weightage);
    }
}
