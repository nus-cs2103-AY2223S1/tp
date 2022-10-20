package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidSubjectException;

/**
 * Represents the subject of the tuition class.
 */
public enum Subject {
    ENGLISH("English"), MATHEMATICS("Mathematics"), PHYSICS("Physics"), CHEMISTRY("Chemistry"), BIOLOGY("Biology");

    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only contain alphabetical characters, "
                    + "and should be one of the valid subjects spelt out in full.\n"
                    + "The valid subjects are:\n"
                    + "1. English\n"
                    + "2. Mathematics\n"
                    + "3. Physics\n"
                    + "4. Chemistry\n"
                    + "5. Biology\n";
    public static final String VALIDATION_REGEX = "(?i)english|mathematics|physics|chemistry|biology";
    public final String subject;

    Subject(String subject) {
        this.subject = subject;
    }


    /**
     * Creates a Subject object depending on the inputSubject.
     *
     * @param inputSubject A string representing the subject of the tuition class.
     * @return A Subject object with the respective enum value.
     * @throws InvalidSubjectException if the inputSubject does not match any of the enum values.
     */
    public static Subject createSubject(String inputSubject) throws InvalidSubjectException {
        for (Subject subject : Subject.values()) {
            if (inputSubject.toUpperCase().equals(subject.name())) {
                return subject;
            }
        }
        throw new InvalidSubjectException(); //change to null?
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid value in enum Subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
