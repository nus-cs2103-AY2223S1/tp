package seedu.address.testutil;

import seedu.address.model.PersonModel;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building PersonModel objects.
 */
public class PersonModelBuilder {

    private PersonModel personModel;

    public PersonModelBuilder() {
        personModel = new PersonModel();
    }

    public PersonModelBuilder(PersonModel personModel) {
        this.personModel = personModel;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonModel} that we are building.
     */
    public PersonModelBuilder withPerson(Person person) {
        personModel.addPerson(person);
        return this;
    }

    public PersonModel build() {
        return personModel;
    }
}
