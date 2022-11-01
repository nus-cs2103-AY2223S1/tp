package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalModules.getTypicalModulesWithAssociations;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Module} and {@code Person} objects to be used in tests.
 */
public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical modules and person details.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical modules and person details, including
     * the person-to-module associations.
     */
    public static AddressBook getTypicalAddressBookWithAssociations() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModulesWithAssociations()) {
            ab.addModule(module);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static AddressBook getTypicalAddressBookWithOnlyModules() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBookWithOnlyPersons() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }
}
