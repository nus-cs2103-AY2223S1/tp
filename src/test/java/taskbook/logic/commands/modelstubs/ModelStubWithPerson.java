package taskbook.logic.commands.modelstubs;

import static java.util.Objects.requireNonNull;

import taskbook.model.person.Name;
import taskbook.model.person.Person;

/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithPerson extends ModelStub {
    private final Person person;

    /**
     * Creates a {@code ModelStub} with the given person.
     */
    public ModelStubWithPerson(Person person) {
        requireNonNull(person);
        this.person = person;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.person.isSamePerson(person);
    }

    @Override
    public Person findPerson(Name name) {
        requireNonNull(name);
        return this.person.getName() == name ? this.person : null;
    }
}
