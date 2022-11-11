package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2106;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class AddPersonToModuleCommandTest {

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddPersonToModuleCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new AddPersonToModuleCommand(CS2106.getModuleCode(), null));
        assertThrows(NullPointerException.class, () ->
                new AddPersonToModuleCommand(null, TypicalPersons.AMY.getName()));
    }

    @Test
    public void execute_addPersonToModuleAcceptedByModel_addSuccessful() throws Exception {
        Module validModule = new ModuleBuilder().build();
        Person validPerson = new PersonBuilder().build();
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModule, validPerson);

        CommandResult commandResult = new AddPersonToModuleCommand(
                validModule.getModuleCode(),
                validPerson.getName()).execute(modelStub);

        HashSet<Person> expectedPersonList = new HashSet<>();
        expectedPersonList.add(validPerson);

        assertEquals(String.format(
                AddPersonToModuleCommand.MESSAGE_ADD_PERSON_TO_MODULE_SUCCESS,
                        validModule.getModuleCodeAsUpperCaseString(),
                        validPerson.getName()),
                commandResult.getFeedbackToUser());
        assertTrue(validModule.isSameModule(modelStub.module));
        assertTrue(validPerson.isSamePerson(modelStub.person));
        assertEquals(expectedPersonList, modelStub.module.getPersons());
    }

    @Test
    public void execute_personAlreadyAddedToModule_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Set<Person> validPersons = new HashSet<>();
        validPersons.add(validPerson);
        Module validModule = new ModuleBuilder().withPersons(validPersons).build();

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name validName = validPerson.getName();

        AddPersonToModuleCommand addPersonToModuleCommand =
                new AddPersonToModuleCommand(validModuleCode, validName);
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModule, validPerson);

        assertThrows(CommandException.class,
                String.format(AddPersonToModuleCommand.MESSAGE_PERSON_ALREADY_EXISTS_IN_MODULE,
                        validModuleCode, validName), () -> addPersonToModuleCommand.execute(modelStub));
    }

    @Test
    public void execute_nonexistentPerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Module validModule = new ModuleBuilder().build();
        ModuleCode validModuleCode = validModule.getModuleCode();

        Person nonExistentPerson = new PersonBuilder().withName(VALID_NAME_AMY).build();
        Name nonExistentName = nonExistentPerson.getName();

        AddPersonToModuleCommand addPersonToModuleCommand =
                new AddPersonToModuleCommand(validModuleCode, nonExistentName);
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModule, validPerson);

        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_PERSON, () -> addPersonToModuleCommand.execute(modelStub));
    }

    @Test
    public void execute_nonexistentModule_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Module validModule = new ModuleBuilder().build();
        Name validName = validPerson.getName();

        Module nonExistentModule = new ModuleBuilder()
                .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK).build();
        ModuleCode nonExistentModuleCode = nonExistentModule.getModuleCode();

        AddPersonToModuleCommand addPersonToModuleCommand =
                new AddPersonToModuleCommand(nonExistentModuleCode, validName);
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(validModule, validPerson);

        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_MODULE, () -> addPersonToModuleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ModuleCode cs2106ModuleCode = new ModuleCode(VALID_CS2106_MODULE_CODE);
        ModuleCode ma2001ModuleCode = new ModuleCode(VALID_MA2001_MODULE_CODE);

        Name amy = new Name(VALID_NAME_AMY);
        Name bob = new Name(VALID_NAME_BOB);

        AddPersonToModuleCommand firstCommand = new AddPersonToModuleCommand(cs2106ModuleCode, amy);
        AddPersonToModuleCommand secondCommand = new AddPersonToModuleCommand(ma2001ModuleCode, bob);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        AddPersonToModuleCommand firstCommandCopy = new AddPersonToModuleCommand(cs2106ModuleCode, amy);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different module and different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different module -> returns false
        assertFalse(firstCommand.equals(new AddPersonToModuleCommand(ma2001ModuleCode, amy)));

        // different person -> returns false
        assertFalse(firstCommand.equals(new AddPersonToModuleCommand(cs2106ModuleCode, bob)));
    }

    /**
     * A mutable Model stub that contains a single module and a single person.
     */
    private class ModelStubWithModuleAndPerson extends ModelStub {
        private Module module;
        private final Person person;

        ModelStubWithModuleAndPerson(Module module, Person person) {
            requireNonNull(module);
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
            if (!module.getModuleCode().equals(moduleCode)) {
                throw new ModuleNotFoundException();
            }
            return module;
        }

        @Override
        public Person getPersonUsingName(Name nameOfPersonToGet, boolean isFiltered) {
            requireNonNull(nameOfPersonToGet);
            if (!person.getName().equals(nameOfPersonToGet)) {
                throw new PersonNotFoundException();
            }
            return person;
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            requireNonNull(target);
            assertTrue(module.isSameModule(target));
            module = editedModule;
        }

        @Override
        public ObservableList<Boolean> getHomeStatus() {
            return FXCollections.singletonObservableList(true);
        }
    }

}
