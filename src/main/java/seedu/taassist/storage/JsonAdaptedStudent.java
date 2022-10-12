package seedu.taassist.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentModuleData;

/**
 * Json-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    @JsonProperty("classes")
    private final List<String> moduleClasses = new ArrayList<>();

    @JsonProperty("moduleData")
    private final List<JsonAdaptedStudentModuleData> moduleData = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("classes") List<String> moduleClasses,
                              @JsonProperty("moduleData") List<JsonAdaptedStudentModuleData> moduleData) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (moduleClasses != null) {
            this.moduleClasses.addAll(moduleClasses);
        }
        if (moduleData != null) {
            this.moduleData.addAll(moduleData);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        moduleClasses.addAll(source.getModuleClasses().stream()
                .map(x -> x.getClassName())
                .collect(Collectors.toList()));
        moduleData.addAll(source.getModuleData().stream()
                .map(JsonAdaptedStudentModuleData::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module class object into the model's {@code ModuleClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module class.
     */
    public Student toModelType() throws IllegalValueException {
        final List<ModuleClass> studentModuleClasses = new ArrayList<>();
        for (String moduleName : moduleClasses) {
            if (moduleName == null) {
                throw new IllegalValueException(
                        String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleClass.class.getSimpleName()));
            }
            if (!ModuleClass.isValidModuleClassName(moduleName)) {
                throw new IllegalValueException(ModuleClass.MESSAGE_CONSTRAINTS);
            }
            studentModuleClasses.add(new ModuleClass(moduleName));
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<ModuleClass> modelModuleClasses = new HashSet<>(studentModuleClasses);

        final List<StudentModuleData> studentModuleData = new ArrayList<>();
        for (JsonAdaptedStudentModuleData moduleData : moduleData) {
            studentModuleData.add(moduleData.toModelType());
        }

        return new Student(modelName, modelPhone, modelEmail, modelAddress, modelModuleClasses, studentModuleData);
    }

}
