package tuthub.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tuthub.commons.exceptions.IllegalValueException;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;

/**
 * Jackson-friendly version of {@link Tutor}.
 */
class JsonAdaptedTutor {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutor's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String module;
    private final String year;
    private final String studentId;
    private final String comment;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutor} with the given tutor details.
     */
    @JsonCreator
    public JsonAdaptedTutor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("module") String module,
            @JsonProperty("year") String year, @JsonProperty("studentId") String studentId,
            @JsonProperty("comment") String comment,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.module = module;
        this.year = year;
        this.studentId = studentId;
        this.comment = comment;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Tutor} into this class for Jackson use.
     */
    public JsonAdaptedTutor(Tutor source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        module = source.getModule().value;
        year = source.getYear().value;
        studentId = source.getStudentId().value;
        comment = source.getComment().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tutor object into the model's {@code Tutor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutor.
     */
    public Tutor toModelType() throws IllegalValueException {
        final List<Tag> tutorTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tutorTags.add(tag.toModelType());
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

        if (module == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }
        if (!Module.isValidModule(module)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        final Module modelModule = new Module(module);

        if (year == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName()));
        }
        if (!Year.isValidYear(year)) {
            throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
        }
        final Year modelYear = new Year(year);

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (comment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Comment.class.getSimpleName()));
        }
        final Comment modelComment = new Comment(comment);

        final Set<Tag> modelTags = new HashSet<>(tutorTags);

        return new Tutor(modelName, modelPhone, modelEmail, modelModule, modelYear,
                modelStudentId, modelComment, modelTags);
    }

}
