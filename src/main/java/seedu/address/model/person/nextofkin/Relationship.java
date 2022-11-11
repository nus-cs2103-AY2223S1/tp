package seedu.address.model.person.nextofkin;

// Solution below adapted from:
// https://github.com/dextertanyj/tp/blob/master/src/main/java/tutorspet/model/lesson/Day.java
/**
 * Represents the relationship that the next of kin has with a student.
 */
public enum Relationship {
    FATHER("father"), MOTHER("mother"), BROTHER("brother"), SISTER("sister"), GUARDIAN("guardian");

    public static final String MESSAGE_CONSTRAINTS =
            "Relationship should only contain letters, and should be spelt out in full.";
    public static final String VALIDATION_REGEX = "(?i)father|mother|brother|sister|guardian";
    public final String relationship;

    Relationship(String relationship) {
        this.relationship = relationship;
    }


    /**
     * Creates a Relationship object depending on the inputRelationship.
     *
     * @param inputRelationship A string representing the relationship that the next of kin has with a student.
     * @return A Relationship object with the respective enum value.
     */
    public static Relationship createRelationship(String inputRelationship) {
        for (Relationship relationship : Relationship.values()) {
            if (inputRelationship.toUpperCase().equals(relationship.name())) {
                return relationship;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid value in enum Relationship.
     */
    public static boolean isValidRelationship(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
