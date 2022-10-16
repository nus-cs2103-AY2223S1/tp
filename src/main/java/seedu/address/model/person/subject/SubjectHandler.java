package seedu.address.model.person.subject;

import java.util.HashMap;

/**
 * Handles a Student's subjects in the application.
 */
public class SubjectHandler {

    private final HashMap<String, Subject> subjectsTaken;

    /**
     * Constructs a {@code SubjectHandler}.
     */
    public SubjectHandler() {
        subjectsTaken = new HashMap<>();
    }

    /**
     * Adds a subject to the list of subjects taken by the student
     *
     * @param subject the subject to be added
     */
    public void addSubject(Subject subject) {
        if (Subject.isValidSubject(subject.getSubjectName())) {
            subjectsTaken.put(subject.getSubjectName(), subject);
        }
    }

    /**
     * Gets a subject from the list of subjects taken by the student
     *
     * @param subjectName the name of the subject to be retrieved
     */
    public Subject getSubject(String subjectName) {
        return subjectsTaken.get(subjectName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SubjectHandler) {
            return subjectsTaken.equals(((SubjectHandler) obj).subjectsTaken);
        }
        return false;
    }

    @Override
    public String toString() {
        return subjectsTaken.toString();
    }
}
