package seedu.address.testutil;

import seedu.address.model.TruthTable;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TruthTableBuilder {

    private TruthTable truthTable;

    public TruthTableBuilder() {
        truthTable = new TruthTable();
    }

    public TruthTableBuilder(TruthTable truthTable) {
        this.truthTable = truthTable;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TruthTableBuilder withPerson(Person person) {
        truthTable.addPerson(person);
        return this;
    }

    public TruthTable build() {
        return truthTable;
    }
}
