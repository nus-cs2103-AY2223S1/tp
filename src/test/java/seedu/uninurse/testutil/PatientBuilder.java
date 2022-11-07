package seedu.uninurse.testutil;

import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;
import seedu.uninurse.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private TagList tags;
    private ConditionList conditions;
    private MedicationList medications;
    private TaskList tasks;
    private RemarkList remarks;

    /**
     * Creates a PatientBuilder with the default details.
     */
    public PatientBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.phone = new Phone(DEFAULT_PHONE);
        this.email = new Email(DEFAULT_EMAIL);
        this.address = new Address(DEFAULT_ADDRESS);
        this.tags = new TagList();
        this.conditions = new ConditionList();
        this.medications = new MedicationList();
        this.tasks = new TaskList();
        this.remarks = new RemarkList();
    }

    /**
     * Initializes the PatientBuilder with the data of patientToCopy.
     */
    public PatientBuilder(Patient patientToCopy) {
        this.name = patientToCopy.getName();
        this.phone = patientToCopy.getPhone();
        this.email = patientToCopy.getEmail();
        this.address = patientToCopy.getAddress();
        this.tags = patientToCopy.getTags();
        this.conditions = patientToCopy.getConditions();
        this.medications = patientToCopy.getMedications();
        this.tasks = patientToCopy.getTasks();
        this.remarks = patientToCopy.getRemarks();
    }

    /**
     * Sets the Name of the Patient that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the Phone of the Patient that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the Email of the Patient that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the Address of the Patient that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Parses the tags into a TagList and set it to the Patient that we are building.
     */
    public PatientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagList(tags);
        return this;
    }

    /**
     * Parses the conditions into a ConditionList and
     * set it to the Patient that we are building.
     */
    public PatientBuilder withConditions(String... conditions) {
        this.conditions = SampleDataUtil.getConditionList(conditions);
        return this;
    }

    /**
     * Parses the Medication into a MedicationList and
     * set it to the Patient that we are building.
     */
    public PatientBuilder withMedications(Medication... medications) {
        this.medications = SampleDataUtil.getMedicationList(medications);
        return this;
    }

    /**
     * Parses the tasks into a TaskList and set it to the Patient that we are building.
     */
    public PatientBuilder withTasks(Task... tasks) {
        this.tasks = SampleDataUtil.getTaskList(tasks);
        return this;
    }

    /**
     * Parses the remarks into a RemarkList and set it to the Patient that we are building.
     */
    public PatientBuilder withRemarks(String... remarks) {
        this.remarks = SampleDataUtil.getRemarkList(remarks);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, address, tags, conditions, medications, tasks, remarks);
    }
}
