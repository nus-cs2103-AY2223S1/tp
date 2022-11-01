package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyPersons;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBookWithOnlyPersons();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.getPerson(null));
    }

    @Test
    public void getPerson_personNotInAddressBook_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.getPerson(AMY));
        assertThrows(PersonNotFoundException.class, () -> addressBook.getPerson(BOB));
    }

    @Test
    public void getPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(AMY);
        assertEquals(AMY, addressBook.getPerson(AMY));
    }

    @Test
    public void getPerson_personWithSameNameInAddressBook_returnsTrue() {
        Person personWithSameName = new PersonBuilder().withName(VALID_NAME_AMY).build();
        addressBook.addPerson(AMY);
        assertEquals(AMY, addressBook.getPerson(personWithSameName));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModule(CS2106));
        assertFalse(addressBook.hasModule(MA2001));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(CS2106);
        assertTrue(addressBook.hasModule(CS2106));
    }

    @Test
    public void hasModule_moduleWithSameModuleCodeInAddressBook_returnsTrue() {
        Module moduleWithSameModuleCode =
                new ModuleBuilder().withModuleCode(CS2106.getModuleCode().value).build();
        addressBook.addModule(CS2106);
        assertTrue(addressBook.hasModule(moduleWithSameModuleCode));
    }

    @Test
    public void getModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.getModule(null));
    }

    @Test
    public void getModule_moduleNotInAddressBook_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> addressBook.getModule(CS2106));
        assertThrows(ModuleNotFoundException.class, () -> addressBook.getModule(MA2001));
    }

    @Test
    public void getModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(CS2106);
        assertEquals(CS2106, addressBook.getModule(CS2106));
    }

    @Test
    public void getModule_moduleWithSameModuleCodeInAddressBook_returnsTrue() {
        Module moduleWithSameModuleCode =
                new ModuleBuilder().withModuleCode(CS2106.getModuleCode().value).build();
        addressBook.addModule(CS2106);
        assertEquals(CS2106, addressBook.getModule(moduleWithSameModuleCode));
    }
    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }
}
