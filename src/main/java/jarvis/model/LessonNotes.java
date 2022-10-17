package jarvis.model;

import java.util.HashMap;
import java.util.HashSet;

public class LessonNotes {
    private String notes;
    private final HashMap<String, String> studentNotes;

    public LessonNotes(HashSet<String> students) {
        notes = "";
        studentNotes = new HashMap<>();
        for (String matricNum : students) {
            studentNotes.put(matricNum, "");
        }
    }

}
