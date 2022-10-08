package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidSubjectException;

/**
 * Represents the subject of the tuition class.
 */
public enum Subject {
    ENGLISH, MATHEMATICS, PHYSICS, CHEMISTRY, BIOLOGY;

    public static final String MESSAGE_CONSTRAINTS =
            "Subject name should only contain letters, and should be spelt out in full.";

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
        throw new InvalidSubjectException();
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
