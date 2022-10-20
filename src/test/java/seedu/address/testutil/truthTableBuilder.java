package seedu.address.testutil;

import seedu.address.model.TruthTable;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class truthTableBuilder {

    private TruthTable truthTable;

    public truthTableBuilder() {
        truthTable = new TruthTable();
    }

    public truthTableBuilder(TruthTable truthTable) {
        this.truthTable = truthTable;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public truthTableBuilder withPerson(Person person) {
        truthTable.addPerson(person);
        return this;
    }

    public TruthTable build() {
        return truthTable;
    }
}
