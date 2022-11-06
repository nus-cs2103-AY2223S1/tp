package seedu.address.logic.commands.stubs;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Person;

/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithPerson extends ModelStub {
    private final Person person;

    public ModelStubWithPerson(Person person) {
        requireNonNull(person);
        this.person = person;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.person.isSamePerson(person);
    }
}