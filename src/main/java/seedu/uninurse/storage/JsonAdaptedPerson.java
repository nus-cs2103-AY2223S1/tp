package seedu.uninurse.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedCondition> conditions = new ArrayList<>();
    private final List<JsonAdaptedMedication> medications = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedRemark> remarks = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("conditions") List<JsonAdaptedCondition> conditions,
            @JsonProperty("medications") List<JsonAdaptedMedication> medications,
            @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
            @JsonProperty("remarks") List<JsonAdaptedRemark> remarks,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

        if (conditions != null) {
            this.conditions.addAll(conditions);
        }

        if (medications != null) {
            this.medications.addAll(medications);
        }

        if (tasks != null) {
            this.tasks.addAll(tasks);
        }

        if (remarks != null) {
            this.remarks.addAll(remarks);
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Patient source) {
        name = source.getName().getValue();
        phone = source.getPhone().getValue();
        email = source.getEmail().getValue();
        address = source.getAddress().getValue();
        conditions.addAll(source.getConditions().getInternalList().stream()
                .map(JsonAdaptedCondition::new)
                .collect(Collectors.toList()));
        medications.addAll(source.getMedications().getInternalList().stream()
                .map(JsonAdaptedMedication::new)
                .collect(Collectors.toList()));
        tasks.addAll(source.getTasks().getInternalList().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        remarks.addAll(source.getRemarks().getInternalList().stream()
                .map(JsonAdaptedRemark::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().getInternalList().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Condition> personConditions = new ArrayList<>();
        for (JsonAdaptedCondition condition : conditions) {
            personConditions.add(condition.toModelType());
        }

        final List<Medication> personMedications = new ArrayList<>();
        for (JsonAdaptedMedication medication : medications) {
            personMedications.add(medication.toModelType());
        }

        final ArrayList<Task> personTasks = new ArrayList<>();
        for (JsonAdaptedTask task : tasks) {
            personTasks.add(task.toModelType());
        }

        final List<Remark> personRemarks = new ArrayList<>();
        for (JsonAdaptedRemark remark : remarks) {
            personRemarks.add(remark.toModelType());
        }

        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
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

        final ConditionList modelConditions = new ConditionList(personConditions);

        final MedicationList modelMedications = new MedicationList(personMedications);

        final TaskList modelTasks = new TaskList(personTasks);

        final RemarkList modelRemarks = new RemarkList(personRemarks);

        final TagList modelTags = new TagList(personTags);

        return new Patient(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelConditions,
                modelMedications, modelTasks, modelRemarks);
    }
}
