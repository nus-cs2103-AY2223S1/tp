package seedu.address.model.person.subject;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Constructs a {@code SubjectHandler} with a specified Set of Subjects.
     */
    public SubjectHandler(Set<Subject> subjects) {
        subjectsTaken = new HashMap<>();
        for (Subject subject : subjects) {
            subjectsTaken.put(subject.getSubjectName(), subject);
        }
    }

    /**
     * Adds a subject to the list of subjects taken by the student
     *
     * @param subject the subject to be added
     */
    public void addSubject(Subject subject) {
        subjectsTaken.put(subject.getSubjectName(), subject);
    }

    /**
     * Gets a subject from the list of subjects taken by the student
     *
     * @param subjectName the name of the subject to be retrieved
     */
    public Subject getSubject(String subjectName) {
        return subjectsTaken.get(subjectName);
    }

    public Set<Subject> getSubjectsTaken() {
        return subjectsTaken.keySet().stream().map(subjectsTaken::get).collect(
            Collectors.toSet());
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
