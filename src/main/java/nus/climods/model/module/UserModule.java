package nus.climods.model.module;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.openapitools.client.api.ModulesApi;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.Model;

/**
 * Class representing module a User has in their My Modules list
 */
public class UserModule {
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module not in current NUS curriculum";
    private final ModulesApi api = new ModulesApi();

    // Identity fields
    private final Module apiModule;
    private final String acadYear = "2022-2023";
    private String tutorial = "Tutorial: Monday, 1400-1500";
    private String lecture = "Lecture: Friday, 1600-1800";

    /**
     * Creates a UserModule
     * @param moduleCode String for the module code
     * @throws ParseException if module code is not a valid module in current NUS curriculum
     */
    public UserModule(String moduleCode, Model model) throws CommandException {
        Optional<Module> optionalModule = model.getModuleList().getListModule(moduleCode);

        if (optionalModule.isEmpty()) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        apiModule = optionalModule.get();
    }

    /**
     * Constructor for use purely in testing stub classes.
     */
    protected UserModule() {
        this.apiModule = null;
    }

    public Module getApiModule() {
        return this.apiModule;
    }

    public String getUserModuleCode() {
        return this.apiModule.getCode();
    }

    public String getUserModuleTitle() {
        return this.apiModule.getTitle();
    }

    public String getDepartment() {
        return apiModule.getDepartment();
    }

    //TODO: fix getWorkload from API
    public String getWorkload() {
        return apiModule.getModuleCredit();
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

    private List<Integer> getAvailableSemesters() {
        return apiModule.getSemesters();
    }

    // TODO: update with user's selected semester
    public String getSelectedSemester() {
        return getAvailableSemesters().contains(1) ? "Semester 1" : "Semester 2";
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
        return Objects.hash(apiModule);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getUserModuleCode());

        //TODO: add string builder for other module details
        return builder.toString();
    }

}
