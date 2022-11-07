package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Lesson;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GITHUB = "Amy-bee";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Github github;
    private Set<Tag> tags;
    private Set<CurrentModule> currModules;
    private Set<PreviousModule> prevModules;
    private Set<PlannedModule> planModules;
    private Set<Lesson> lessons;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        github = new Github(DEFAULT_GITHUB);
        tags = new HashSet<>();
        currModules = new HashSet<>();
        prevModules = new HashSet<>();
        planModules = new HashSet<>();
        lessons = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        github = personToCopy.getGithub();
        tags = new HashSet<>(personToCopy.getTags());
        currModules = new HashSet<>(personToCopy.getCurrModules());
        prevModules = new HashSet<>(personToCopy.getPrevModules());
        planModules = new HashSet<>(personToCopy.getPlanModules());
        lessons = new HashSet<>(personToCopy.getLessons());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithub(String github) {
        this.github = new Github(github);
        return this;
    }

    /**
     * Parses the {@code current modules} into a {@code Set<CurrentModule>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withCurrentModules(String ... currentModules) {
        this.currModules = SampleDataUtil.getCurrentModuleSet(currentModules);
        return this;
    }

    /**
     * Parses the {@code planned modules} into a {@code Set<PlannedModule>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withPlannedModules(String ... plannedModules) {
        this.planModules = SampleDataUtil.getPlannedModuleSet(plannedModules);
        return this;
    }

    /**
     * Parses the {@code previous modules} into a {@code Set<PreviousModule>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withPreviousModules(String ... previousModules) {
        this.prevModules = SampleDataUtil.getPreviousModuleSet(previousModules);
        return this;
    }

    /**
     * Parses the {@code lessons} into a {@code Set<Lesson>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withLessons(Lesson ... lessons) {
        this.lessons = SampleDataUtil.getLessonSet(lessons);
        return this;
    }

    /**
     * Builds a person with the data stored in the builder.
     *
     * @return a Person with the stored fields, tags, modules and lessons.
     */
    public Person build() {
        Person person = new Person(name, phone, email, address, github, tags, currModules, prevModules, planModules);
        for (Lesson lesson : lessons) {
            try {
                person.addLesson(lesson);
            } catch (CommandException c) {
                System.out.println(c);
            }
        }
        return person;
    }

}
