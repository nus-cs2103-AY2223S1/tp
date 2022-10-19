package jarvis.model;

import java.util.EnumMap;

public class GradeProfile {
    private final EnumMap<Assessment, GradeComponent> grades;

    /**
     * Constructor for the default grade profile (i.e. all components are ungraded)
     */
    public GradeProfile() {
        grades = new EnumMap<>(Assessment.class);
        for (Assessment a : Assessment.values()) {
            grades.put(a, new GradeComponent(a));
        }
    }

    public void grade(Assessment a, int mark, int totalMark) {
        grades.get(a).setGrade(mark, totalMark);
    }

    public String getMc1() {
        return grades.get(Assessment.MC1).getGrade();
    }

    public String getMc2() {
        return grades.get(Assessment.MC2).getGrade();
    }

    public String getRa1() {
        return grades.get(Assessment.RA1).getGrade();
    }

    public String getRa2() {
        return grades.get(Assessment.RA2).getGrade();
    }

    public String getMidterm() {
        return grades.get(Assessment.MIDTERM).getGrade();
    }

    public String getPracticalAssessment() {
        return grades.get(Assessment.PRACTICAL_ASSESSMENT).getGrade();
    }

    public String getFinalAssessment() {
        return grades.get(Assessment.FINAL_ASSESSMENT).getGrade();
    }

    public String getStudioAttendance() {
        return grades.get(Assessment.STUDIO_ATTENDANCE).getGrade();
    }
}
