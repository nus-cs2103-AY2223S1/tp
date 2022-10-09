package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent extends JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String id;
    private final String handle;
    private final String info;

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
                              @JsonProperty("info") String info) {
        super(name, phone, email, address, tagged);
        this.id = id;
        this.handle = handle;
        this.info = info;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     *
     * @param source
     */
    public JsonAdaptedStudent(Person source) {
        super(source);
        id = ((Student) source).getId();
        handle = ((Student) source).getTelegramHandle();
        info = ((Student) source).getStudentInfo();
    }

    @Override
    public Student toModelType() throws IllegalValueException {
        Person person = super.toModelType();
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }
        if (handle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Telegram Handle"));
        }
        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "info"));
        }
        return new Student(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                person.getTags(), id, handle, info);
    }
}
