package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.DistinctModuleList;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

   // public static final String DEFAULT_NAME = "Amy Bee";
   // public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_DESCRIPTION = "test";
    public static final String DEFAULT_MODULE = "CS2030S";
  //  public static final String DEFAULT_EMAIL = "amy@gmail.com";
    //public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

//    private Name name;
//    private Phone description;
//    private Email email;
//    private Address address;
//    private Set<Tag> tags;

    private TaskDescription taskDescription;
    private Module module;
    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public TaskBuilder() {
        Module m = new Module(new ModuleCode(DEFAULT_MODULE));
        DistinctModuleList list =  new DistinctModuleList();
        list.addModule(m);


//        name = new Name(DEFAULT_NAME);
//        phone = new Phone(DEFAULT_PHONE);
//        email = new Email(DEFAULT_EMAIL);
//        address = new Address(DEFAULT_ADDRESS);
//        tags = new HashSet<>();
         taskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
         module = new Module(new ModuleCode(DEFAULT_MODULE));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
//    public PersonBuilder(Person personToCopy) {
//        name = personToCopy.getName();
//        phone = personToCopy.getPhone();
//        email = personToCopy.getEmail();
//        address = personToCopy.getAddress();
//        tags = new HashSet<>(personToCopy.getTags());
//    }

    public TaskBuilder(Task taskToCopy) {
        taskDescription = taskToCopy.getDescription();
        module = taskToCopy.getModule();
    }
//        name = personToCopy.getName();
//        phone = personToCopy.getPhone();
//        email = personToCopy.getEmail();
//        address = personToCopy.getAddress();
//        tags = new HashSet<>(personToCopy.getTags());
//    }

    public TaskBuilder withName(String taskDescription) {
        this.taskDescription = new TaskDescription(taskDescription);
        return this;
    }

    public TaskBuilder withModule(String moduleCode) {
        this.module = new Module(new ModuleCode(moduleCode));
        return this;
    }

    public Task build() {
        return new Task(module, taskDescription);
    }
//    /**
//     * Sets the {@code Name} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withName(String name) {
//        this.name = new Name(name);
//        return this;
//    }
//
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
//     */
//    public PersonBuilder withTags(String ... tags) {
//        this.tags = SampleDataUtil.getTagSet(tags);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Address} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withAddress(String address) {
//        this.address = new Address(address);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withEmail(String email) {
//        this.email = new Email(email);
//        return this;
//    }

//    public Person build() {
//        return new Person(name, phone, email, address, tags);
//    }

}
