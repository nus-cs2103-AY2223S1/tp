package nus.climods.storage.module.user;

import java.util.HashMap;
import java.util.Objects;

import org.openapitools.client.model.SemestersEnum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.module.LessonTypeEnum;
import nus.climods.model.module.UserModule;

/**
 * Jackson-friendly version of {@link UserModule}.
 */
@JsonPropertyOrder({
    JsonAdaptedUserModule.JSON_PROPERTY_MODULE_CODE,
    JsonAdaptedUserModule.JSON_PROPERTY_SELECTED_SEMESTER,
    JsonAdaptedUserModule.JSON_PROPERTY_LESSONS
})
class JsonAdaptedUserModule {
    public static final String JSON_PROPERTY_MODULE_CODE = "moduleCode";
    public static final String JSON_PROPERTY_SELECTED_SEMESTER = "selectedSemester";
    public static final String JSON_PROPERTY_LESSONS = "lessons";
    private String moduleCode;
    private String selectedSemester;
    private HashMap<LessonTypeEnum, String> lessons;

    /**
     * Converts a given {@code UserModule} into this class for Jackson use.
     */
    public JsonAdaptedUserModule(UserModule source) {
        this.moduleCode = source.getCode();
        this.selectedSemester = source.getSelectedSemester().name();
        this.lessons = source.getLessons();
    }

    public JsonAdaptedUserModule() {

    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code UserModule} object.
     */
    public UserModule toModelType() throws CommandException {
        SemestersEnum semester = SemestersEnum.fromValue(selectedSemester);

        if (lessons == null) {
            return new UserModule(moduleCode, semester);
        }
        return new UserModule(moduleCode, semester, lessons);
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
    @JsonProperty(JSON_PROPERTY_SELECTED_SEMESTER)
    public String getSelectedSemester() {
        return selectedSemester;
    }

    @JsonProperty(JSON_PROPERTY_SELECTED_SEMESTER)
    public void setSelectedSemesters(String selectedSemester) {
        this.selectedSemester = selectedSemester;
    }

    @JsonProperty(JSON_PROPERTY_LESSONS)
    public HashMap<LessonTypeEnum, String> getLessons() {
        return lessons;
    }

    @JsonProperty(JSON_PROPERTY_LESSONS)
    public void setLessons(HashMap<LessonTypeEnum, String> lessons) {
        this.lessons = lessons;
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
                && Objects.equals(this.selectedSemester, userModule.selectedSemester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, selectedSemester);
    }
}
