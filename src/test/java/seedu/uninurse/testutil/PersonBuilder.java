package seedu.uninurse.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;
import seedu.uninurse.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private ConditionList conditions;
    private MedicationList medications;
    private TaskList tasks;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.phone = new Phone(DEFAULT_PHONE);
        this.email = new Email(DEFAULT_EMAIL);
        this.address = new Address(DEFAULT_ADDRESS);
        this.conditions = new ConditionList();
        this.medications = new MedicationList();
        this.tasks = new TaskList();
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Patient personToCopy) {
        this.name = personToCopy.getName();
        this.phone = personToCopy.getPhone();
        this.email = personToCopy.getEmail();
        this.address = personToCopy.getAddress();
        this.conditions = personToCopy.getConditions();
        this.medications = personToCopy.getMedications();
        this.tasks = personToCopy.getTasks();
        this.tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code conditions} into a {@code ConditionList} and
     * set it to the {@code Patient} that we are building.
     */
    public PersonBuilder withConditions(String... conditions) {
        this.conditions = SampleDataUtil.getConditionList(conditions);
        return this;
    }

    /**
     * Parses the {@code Medication} into a {@code MedicationList} and
     * set it to the {@code Patient} that we are building.
     */
    public PersonBuilder withMedications(Medication... medications) {
        this.medications = SampleDataUtil.getMedicationList(medications);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code TaskList} and set it to the {@code Patient} that we are building.
     */
    public PersonBuilder withTasks(Task... tasks) {
        this.tasks = SampleDataUtil.getTaskList(tasks);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, address, conditions, medications, tasks, tags);
    }

}
