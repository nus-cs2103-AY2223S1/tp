package seedu.address.testutil;

import seedu.address.model.Survin;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Survin objects.
 * Example usage: <br>
 *     {@code Survin ab = new SurvinBuilder().withPerson("John", "Doe").build();}
 */
public class SurvinBuilder {

    private Survin survin;

    public SurvinBuilder() {
        survin = new Survin();
    }

    public SurvinBuilder(Survin survin) {
        this.survin = survin;
    }

    /**
     * Adds a new {@code Person} to the {@code Survin} that we are building.
     */
    public SurvinBuilder withPerson(Person person) {
        survin.addPerson(person);
        return this;
    }

    public Survin build() {
        return survin;
    }
}
