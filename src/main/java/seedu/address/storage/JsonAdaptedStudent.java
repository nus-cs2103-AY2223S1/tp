package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String id;
    private final String handle;
    private final List<JsonAdaptedModuleCode> info = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tagged
     */
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("ID") String id, @JsonProperty("handle") String handle,
                              @JsonProperty("info") List<JsonAdaptedModuleCode> info) {
        super(name, phone, email, address, tagged);
        this.id = id;
        this.handle = handle;
        if (info != null) {
            this.info.addAll(info);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     *
     * @param source
     */
    public JsonAdaptedStudent(Person source) {
        super(source);
        id = ((Student) source).getId().value;
        handle = ((Student) source).getTelegramHandle().telegramHandle;
        info.addAll(((Student) source).getStudentModuleInfo().stream()
                .map(JsonAdaptedModuleCode::new)
                .collect(Collectors.toList()));
    }

    @Override
    public Student toModelType() throws IllegalValueException {
        Person person = super.toModelType();
        final List<ModuleCode> moduleCodes = new ArrayList<>();
        for (JsonAdaptedModuleCode moduleCode : info) {
            moduleCodes.add(moduleCode.toModelType());
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentID(id)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(id);

        if (handle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(handle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle = new TelegramHandle(handle);

        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "INFO"));
        }

        final Set<ModuleCode> modelCodes = new HashSet<>(moduleCodes);
        return new Student(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                person.getTags(), modelStudentId, modelTelegramHandle, modelCodes);
    }
}
