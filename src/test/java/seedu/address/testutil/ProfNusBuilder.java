package seedu.address.testutil;

import seedu.address.model.ProfNus;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building ProfNus objects.
 * Example usage: <br>
 *     {@code ProfNus ab = new ProfNusBuilder().withPerson("John", "Doe").build();}
 */
public class ProfNusBuilder {

    private ProfNus profNus;

    public ProfNusBuilder() {
        profNus = new ProfNus();
    }

    public ProfNusBuilder(ProfNus profNus) {
        this.profNus = profNus;
    }

    /**
     * Adds a new {@code Person} to the {@code ProfNus} that we are building.
     */
    public ProfNusBuilder withPerson(Person person) {
        profNus.addPerson(person);
        return this;
    }

    public ProfNus build() {
        return profNus;
    }
}
