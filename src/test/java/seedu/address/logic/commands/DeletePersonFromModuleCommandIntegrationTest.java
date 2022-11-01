package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithAssociations;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code DeletePersonFromModuleCommand}.
 */
public class DeletePersonFromModuleCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithAssociations(), new UserPrefs());
    }

    @Test
    public void execute_deletePersonFromModuleAtHome_success() {
        Module validModuleWithAliceAndBenson = TypicalModules.CS2106_WITH_ALICE_BENSON;

        ModuleCode validModuleCode = validModuleWithAliceAndBenson.getModuleCode();
        Name aliceName = ALICE.getName();

        model.goToHomePage();

        Set<Person> expectedPersons = new HashSet<>(Arrays.asList(BENSON));
        Module expectedModule = new ModuleBuilder(validModuleWithAliceAndBenson).withPersons(expectedPersons).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.goToHomePage();
        expectedModel.setModule(validModuleWithAliceAndBenson, expectedModule);

        assertCommandSuccess(new DeletePersonFromModuleCommand(validModuleCode, aliceName), model,
                String.format(DeletePersonFromModuleCommand.MESSAGE_DELETE_PERSON_FROM_MODULE_SUCCESS, validModuleCode,
                        aliceName), expectedModel);
    }

    @Test
    public void execute_deletePersonFromModuleNotAtHome_success() {
        Module validModuleWithAliceAndBenson = TypicalModules.CS2106_WITH_ALICE_BENSON;

        ModuleCode validModuleCode = validModuleWithAliceAndBenson.getModuleCode();
        Name aliceName = ALICE.getName();

        Predicate<Module> modulePredicate = module -> module.equals(validModuleWithAliceAndBenson);
        Predicate<Person> personPredicate = person -> person.equals(ALICE) || person.equals(BENSON);

        // Simulate not at home.
        model.updateFilteredModuleList(modulePredicate);
        model.updateFilteredPersonList(personPredicate);
        model.setHomeStatus(false);

        Set<Person> expectedPersons = new HashSet<>(Arrays.asList(BENSON));
        Module expectedModule = new ModuleBuilder(validModuleWithAliceAndBenson).withPersons(expectedPersons).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // Simulate not at home.
        expectedModel.updateFilteredModuleList(modulePredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.setHomeStatus(false);

        // After setting new module, the person list is now updated to be  without the deleted person.
        expectedModel.setModule(validModuleWithAliceAndBenson, expectedModule);
        Predicate<Person> newPersonPredicate = person -> person.equals(BENSON);
        expectedModel.updateFilteredPersonList(newPersonPredicate);

        assertCommandSuccess(new DeletePersonFromModuleCommand(validModuleCode, aliceName), model,
                String.format(DeletePersonFromModuleCommand.MESSAGE_DELETE_PERSON_FROM_MODULE_SUCCESS, validModuleCode,
                        aliceName), expectedModel);
    }

    @Test
    public void execute_deletePersonFromModuleWhenModuleNotInFilteredList_throwsCommandException() {
        Module validModuleWithAliceAndBenson = TypicalModules.CS2106_WITH_ALICE_BENSON;

        ModuleCode validModuleCode = validModuleWithAliceAndBenson.getModuleCode();
        Name aliceName = ALICE.getName();

        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ZERO_MODULE);

        assertCommandFailure(new DeletePersonFromModuleCommand(validModuleCode, aliceName), model,
                Messages.MESSAGE_NO_SUCH_MODULE);
    }

    @Test
    public void execute_deletePersonFromModuleWhenPersonNotInFilteredList_throwsCommandException() {
        Module validModuleWithAliceAndBenson = TypicalModules.CS2106_WITH_ALICE_BENSON;

        ModuleCode validModuleCode = validModuleWithAliceAndBenson.getModuleCode();
        Name aliceName = ALICE.getName();

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ZERO_PERSON);

        assertCommandFailure(new DeletePersonFromModuleCommand(validModuleCode, aliceName), model,
                Messages.MESSAGE_NO_SUCH_PERSON);
    }

    @Test
    public void execute_personToBeDeletedFromModuleDoesNotExistInModule_throwsCommandException() {
        Module validModuleWithAliceAndBenson = TypicalModules.CS2106_WITH_ALICE_BENSON;

        ModuleCode validModuleCode = validModuleWithAliceAndBenson.getModuleCode();
        Name nameOfPersonThatDoesNotExistInModule = CARL.getName();

        assertCommandFailure(new DeletePersonFromModuleCommand(validModuleCode, nameOfPersonThatDoesNotExistInModule),
                model, String.format(DeletePersonFromModuleCommand.MESSAGE_NO_SUCH_PERSON_IN_MODULE,
                        validModuleCode, nameOfPersonThatDoesNotExistInModule));
    }

    @Test
    public void execute_nonexistentPerson_throwsCommandException() {
        Module validModule = TypicalModules.CS2106_WITH_ALICE_BENSON;
        Person nonexistentPerson = TypicalPersons.AMY;

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name nameOfNonexistentPerson = nonexistentPerson.getName();

        assertCommandFailure(new DeletePersonFromModuleCommand(validModuleCode, nameOfNonexistentPerson), model,
                Messages.MESSAGE_NO_SUCH_PERSON);
    }

    @Test
    public void execute_nonexistentModule_throwsCommandException() {
        Person validPerson = TypicalPersons.ELLE;
        Module nonexistentModule = new ModuleBuilder()
                .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK)
                .withPersons(new HashSet<>(Arrays.asList(validPerson)))
                .build();

        ModuleCode nonexistentModuleCode = nonexistentModule.getModuleCode();
        Name validName = validPerson.getName();

        assertCommandFailure(new DeletePersonFromModuleCommand(nonexistentModuleCode, validName), model,
                Messages.MESSAGE_NO_SUCH_MODULE);
    }
}

