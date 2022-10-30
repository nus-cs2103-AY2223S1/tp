package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_6;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_9;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ModuleIndexCommand.
 */
public class ModuleIndexCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexFirstPerson = Index.fromZeroBased(0);
        Person firstPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withCurrentModules("CS2103T", VALID_MODULE_7)
                .withPreviousModules(VALID_MODULE_9).withPlannedModules("CS2109S", VALID_MODULE_11).build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_7).withPreviousModules(VALID_MODULE_9)
                .withPlannedModules(VALID_MODULE_11).withModToRemove("CS2040S").build();
        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(ModuleIndexCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedPerson.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allAddFieldsSpecifiedUnfilteredList_success() {
        Index indexFirstPerson = Index.fromZeroBased(0);
        Person firstPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPreviousModules(VALID_MODULE_3, VALID_MODULE_4)
                .withPlannedModules(VALID_MODULE_5, VALID_MODULE_6).build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedPerson).build();
        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(ModuleIndexCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedPerson.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPreviousModules(VALID_MODULE_3)
                .withPlannedModules(VALID_MODULE_5, VALID_MODULE_6).build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPlannedModules(VALID_MODULE_5, VALID_MODULE_6).build();
        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(ModuleIndexCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedPerson.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeModulesOnlyUnfilteredList_success() {
        Index indexFirstPerson = Index.fromZeroBased(0);
        Person firstPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withCurrentModules().withPlannedModules().build();

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModToRemove("CS2103T", "CS2109S").build();
        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(ModuleIndexCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedPerson.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(INDEX_FIRST_PERSON,
                new ModuleCommand.EditModuleDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(ModuleIndexCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedPerson.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(moduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withCurrentModules("CS2103T", VALID_MODULE_2)
                .build();
        ModuleIndexCommand editCommand = new ModuleIndexCommand(INDEX_FIRST_PERSON,
                new EditModuleDescriptorBuilder().withCurrentModules(VALID_MODULE_2).build());

        String expectedMessage = String.format(ModuleIndexCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedPerson.getModuleList());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_7).build();
        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(moduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ModuleIndexCommand moduleCommand = new ModuleIndexCommand(outOfBoundIndex,
                new EditModuleDescriptorBuilder().withCurrentModules(VALID_MODULE_8).build());

        assertCommandFailure(moduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ModuleIndexCommand standardCommand = new ModuleIndexCommand(INDEX_FIRST_PERSON, MODULE_DESC_AMY);

        // same values -> returns true
        ModuleCommand.EditModuleDescriptor copyDescriptor = new ModuleCommand.EditModuleDescriptor(MODULE_DESC_AMY);
        ModuleIndexCommand commandWithSameValues = new ModuleIndexCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ModuleIndexCommand(INDEX_SECOND_PERSON, MODULE_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ModuleIndexCommand(INDEX_FIRST_PERSON, MODULE_DESC_BOB)));
    }

}
