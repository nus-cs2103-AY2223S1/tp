package seedu.address.testutil;

import seedu.address.model.MassLinkers;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building massLinkers objects.
 * Example usage: <br>
 *     {@code MassLinkers ab = new MassLinkersBuilder().withPerson("John", "Doe").build();}
 */
public class MassLinkersBuilder {

    private MassLinkers massLinkers;

    public MassLinkersBuilder() {
        massLinkers = new MassLinkers();
    }

    public MassLinkersBuilder(MassLinkers massLinkers) {
        this.massLinkers = massLinkers;
    }

    /**
     * Adds a new {@code Person} to the {@code MassLinkers} that we are building.
     */
    public MassLinkersBuilder withPerson(Person person) {
        massLinkers.addPerson(person);
        return this;
    }

    public MassLinkers build() {
        return massLinkers;
    }
}
