package seedu.address.testutil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public abstract class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_LOCATION = "NUS";
    public static final String DEFAULT_USERNAME = "amyb";

    private Name name;
    private Phone phone;
    private Email email;
    private Gender gender;
    private Set<Tag> tags;
    private Location location;
    private GithubUsername githubUsername;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gender = new Gender(DEFAULT_GENDER);
        tags = new HashSet<>();
        location = new Location(DEFAULT_LOCATION);
        githubUsername = new GithubUsername(DEFAULT_USERNAME, false);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        gender = personToCopy.getGender();
        tags = new HashSet<>(personToCopy.getTags());
        location = personToCopy.getLocation();
        githubUsername = personToCopy.getUsername();
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
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
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
     * Sets the {@code Location} of the {@code Person} that we are building.
     */
    public PersonBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code GithubUsername} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithubUsername(String username) {
        if (username.equals(GithubUsername.DEFAULT_USERNAME)) {
            this.githubUsername = new GithubUsername(username, false);
        } else {
            this.githubUsername = new GithubUsername(username, true);
        }
        return this;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Location getLocation() {
        return location;
    }

    public GithubUsername getGithubUsername() {
        return githubUsername;
    }

    public abstract Person build();

}
