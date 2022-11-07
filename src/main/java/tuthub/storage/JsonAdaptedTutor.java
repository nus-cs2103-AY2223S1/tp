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
import tuthub.model.tutor.CommentList;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.Rating;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.TeachingNomination;
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
    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final String year;
    private final String studentId;
    private final String teachingNomination;
    private final String rating;
    private final List<JsonAdaptedComment> comments = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutor} with the given tutor details.
     */
    @JsonCreator
    public JsonAdaptedTutor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("module") List<JsonAdaptedModule> module,
                            @JsonProperty("year") String year, @JsonProperty("studentId") String studentId,
                            @JsonProperty("teaching nominations") String teachingNomination,
                            @JsonProperty("rating") String rating,
                            @JsonProperty("comments") List<JsonAdaptedComment> comments,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (module != null) {
            this.modules.addAll(module);
        }
        this.year = year;
        this.studentId = studentId;
        this.rating = rating;
        this.teachingNomination = teachingNomination;
        if (comments != null) {
            this.comments.addAll(comments);
        }
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
        modules.addAll(source.getModules().stream()
                .map(JsonAdaptedModule::new)
                .collect(Collectors.toList()));
        year = source.getYear().value;
        studentId = source.getStudentId().value;
        teachingNomination = source.getTeachingNomination().value;
        rating = source.getRating().value;
        comments.addAll(source.getComments().getList().stream()
                .map(JsonAdaptedComment::new)
                .collect(Collectors.toList()));
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

        final List<Module> tutorModules = new ArrayList<>();
        for (JsonAdaptedModule module : modules) {
            tutorModules.add(module.toModelType());
        }

        final List<Comment> tutorComments = new ArrayList<>();
        for (JsonAdaptedComment comment : comments) {
            tutorComments.add(comment.toModelType());
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

        if (modules.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }
        final Set<Module> modelModule = new HashSet<>(tutorModules);

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

        if (teachingNomination == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeachingNomination.class.getSimpleName()));
        }
        if (!TeachingNomination.isValidTeachingNomination(teachingNomination)) {
            throw new IllegalValueException(TeachingNomination.MESSAGE_CONSTRAINTS);
        }
        final TeachingNomination modelTeachingNomination = new TeachingNomination(teachingNomination);

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        final CommentList modelComments = new CommentList(tutorComments);

        final Set<Tag> modelTags = new HashSet<>(tutorTags);

        return new Tutor(modelName, modelPhone, modelEmail, modelModule, modelYear,
                modelStudentId, modelComments, modelTeachingNomination, modelRating, modelTags);
    }

}
