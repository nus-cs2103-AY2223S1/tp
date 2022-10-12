package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidSubjectException;

/**
 * Represents the subject of the tuition class.
 */
public enum Subject {
    ENGLISH("english"), MATHEMATICS("mathematics"), PHYSICS("physics"), CHEMISTRY("chemistry"), BIOLOGY("biology");

    public static final String MESSAGE_CONSTRAINTS =
            "Subject name should only contain letters, and should be spelt out in full.";
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

    //    /**
    //     * Returns true if a given string is a valid value in enum Subject.
    //     */
    //    public static boolean isValidSubject(String subject) {
    //        for (Subject s : Subject.values()) {
    //            if (s.name().equals(subject)) {
    //                return true;
    //            }
    //        }
    //        return false;
    //    }

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
