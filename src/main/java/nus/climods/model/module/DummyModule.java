package nus.climods.model.module;

import java.util.ArrayList;
import java.util.Arrays;

public class DummyModule {
    public String getModuleCode() {
        return "CS2103";
    }

    public String getDepartment() {
        return "Computer Science";
    }

    public String getWorkload() {
        return "4 MCs";
    }

    public String getTitle() {
        return "Software Engineering";
    }

    public String getAcademicYear() {
        return "AY2223";
    }

    public String getTutorial() {
        return "Tutorial: Monday, 1400-1500";
    }

    public String getLecture() {
        return "Lecture: Friday, 1600-1800";
    }

    public ArrayList<String> getSemesterData() {
        return new ArrayList<String>(Arrays.asList("Semester 1", "Semester 2"));
    }
}
