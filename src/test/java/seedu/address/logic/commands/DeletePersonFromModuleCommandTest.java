package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.DeletePersonFromModuleCommand.MESSAGE_NO_SUCH_PERSON_IN_MODULE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_TASK_A;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ModuleBuilder;

public class DeletePersonFromModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeletePersonFromModuleCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new DeletePersonFromModuleCommand(CS2106.getModuleCode(), null));
        assertThrows(NullPointerException.class, () ->
                new DeletePersonFromModuleCommand(null, AMY.getName()));
    }

    @Test
    public void execute_deletePersonFromModuleWhenPersonExistsInModule_deleteSuccessful() throws Exception {
        // Model with just a valid module and a person AMY.
        // AMY is contained in both the address book and module.
        Set<Person> setWithAmy = new HashSet<>(Arrays.asList(AMY));
        Module validModuleWithAmy = new ModuleBuilder().withPersons(setWithAmy).build();
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModuleWithAmy, AMY);

        // Delete AMY from the module.
        CommandResult commandResult = new DeletePersonFromModuleCommand(
                validModuleWithAmy.getModuleCode(),
                AMY.getName()).execute(modelStub);

        HashSet<Person> expectedPersonSet = new HashSet<>();
        assertEquals(String.format(DeletePersonFromModuleCommand.MESSAGE_DELETE_PERSON_FROM_MODULE_SUCCESS,
                        validModuleWithAmy.getModuleCodeAsUpperCaseString(),
                        AMY.getName()),
                commandResult.getFeedbackToUser());
        assertTrue(validModuleWithAmy.isSameModule(modelStub.module));
        assertTrue(AMY.isSamePerson(modelStub.person));
        assertEquals(expectedPersonSet, modelStub.module.getPersons()); // Module's set of persons is now empty.
    }

    @Test
    public void execute_deletePersonFromModuleWhenPersonDoesNotExistInModuleAndAddressBook_throwsCommandException() {
        // Model with just a valid module and a person AMY.
        // AMY is contained in both the address book and module.
        Set<Person> setWithAmy = new HashSet<>(Arrays.asList(AMY));
        Module validModuleWithAmy = new ModuleBuilder().withPersons(setWithAmy).build();
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModuleWithAmy, AMY);

        // Delete BOB from the module.
        DeletePersonFromModuleCommand deletePersonFromModuleCommand =
                new DeletePersonFromModuleCommand(validModuleWithAmy.getModuleCode(), BOB.getName());

        HashSet<Person> expectedPersonSet = new HashSet<>(Arrays.asList(AMY));
        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_PERSON, ()
                        -> deletePersonFromModuleCommand.execute(modelStub));
        assertTrue(validModuleWithAmy.isSameModule(modelStub.module));
        assertTrue(AMY.isSamePerson(modelStub.person));
        assertEquals(expectedPersonSet, modelStub.module.getPersons()); // Module's set of persons remains unchanged.
    }

    @Test
    public void execute_deletePersonFromModuleWhenPersonExistsInAddressBookButNotInModule_throwsCommandException() {
        // Model with just a valid module and a person AMY.
        // AMY is contained in the address book but not in the module.
        Set<Person> emptyPersonsSet = new HashSet<>();
        Module validModuleWithoutAmy = new ModuleBuilder().withPersons(emptyPersonsSet).build();
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModuleWithoutAmy, AMY);

        // Delete AMY from the module.
        DeletePersonFromModuleCommand deletePersonFromModuleCommand =
                new DeletePersonFromModuleCommand(validModuleWithoutAmy.getModuleCode(), AMY.getName());

        String expectedMessage = String.format(MESSAGE_NO_SUCH_PERSON_IN_MODULE,
                validModuleWithoutAmy.getModuleCode(),
                AMY.getName());
        HashSet<Person> expectedPersonSet = new HashSet<>();
        assertThrows(CommandException.class, expectedMessage, () -> deletePersonFromModuleCommand.execute(modelStub));
        assertTrue(validModuleWithoutAmy.isSameModule(modelStub.module));
        assertTrue(AMY.isSamePerson(modelStub.person));
        assertEquals(expectedPersonSet, modelStub.module.getPersons()); // Module's set of persons remains unchanged.
    }

    @Test
    public void execute_deletePersonFromModuleWhenModuleDoesNotExists_throwCommandException() {
        // Model without CS2106.
        ModelStubWithModuleAndPerson modelStub =
                new ModelStubWithModuleAndPerson(CS2103T_WITH_TASK_A, AMY);

        // Delete AMY from CS2106.
        DeletePersonFromModuleCommand deletePersonFromModuleCommand =
                new DeletePersonFromModuleCommand(CS2106.getModuleCode(), AMY.getName());

        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_MODULE, () -> deletePersonFromModuleCommand.execute(modelStub));
    }


    @Test
    public void equals() {
        // Model with just a valid module and a person AMY.
        // AMY is contained in both the address book and module.
        Set<Person> setWithAmy = new HashSet<>(Arrays.asList(AMY));
        Module validModuleWithAmy = new ModuleBuilder().withPersons(setWithAmy).build();
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModuleWithAmy, AMY);

        ModuleCode firstModuleCode = validModuleWithAmy.getModuleCode();
        ModuleCode secondModuleCode = new ModuleCode(VALID_MA2001_MODULE_CODE);

        Name firstName = AMY.getName();
        Name secondName = new Name(VALID_NAME_BOB);

        DeletePersonFromModuleCommand firstCommand = new DeletePersonFromModuleCommand(firstModuleCode, firstName);
        DeletePersonFromModuleCommand secondCommand = new DeletePersonFromModuleCommand(secondModuleCode, secondName);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        DeletePersonFromModuleCommand firstCommandCopy = new DeletePersonFromModuleCommand(firstModuleCode, firstName);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different module code and different name -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different module code -> returns false
        assertFalse(firstCommand.equals(new DeletePersonFromModuleCommand(secondModuleCode, firstName)));

        // different name -> returns false
        assertFalse(firstCommand.equals(new DeletePersonFromModuleCommand(firstModuleCode, secondName)));
    }

    /**
     * A mutable Model stub that contains a single module and a single person.
     */
    private class ModelStubWithModuleAndPerson extends ModelStub {
        private Module module;
        private Person person;

        ModelStubWithModuleAndPerson(Module module, Person person) {
            requireAllNonNull(module, person);
            this.module = module;
            this.person = person;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public Module getModuleUsingModuleCode(ModuleCode moduleCode, boolean isFiltered) {
            requireNonNull(moduleCode);
            if (module.getModuleCode().equals(moduleCode)) {
                return module;
            }
            throw new ModuleNotFoundException();
        }

        @Override
        public Person getPersonUsingName(Name nameOfPersonToGet, boolean isFiltered) {
            requireNonNull(nameOfPersonToGet);
            if (person.getName().equals(nameOfPersonToGet)) {
                return person;
            }
            throw new PersonNotFoundException();
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            requireNonNull(target);
            assertTrue(module.isSameModule(target));
            module = editedModule;
        }
    }
}
