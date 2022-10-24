package seedu.uninurse.model.person;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.tag.Tag;
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

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Address address, ConditionList conditions,
            MedicationList medications, TaskList tasks, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        requireAllNonNull(name, phone, email, address, conditions, tasks, tags);
        this.conditions = conditions;
        this.medications = medications;
        this.tasks = tasks;
    }

    /**
     * Used to return a new immutable {@code Patient} when {@code ConditionList} is updated.
     * @param patient the patient to be updated
     * @param updatedConditions the updated conditions.
     */
    public Patient(Patient patient, ConditionList updatedConditions) {
        super(patient);
        this.conditions = updatedConditions;
        this.medications = patient.medications;
        this.tasks = patient.tasks;
    }

    /**
     * Used to return a new immutable {@code Patient} when {@code MedicationList} is updated.
     * @param patient the patient to be updated
     * @param updatedMedications the updated medications.
     */
    public Patient(Patient patient, MedicationList updatedMedications) {
        super(patient);
        this.conditions = patient.conditions;
        this.medications = updatedMedications;
        this.tasks = patient.tasks;
    }


    /**
     * Used to return a new immutable {@code Patient} when {@code TaskList} is updated.
     * @param patient the patient to be updated
     * @param updatedTasks the updated tasks.
     */
    public Patient(Patient patient, TaskList updatedTasks) {
        super(patient);
        this.conditions = patient.conditions;
        this.medications = patient.medications;
        this.tasks = updatedTasks;
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

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Patient otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
