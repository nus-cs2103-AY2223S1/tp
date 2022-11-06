package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidSubjectException;

/**
 * Represents the subject of the tuition class.
 */
public enum Subject {
    ENGLISH("english"), MATHEMATICS("mathematics"), PHYSICS("physics"), CHEMISTRY("chemistry"), BIOLOGY("biology"),
    EMATH("emath"), AMATH("amath");

    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only contain alphabetical characters, "
                    + "and should be one of the valid subjects spelt out in full.\n"
                    + "The valid subjects are:\n"
                    + "1. English\n"
                    + "2. Mathematics\n"
                    + "3. Physics\n"
                    + "4. Chemistry\n"
                    + "5. Biology\n"
                    + "6. Elementary Mathematics\n"
                    + "7. Additional Mathematics\n";

    public static final String MESSAGE_DID_YOU_MEAN_ENGLISH = "Did you mean \"English\"?";
    public static final String MESSAGE_DID_YOU_MEAN_MATHEMATICS = "Did you mean \"Math\"?";
    public static final String MESSAGE_DID_YOU_MEAN_PHYSICS = "Did you mean \"Physics\"?";
    public static final String MESSAGE_DID_YOU_MEAN_CHEMISTRY = "Did you mean \"Chemistry\"?";
    public static final String MESSAGE_DID_YOU_MEAN_BIOLOGY = "Did you mean \"Biology\"?";
    public static final String MESSAGE_DID_YOU_MEAN_EMATH = "Did you mean \"EMath\"?";
    public static final String MESSAGE_DID_YOU_MEAN_AMATH = "Did you mean \"AMath\"?";


    public static final String VALIDATION_REGEX = "(?i)english|eng|"
            + "mathematics|maths{0,1}|"
            + "physics|phys{0,1}|"
            + "chemistry|chem|"
            + "biology|bio|"
            + "(e|elem|elementary)\\s*(mathematics|maths{0,1})|"
            + "(a|add|additional)\\s*(mathematics|maths{0,1})";

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
