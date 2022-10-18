package nus.climods.model.module;

import java.util.Objects;

/**
 * Class representing module a User has in their My Modules list
 */
public class UserModule {
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module not in current NUS curriculum";

    // Identity fields

    //TODO: Remove when implement tutorial/lecture/selectedSemester support
    private String tutorial = "Tutorial: Monday, 1400-1500";
    private String lecture = "Lecture: Friday, 1600-1800";
    private String selectedSemester;
    private String moduleCode;

    /**
     * Creates a UserModule
     * @param module chosen by user
     */
    public UserModule(Module module) {
        this.moduleCode = module.getCode();
        // Todo:
        this.selectedSemester = module.getSemesters().contains(1) ? "Semester 1" : "Semester 2";
    }

    /**
     * Creates a UserModule from json {@code JsonAdaptedUserModule} object
     * @param moduleCode String for the module code
     * @param lecture cached selected lectures slot
     * @param tutorial cached selected tutorial slot
     * @param selectedSemesters the semesters selected
     */
    public UserModule(String moduleCode, String lecture, String tutorial, String selectedSemesters) {
        this.moduleCode = moduleCode;
        this.lecture = lecture;
        this.tutorial = tutorial;
        // TODO: update with user's selected semester
        this.selectedSemester = selectedSemesters;
    }

    /**
     * Constructor for use purely in testing stub classes.
     */
    protected UserModule() {
    }

    public String getUserModuleCode() {
        return this.moduleCode;
    }

    //TODO: add Tutorial method
    public String getTutorial() {
        return this.tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public String getLecture() {
        return this.lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }


    // TODO: update with user's selected semester
    public String getSelectedSemester() {
        return selectedSemester;
    }

    /**
     * Returns true if both modules have the same name. This defines a weaker notion of equality between two modules.
     */
    public boolean isSameUserModule(UserModule otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getUserModuleCode().equals(getUserModuleCode());
    }

    /**
     * Returns true if both modules have the same identity and data fields. This defines a stronger notion of equality
     * between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof nus.climods.model.module.UserModule)) {
            return false;
        }

        nus.climods.model.module.UserModule otherModule = (nus.climods.model.module.UserModule) other;
        return otherModule.getUserModuleCode().equals(getUserModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, selectedSemester, lecture, tutorial);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getUserModuleCode());

        //TODO: add string builder for other module details
        return builder.toString();
    }

}
