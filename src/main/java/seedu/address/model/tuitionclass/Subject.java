package seedu.address.model.tuitionclass;

import seedu.address.model.tuitionclass.exceptions.InvalidSubjectException;

/**
 * Represents the subject of the tuition class.
 */
public enum Subject {
    ENGLISH, MATHEMATICS, PHYSICS, CHEMISTRY, BIOLOGY;

    public static final String MESSAGE_CONSTRAINTS =
            "Subject name should only contain letters, and should be spelt out in full.";

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
