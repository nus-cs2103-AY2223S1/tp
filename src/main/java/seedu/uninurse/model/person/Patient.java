package seedu.uninurse.model.person;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.TaskList;


/**
 * Represents a Patient in the Uninurse book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    // Data fields
    private final ConditionList conditions;
    private final MedicationList medications;
    private final TaskList tasks;
    private final RemarkList remarks;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Address address, TagList tags, ConditionList conditions,
            MedicationList medications, TaskList tasks, RemarkList remarks) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, email, address, tags, conditions, medications, tasks, remarks);
        this.conditions = conditions;
        this.medications = medications;
        this.tasks = tasks;
        this.remarks = remarks;
    }

    /**
     * Used to return a new immutable {@code Patient} when {@code ConditionList} is updated.
     * @param patient the patient to be updated.
     * @param updatedConditions the updated conditions.
     */
    public Patient(Patient patient, ConditionList updatedConditions) {
        super(patient);
        requireAllNonNull(patient, updatedConditions);
        this.conditions = updatedConditions;
        this.medications = patient.medications;
        this.tasks = patient.tasks;
        this.remarks = patient.remarks;
    }

    /**
     * Used to return a new immutable {@code Patient} when {@code MedicationList} is updated.
     * @param patient the patient to be updated
     * @param updatedMedications the updated medications.
     */
    public Patient(Patient patient, MedicationList updatedMedications) {
        super(patient);
        requireAllNonNull(patient, updatedMedications);
        this.conditions = patient.conditions;
        this.medications = updatedMedications;
        this.tasks = patient.tasks;
        this.remarks = patient.remarks;
    }


    /**
     * Used to return a new immutable {@code Patient} when {@code TaskList} is updated.
     * @param patient the patient to be updated.
     * @param updatedTasks the updated tasks.
     */
    public Patient(Patient patient, TaskList updatedTasks) {
        super(patient);
        requireAllNonNull(patient, updatedTasks);
        this.conditions = patient.conditions;
        this.medications = patient.medications;
        this.tasks = updatedTasks;
        this.remarks = patient.remarks;
    }

    /**
     * Used to return a new immutable {@code Patient} when {@code TagList} is updated.
     * @param patient The patient to be updated.
     * @param updatedTagList The updated tags.
     */
    public Patient(Patient patient, TagList updatedTagList) {
        super(patient, updatedTagList);
        requireAllNonNull(patient, updatedTagList);
        this.conditions = patient.conditions;
        this.medications = patient.medications;
        this.tasks = patient.tasks;
        this.remarks = patient.remarks;
    }

    /**
     * Used to return a new immutable {@code Patient} when {@code RemarkList} is updated.
     * @param patient The patient to be updated.
     * @param updatedRemarks The updated tags.
     */
    public Patient(Patient patient, RemarkList updatedRemarks) {
        super(patient);
        requireAllNonNull(patient, updatedRemarks);
        this.conditions = patient.conditions;
        this.medications = patient.medications;
        this.tasks = patient.tasks;
        this.remarks = updatedRemarks;
    }

    public ConditionList getConditions() {
        return conditions;
    }

    public MedicationList getMedications() {
        return medications;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public RemarkList getRemarks() {
        return remarks;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Patient otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPerson = (Patient) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getConditions().equals(getConditions())
                && otherPerson.getTasks().equals(getTasks())
                && otherPerson.getRemarks().equals(getRemarks())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.hashCode(), conditions, tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress());

        TagList tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ")
                    .append(tags);
        }

        ConditionList conditions = getConditions();
        if (!conditions.isEmpty()) {
            builder.append("\nConditions:\n")
                    .append(conditions);
        }

        MedicationList medications = getMedications();
        if (!medications.isEmpty()) {
            builder.append("\nMedications:\n")
                    .append(medications);
        }

        TaskList tasks = getTasks();
        if (!tasks.isEmpty()) {
            builder.append("\nTasks:\n")
                    .append(tasks);
        }

        RemarkList remarks = getRemarks();
        if (!remarks.isEmpty()) {
            builder.append("\nRemarks:\n")
                    .append(remarks);
        }

        return builder.toString();
    }
}
