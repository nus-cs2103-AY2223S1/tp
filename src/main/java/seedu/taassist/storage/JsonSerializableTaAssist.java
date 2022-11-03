package seedu.taassist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.StudentModuleData;
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.Student;

/**
 * An Immutable TaAssist that is serializable to JSON format.
 */
@JsonRootName(value = "taassist")
class JsonSerializableTaAssist {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_MODULE_CLASS = "The class list contains duplicate class(es).";
    public static final String MESSAGE_CLASS_NOT_FOUND = "Class for some student(s) not found in the class list.";
    public static final String MESSAGE_SESSION_NOT_FOUND =
            "Session for some student(s) not found in the session list for the corresponding class.";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedModuleClass> moduleClasses = new ArrayList<>();

    // TODO: Find out what this method is supposed to be used for. There are currently no usages of this method.
    /**
     * Constructs a {@code JsonSerializableTaAssist} with the given students.
     */
    @JsonCreator
    public JsonSerializableTaAssist(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                    @JsonProperty("moduleClasses") List<JsonAdaptedModuleClass> moduleClasses) {
        this.students.addAll(students);
        this.moduleClasses.addAll(moduleClasses);
    }

    /**
     * Converts a given {@code ReadOnlyTaAssist} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaAssist}.
     */
    public JsonSerializableTaAssist(ReadOnlyTaAssist source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        moduleClasses.addAll(source.getModuleClassList().stream()
                .map(JsonAdaptedModuleClass::new).collect(Collectors.toList()));
    }

    /**
     * Converts this TaAssist into the model's {@code TaAssist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaAssist toModelType() throws IllegalValueException {
        TaAssist taAssist = new TaAssist();
        for (JsonAdaptedModuleClass jsonAdaptedModuleClass : moduleClasses) {
            ModuleClass moduleClass = jsonAdaptedModuleClass.toModelType();
            if (taAssist.hasModuleClass(moduleClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE_CLASS);
            }
            taAssist.addModuleClass(moduleClass);
        }
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (taAssist.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }

            // Check if the module data are valid
            for (StudentModuleData moduleData : student.getModuleDataList()) {
                ModuleClass moduleClass = moduleData.getModuleClass();
                if (!taAssist.hasModuleClass(moduleClass)) {
                    throw new IllegalValueException(MESSAGE_CLASS_NOT_FOUND);
                }
                ModuleClass realModuleClass = taAssist.findModuleClass(moduleClass).orElseThrow(AssertionError::new);
                for (SessionData sessionData : moduleData.getSessionDataList()) {
                    if (!realModuleClass.hasSession(sessionData.getSession())) {
                        throw new IllegalValueException(MESSAGE_SESSION_NOT_FOUND);
                    }
                }
            }

            taAssist.addStudent(student);
        }
        return taAssist;
    }

}
