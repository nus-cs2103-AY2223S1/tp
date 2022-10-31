package friday.model.grades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Student's list of grades for their assessments and examinations.
 */
public class GradesList {

    // Names of assessments and exams
    public static final String[] EXAMS = {"RA1", "RA2", "Midterm", "Practical", "Finals"};
    public static final int EXAMS_COUNT = 5;

    public final Map<String, Grade> gradesMap;

    /**
     * Constructs an {@code GradesList} with all grades set to 0 by default.
     */
    public GradesList() {
        this.gradesMap = new HashMap<>();
        initialiseGrades(gradesMap);
    }

    /**
     * Creates and adds the Grades for the associated exams in the gradesMap
     *
     * @param gradesMap
     */
    public void initialiseGrades(Map<String, Grade> gradesMap) {
        for (String exam : EXAMS) {
            gradesMap.putIfAbsent(exam, new Grade(exam, "0"));
        }
    }

    /**
     * Replaces the Grade in the gradesList with the given new Grade
     *
     * @param gradesList
     * @param newGrade
     */
    public static void editGrade(GradesList gradesList, Grade newGrade) {
        String key = newGrade.getExamName();
        gradesList.gradesMap.put(key, newGrade);
    }

    /**
     * Creates and returns an ordered List of {@code Grade}.
     *
     * @return a list of grades
     */
    public List<Grade> getGradesArrayList() {
        List<Grade> gradesList = new ArrayList<>();
        gradesList.add(gradesMap.get("RA1"));
        gradesList.add(gradesMap.get("RA2"));
        gradesList.add(gradesMap.get("Midterm"));
        gradesList.add(gradesMap.get("Practical"));
        gradesList.add(gradesMap.get("Finals"));
        return gradesList;
    }

    /**
     * Returns the Grade associated with the specified name of the exam.
     *
     * @param examName the name of the exam
     * @return the Grade received for that exam
     */
    public Grade getGrade(String examName) {
        return gradesMap.get(examName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(gradesMap.get("RA1"))
                .append(gradesMap.get("RA2"))
                .append(gradesMap.get("Midterm"))
                .append(gradesMap.get("Practical"))
                .append(gradesMap.get("Finals"));
        return builder.toString();
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
