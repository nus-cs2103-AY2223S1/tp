package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;
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
    public static final String DEFAULT_GENDER = "female";
    public static final String DEFAULT_BIRTHDATE = "1989-09-11";
    public static final String DEFAULT_RACE = "Chinese";
    public static final String DEFAULT_RELIGION = "Christian";
    public static final String DEFAULT_SURVEY = "Environmental Survey";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Gender gender;
    private Birthdate birthdate;
    private Race race;
    private Religion religion;
    private Set<Survey> surveys;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        gender = new Gender(DEFAULT_GENDER);
        birthdate = new Birthdate(DEFAULT_BIRTHDATE);
        race = new Race(DEFAULT_RACE);
        religion = new Religion(DEFAULT_RELIGION);
        surveys = new HashSet<>();
        surveys.add(new Survey(DEFAULT_SURVEY));
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        gender = personToCopy.getGender();
        birthdate = personToCopy.getBirthdate();
        race = personToCopy.getRace();
        religion = personToCopy.getReligion();
        surveys = new HashSet<>(personToCopy.getSurveys());
        tags = new HashSet<>(personToCopy.getTags());
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
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Birthdate} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthdate(String birthdate) {
        this.birthdate = new Birthdate(birthdate);
        return this;
    }

    /**
     * Sets the {@code Race} of the {@code Person} that we are building.
     */
    public PersonBuilder withRace(String race) {
        this.race = new Race(race);
        return this;
    }

    /**
     * Sets the {@code Religion} of the {@code Person} that we are building.
     */
    public PersonBuilder withReligion(String religion) {
        this.religion = new Religion(religion);
        return this;
    }

    /**
     * Sets the {@code Survey} of the {@code Person} that we are building.
     */
    public PersonBuilder withSurvey(String survey, boolean isDone) {
        Set<Survey> surveyToAdd = new HashSet<>();
        surveyToAdd.add(new Survey(survey, isDone));
        this.surveys = surveyToAdd;
        return this;
    }

    /**
     * Sets the {@code Set<Survey>} of the {@code Person} that we are building.
     */
    public PersonBuilder withSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
        return this;
    }

    /**
     * Builds a new Person with default parameters or given parameters.
     * @return a Person with stated parameters
     */
    public Person build() {
        return new Person(name, phone, email, address, gender,
                birthdate, race, religion, surveys, tags);
    }

}
