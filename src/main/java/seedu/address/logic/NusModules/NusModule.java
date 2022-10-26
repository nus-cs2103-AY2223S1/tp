package seedu.address.logic.nusmodules;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Custom class for NUSModules deserialization
 */
public class NusModule {
    @JsonProperty("moduleCode")
    private String moduleCode;
    @JsonProperty("title")
    private String moduleTitle;
    @JsonProperty("semesters")
    private int[] semesterOffered;

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getModuleTitle() {
        return this.moduleTitle;
    }

    //todo validating semesterOffereed array
    public void setSemesterOffered(int[] semesterOffered) {
        this.semesterOffered = semesterOffered;
    }

    public int[] getSemesterOffered() {
        return this.semesterOffered;
    }

    @Override
    public String toString() {
        return moduleCode + ", " + moduleTitle + ", Semester(s) " + Arrays.toString(semesterOffered);
    }
}
