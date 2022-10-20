package jarvis.model;

import java.util.EnumMap;

/**
 * A class to encapsulate a student's grades for all assessments.
 */
public class GradeProfile {
    private final EnumMap<Assessment, GradeComponent> gradeMap;

    /**
     * Constructor for the default grade profile (i.e. all components are ungraded)
     */
    public GradeProfile() {
        gradeMap = new EnumMap<>(Assessment.class);
        for (Assessment a : Assessment.values()) {
            gradeMap.put(a, new GradeComponent(a));
        }
    }

    public void grade(Assessment a, int mark) {
        gradeMap.get(a).setGrade(mark);
    }

    /**
     * Updates the current gradeMap with all graded components in the given GradeProfile.
     */
    public void updateGrades(GradeProfile gp) {
        EnumMap<Assessment, GradeComponent> updatedGradeMap = gp.getGradeMap();
        for (Assessment a : Assessment.values()) {
            GradeComponent gc = updatedGradeMap.get(a);
            if (gc.isGraded()) {
                gradeMap.get(a).setGrade(gc.getMarks());
            }
        }
    }

    public EnumMap<Assessment, GradeComponent> getGradeMap() {
        return gradeMap.clone();
    }

    // Getters (for Jackson Use)
    public String getMc1() {
        return gradeMap.get(Assessment.MC1).getGradeString();
    }

    public String getMc2() {
        return gradeMap.get(Assessment.MC2).getGradeString();
    }

    public String getRa1() {
        return gradeMap.get(Assessment.RA1).getGradeString();
    }

    public String getRa2() {
        return gradeMap.get(Assessment.RA2).getGradeString();
    }

    public String getMidterm() {
        return gradeMap.get(Assessment.MIDTERM).getGradeString();
    }

    public String getPracticalAssessment() {
        return gradeMap.get(Assessment.PRACTICAL_ASSESSMENT).getGradeString();
    }

    public String getFinalAssessment() {
        return gradeMap.get(Assessment.FINAL_ASSESSMENT).getGradeString();
    }

    public String getStudioAttendance() {
        return gradeMap.get(Assessment.STUDIO_ATTENDANCE).getGradeString();
    }

    // Setters
    public void setMc1(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.MC1);
        gc.setGrade(marks);
        gradeMap.put(Assessment.MC1, gc);
    }

    public void setMc2(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.MC2);
        gc.setGrade(marks);
        gradeMap.put(Assessment.MC2, gc);
    }

    public void setRa1(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.RA1);
        gc.setGrade(marks);
        gradeMap.put(Assessment.RA1, gc);
    }

    public void setRa2(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.RA2);
        gc.setGrade(marks);
        gradeMap.put(Assessment.RA2, gc);
    }

    public void setMidterm(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.MIDTERM);
        gc.setGrade(marks);
        gradeMap.put(Assessment.MIDTERM, gc);
    }

    public void setPracticalAssessment(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.PRACTICAL_ASSESSMENT);
        gc.setGrade(marks);
        gradeMap.put(Assessment.PRACTICAL_ASSESSMENT, gc);
    }

    public void setFinalAssessment(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.FINAL_ASSESSMENT);
        gc.setGrade(marks);
        gradeMap.put(Assessment.FINAL_ASSESSMENT, gc);
    }

    public void setStudioAttendance(double marks) {
        GradeComponent gc = new GradeComponent(Assessment.STUDIO_ATTENDANCE);
        gc.setGrade(marks);
        gradeMap.put(Assessment.STUDIO_ATTENDANCE, gc);
    }
}
