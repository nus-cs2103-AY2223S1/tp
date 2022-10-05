package seedu.address.ui.module;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a dummy module used to test the UI
 */

public class Module {

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

    public ArrayList<String> getSemesterData() {
        return new ArrayList<String>(Arrays.asList("Semester 1", "Semester 2"));
    }
}
