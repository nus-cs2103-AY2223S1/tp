package nus.climods.storage.module.user;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.module.UserModule;

/**
 * Jackson-friendly version of {@link UserModule}.
 */
@JsonPropertyOrder({
    JsonAdaptedUserModule.JSON_PROPERTY_MODULE_CODE,
    JsonAdaptedUserModule.JSON_PROPERTY_TUTORIAL,
    JsonAdaptedUserModule.JSON_PROPERTY_LECTURE,
    JsonAdaptedUserModule.JSON_PROPERTY_SELECTED_SEMESTERS
})

class JsonAdaptedUserModule {
    public static final String JSON_PROPERTY_MODULE_CODE = "moduleCode";
    public static final String JSON_PROPERTY_TUTORIAL = "tutorial";
    public static final String JSON_PROPERTY_LECTURE = "lecture";
    public static final String JSON_PROPERTY_SELECTED_SEMESTERS = "selectedSemesters";
    private String moduleCode;
    private String tutorial;
    private String lecture;
    private String selectedSemesters;

    /**
     * Converts a given {@code UserModule} into this class for Jackson use.
     */
    public JsonAdaptedUserModule(UserModule source) {
        this.moduleCode = source.getUserModuleCode();
        this.selectedSemesters = source.getSelectedSemester();
        this.tutorial = source.getTutorial();
        this.lecture = source.getLecture();
    }

    public JsonAdaptedUserModule() {

    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code UserModule} object.
     */
    public UserModule toModelType() throws CommandException {
        return new UserModule(moduleCode, lecture, tutorial, selectedSemesters);
    }

    /**
     * Get moduleCode
     *
     * @return moduleCode
     **/
    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_MODULE_CODE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getModuleCode() {
        return moduleCode;
    }
    @JsonProperty(JSON_PROPERTY_MODULE_CODE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Get selectedSemesters
     *
     * @return selectedSemesters
     **/
    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_SELECTED_SEMESTERS)

    public String getSelectedSemesters() {
        return selectedSemesters;
    }

    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_SELECTED_SEMESTERS)

    public void setSelectedSemesters(String selectedSemesters) {
        this.selectedSemesters = selectedSemesters;
    }

    /**
     * Get lecture slot
     *
     * @return lecture
     **/
    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_LECTURE)

    public String getLecture() {
        return lecture;
    }

    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_LECTURE)
    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    /**
     * Get tutorial slot
     *
     * @return tutorial
     **/
    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_TUTORIAL)

    public String getTutorial() {
        return tutorial;
    }

    @javax.annotation.Nonnull
    @JsonProperty(JSON_PROPERTY_TUTORIAL)

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }
    /**
     * Return true if this JsonAdaptedUserModule object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonAdaptedUserModule userModule = (JsonAdaptedUserModule) o;
        return Objects.equals(this.moduleCode, userModule.moduleCode)
                && Objects.equals(this.lecture, userModule.lecture)
                && Objects.equals(this.selectedSemesters, userModule.selectedSemesters)
                && Objects.equals(this.tutorial, userModule.tutorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, selectedSemesters, lecture, tutorial);
    }
}
