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
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building User objects.
 */
public class UserBuilder {

    public static final String DEFAULT_NAME = "Michael Scott";
    public static final String DEFAULT_PHONE = "97575757";
    public static final String DEFAULT_EMAIL = "mikescott@gmail.com";
    public static final String DEFAULT_ADDRESS = "755, Tampines St 76, #04-20";
    public static final String DEFAULT_GITHUB = "michaelSc0tt";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Github github;
    private Set<CurrentModule> currModules;
    private Set<PreviousModule> prevModules;
    private Set<PlannedModule> planModules;
    private Set<Lesson> lessons;

    /**
     * Creates a {@code UserBuilder} with the default details.
     */
    public UserBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        github = new Github(DEFAULT_GITHUB);
        currModules = new HashSet<>();
        prevModules = new HashSet<>();
        planModules = new HashSet<>();
        lessons = new HashSet<>();
    }

    /**
     * Initializes the UserBuilder with the data of {@code userToCopy}.
     */
    public UserBuilder(User userToCopy) {
        name = userToCopy.getName();
        phone = userToCopy.getPhone();
        email = userToCopy.getEmail();
        address = userToCopy.getAddress();
        github = userToCopy.getGithub();
        currModules = new HashSet<>(userToCopy.getCurrModules());
        prevModules = new HashSet<>(userToCopy.getPrevModules());
        planModules = new HashSet<>(userToCopy.getPlanModules());
        lessons = new HashSet<>(userToCopy.getLessons());
    }

    /**
     * Sets the {@code Name} of the {@code User} that we are building.
     */
    public UserBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code User} that we are building.
     */
    public UserBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code User} that we are building.
     */
    public UserBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code User} that we are building.
     */
    public UserBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code User} that we are building.
     */
    public UserBuilder withGithub(String github) {
        this.github = new Github(github);
        return this;
    }

    /**
     * Parses the {@code current modules} into a {@code Set<CurrentModule>}
     * and set it to the {@code User} that we are building.
     */
    public UserBuilder withCurrentModules(String ... currentModules) {
        this.currModules = SampleDataUtil.getCurrentModuleSet(currentModules);
        return this;
    }

    /**
     * Parses the {@code planned modules} into a {@code Set<PlannedModule>}
     * and set it to the {@code User} that we are building.
     */
    public UserBuilder withPlannedModules(String ... plannedModules) {
        this.planModules = SampleDataUtil.getPlannedModuleSet(plannedModules);
        return this;
    }

    /**
     * Parses the {@code previous modules} into a {@code Set<PreviousModule>}
     * and set it to the {@code User} that we are building.
     */
    public UserBuilder withPreviousModules(String ... previousModules) {
        this.prevModules = SampleDataUtil.getPreviousModuleSet(previousModules);
        return this;
    }

    /**
     * Parses the {@code lessons} into a {@code Set<Lesson>}
     * and set it to the {@code User} that we are building.
     */
    public UserBuilder withLessons(Lesson... lessons) {
        this.lessons = SampleDataUtil.getLessonSet(lessons);
        return this;
    }

    /**
     * Builds a new user with the data stored.
     *
     * @return User with all details, modules and lessons stored.
     */
    public User build() {
        User user = new ExistingUser(name, phone, email, address, github, currModules, prevModules, planModules);
        for (Lesson lesson : lessons) {
            try {
                user.addLesson(lesson);
            } catch (CommandException e) {
                System.out.println(e);
            }
        }
        return user;
    }

}
