package nus.climods.model.module;

import org.openapitools.client.model.SemestersEnum;

/**
 * Class representing module a chosen by a user
 */
public class UserModule {

    private String code;
    private SemestersEnum selectedSemester;
    private String tutorial = "<Not Selected>"; // TODO placeholder
    private String lecture = "<Not Selected>"; // TODO placeholder

    /**
     * Creates a UserModule
     *
     * @param moduleCode module code
     * @param selectedSemester semester
     */

    public UserModule(String moduleCode, SemestersEnum selectedSemester) {
        this.code = moduleCode;
        this.selectedSemester = selectedSemester;
    }

    /**
     * Creates a UserModule
     *
     * @param moduleCode           string for the module code
     * @param lecture              selected lecture slot
     * @param tutorial             selected tutorial slot
     * @param selectedSemesterStr  selected semester
     */
    public UserModule(String moduleCode, String lecture, String tutorial, String selectedSemesterStr) {
        this.code = moduleCode;
        this.selectedSemester = SemestersEnum.fromValue(selectedSemesterStr);

        this.lecture = lecture;
        this.tutorial = tutorial;
    }

    /**
     * Constructor used for UserModuleStub
     */
    protected UserModule() {}

    /**
     * Returns the module code.
     *
     * @return module code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the semester chosen by user to take this module
     *
     * @return selected semester
     */
    public SemestersEnum getSelectedSemester() {
        return this.selectedSemester;
    }

    // TEMP
    public String getTutorial() {
        return this.tutorial;
    }

    // TEMP
    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    // TEMP
    public String getLecture() {
        return this.lecture;
    }

    // TEMP
    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    /**
     * Returns true if both modules have the same code. This defines a weaker notion of equality between two modules.
     *
     * @param otherModule other user module
     * @return true if both module has the same module code else false
     */
    public boolean isSameUserModule(UserModule otherModule) {
        return (otherModule == this) || otherModule != null && code.equals(otherModule.code);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UserModule)) {
            return false;
        }

        UserModule otherModule = (UserModule) other;
        return code.equals(otherModule.getCode()) && selectedSemester.equals(otherModule.selectedSemester)
            && tutorial.equals(otherModule.tutorial) && lecture.equals(otherModule.lecture);
    }
}
