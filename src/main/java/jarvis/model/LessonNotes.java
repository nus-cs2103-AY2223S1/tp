package jarvis.model;

import java.util.Collection;
import java.util.HashMap;

public class LessonNotes {
    private final StringBuilder overallNotes;
    private final HashMap<Student, StringBuilder> studentNotes;

    public LessonNotes(Collection<Student> students) {
        overallNotes = new StringBuilder();
        studentNotes = new HashMap<>();
        for (Student stu : students) {
            studentNotes.put(stu, new StringBuilder());
        }
    }

    public void addNotes(String notes) {
        overallNotes.append(notes);
        overallNotes.append("\n");
    }

    public void addNotes(Student student, String notes) {
    }
}
