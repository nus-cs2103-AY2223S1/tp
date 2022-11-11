package seedu.intrack.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Salary;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Task;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Internship}.
 */
class JsonAdaptedInternship {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final String name;
    private final String position;
    private final String status;
    private final String email;
    private final String website;
    private final String salary;
    private final List<JsonAdaptedTask> taskFilled = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("name") String name, @JsonProperty("position") String position,
            @JsonProperty("status") String status, @JsonProperty("email") String email,
            @JsonProperty("website") String website,
            @JsonProperty("taskFilled") List<JsonAdaptedTask> taskFilled, @JsonProperty("salary") String salary,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("remark") String remark) {
        this.name = name;
        this.position = position;
        this.status = status;
        this.email = email;
        this.website = website;
        this.salary = salary;
        if (taskFilled != null) {
            this.taskFilled.addAll(taskFilled);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.remark = remark;
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {
        name = source.getName().fullName;
        position = source.getPosition().positionName;
        status = source.getStatus().value;
        email = source.getEmail().value;
        website = source.getWebsite().value;
        salary = source.getSalary().value;
        taskFilled.addAll(source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {
        final List<Task> internshipTasks = new ArrayList<>();
        for (JsonAdaptedTask task : taskFilled) {
            internshipTasks.add(task.toModelType());
        }

        final List<Tag> internshipTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            internshipTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (website == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Website.class.getSimpleName()));
        }
        if (!Website.isValidWebsite(website)) {
            throw new IllegalValueException(Website.MESSAGE_CONSTRAINTS);
        }
        final Website modelWebsite = new Website(website);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final List<Task> modelTasks = new ArrayList<>(internshipTasks);

        final Set<Tag> modelTags = new HashSet<>(internshipTags);

        return new Internship(modelName, modelPosition, modelStatus, modelEmail, modelWebsite, modelTasks, modelSalary,
                modelTags, modelRemark);
    }

}
