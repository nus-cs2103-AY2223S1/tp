package seedu.taassist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.StudentModuleData;
import seedu.taassist.model.session.SessionData;

/**
 * Json-friendly version of {@link StudentModuleData}.
 */
class JsonAdaptedStudentModuleData {

    @JsonProperty("module")
    private final String className;

    @JsonProperty("data")
    private final List<JsonAdaptedSessionData> sessionDataList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudentModuleData} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudentModuleData(@JsonProperty("module") String className,
            @JsonProperty("data") List<JsonAdaptedSessionData> sessionDataList) {
        this.className = className;
        if (sessionDataList != null) {
            this.sessionDataList.addAll(sessionDataList);
        }
    }

    /**
     * Converts a given {@code StudentModuleData} into this class for Jackson use.
     */
    public JsonAdaptedStudentModuleData(StudentModuleData source) {
        className = source.getModuleClass().getClassName();
        sessionDataList.addAll(source.getSessionDataList().stream()
                .map(JsonAdaptedSessionData::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code StudentModuleData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public StudentModuleData toModelType() throws IllegalValueException {
        if (!ModuleClass.isValidModuleClassName(className)) {
            throw new IllegalValueException(ModuleClass.MESSAGE_CONSTRAINTS);
        }

        final List<SessionData> studentSessionDataList = new ArrayList<>();
        for (JsonAdaptedSessionData sessionData : sessionDataList) {
            studentSessionDataList.add(sessionData.toModelType());
        }

        return new StudentModuleData(new ModuleClass(className), studentSessionDataList);
    }
}
