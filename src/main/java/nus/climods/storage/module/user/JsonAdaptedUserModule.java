package nus.climods.storage.module.user;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
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
    private final UserModule source;
    private final String moduleCode;
    private final String tutorial;
    private final String lecture;
    private final String selectedSemesters;

    /**
     * Converts a given {@code UserModule} into this class for Jackson use.
     */
    public JsonAdaptedUserModule(UserModule source) {
        this.source = source;
        this.moduleCode = source.getUserModuleCode();
        this.selectedSemesters = source.getSelectedSemester();
        this.tutorial = source.getTutorial();
        this.lecture = source.getLecture();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code UserModule} object.
     * @param model Current list of modules
     */
    public UserModule toModelType(Model model) throws CommandException {
        return new UserModule(moduleCode, model, lecture, tutorial, selectedSemesters);
    }

    /**
     * Get moduleCode
     *
     * @return moduleCode
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "EL1101E", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_MODULE_CODE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getModuleCode() {
        return moduleCode;
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
                && Objects.equals(this.lecture, userModule.lecture)
                && Objects.equals(this.tutorial, userModule.tutorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, selectedSemesters, lecture, tutorial);
    }
}
