package friday.model.grades;

import java.util.HashMap;
import java.util.Map;

public class GradesList {
    public final Map<String, Grade> gradesMap;
    public static final String[] EXAMS = {"RA1", "Mid-Term", "RA2", "Practical", "Final"};

    public GradesList() {
        this.gradesMap = new HashMap<>();
        initialiseGrades(gradesMap);
    }

    public void initialiseGrades(Map<String, Grade> gradesMap) {
        for (String exam : EXAMS) {
            gradesMap.putIfAbsent(exam, new Grade(exam, "0"));
        }
    }

    public static void editGrade(GradesList gradesList, Grade newGrade) {
        String key = newGrade.examName;
        gradesList.gradesMap.put(key, newGrade);
    }

    @Override
    public String toString() {
        return gradesMap.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradesList // instanceof handles nulls
                && gradesMap.equals(((GradesList) other).gradesMap)); // state check
    }

    @Override
    public int hashCode() {
        return gradesMap.hashCode();
    }
}
