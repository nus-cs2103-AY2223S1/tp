package seedu.address.model.person.testutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;
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
    public static final String DEFAULT_GROUP = "GROUP 1";
    public static final String DEFAULT_ASSIGNMENT = "Assignment 0";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private HashMap<String, ArrayList<Assignment>> assignments;
    private ArrayList<PersonGroup> personGroup;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        assignments = new HashMap<>();
        personGroup = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        assignments = new HashMap<>(personToCopy.getAssignments());
        personGroup = new ArrayList<>(personToCopy.getPersonGroups());
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
     * Parses the {@code Assignments} into a {@code HashMap<String, ArrayList<Assignment>>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAssignments(String[] group, String[][] assignment) {
        this.assignments = new HashMap<>();
        for (int i = 0; i < group.length; i++) {
            ArrayList<Assignment> assignmentList = new ArrayList<>();
            for (int j = 0; j < assignment[i].length; j++) {
                assignmentList.add(new Assignment(assignment[i][j]));
            }
            this.assignments.put(group[i], assignmentList);
        }
        return this;
    }

    /**
     * Parses the {@code PersonGroup} into a {@code Arraylist<PersonGroup>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withGroups(String[] groups) {
        ArrayList<PersonGroup> personGroupArrayList = new ArrayList<>();
        for (String group : groups) {
            personGroupArrayList.add(new PersonGroup(group));
        }
        this.personGroup.addAll(personGroupArrayList);
        return this;

    }

    public Person build() {
        return new Person(name, phone, email, address, tags, assignments, personGroup);
    }

}
