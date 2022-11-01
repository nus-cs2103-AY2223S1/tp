package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithAssociations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
 * Contains integration tests (interaction with the Model) for {@code AddPersonToModuleCommand}.
 */
public class AddPersonToModuleCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithAssociations(), new UserPrefs());
    }

    @Test
    public void execute_addPersonToModuleAtHome_success() {
        Module validModule = TypicalModules.CS2106_WITH_ALICE_BENSON;
        Person validPerson = TypicalPersons.ELLE;

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name validName = validPerson.getName();

        Set<Person> expectedPersons = new HashSet<>(TypicalModules.CS2106_WITH_ALICE_BENSON.getPersons());
        expectedPersons.add(validPerson);
        Module expectedModule = new ModuleBuilder(validModule).withPersons(expectedPersons).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setModule(validModule, expectedModule);

        assertCommandSuccess(new AddPersonToModuleCommand(validModuleCode, validName), model,
                String.format(AddPersonToModuleCommand.MESSAGE_ADD_PERSON_TO_MODULE_SUCCESS,
                        validModuleCode, validName),
                expectedModel);
    }

    @Test
    public void execute_addPersonToModuleNotInFilteredList_throwsCommandException() {
        Module validModule = TypicalModules.CS2106_WITH_ALICE_BENSON;
        Person validPerson = TypicalPersons.ELLE;

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name validName = validPerson.getName();

        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ZERO_MODULE);

        assertCommandFailure(new AddPersonToModuleCommand(validModuleCode, validName), model,
                Messages.MESSAGE_NO_SUCH_MODULE);
    }

    @Test
    public void execute_addPersonNotInFilteredListToModule_throwsCommandException() {
        Module validModule = TypicalModules.CS2106_WITH_ALICE_BENSON;
        Person validPerson = TypicalPersons.ELLE;

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name validName = validPerson.getName();

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ZERO_PERSON);

        assertCommandFailure(new AddPersonToModuleCommand(validModuleCode, validName), model,
                Messages.MESSAGE_NO_SUCH_PERSON);
    }

    @Test
    public void execute_personAlreadyAddedToModule_throwsCommandException() {
        Module validModule = TypicalModules.CS2106_WITH_ALICE_BENSON;
        Person personAlreadyAddedToModule = validModule.getPersons().stream().findFirst().get();

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name nameOfPersonAlreadyAdded = personAlreadyAddedToModule.getName();

        assertCommandFailure(new AddPersonToModuleCommand(validModuleCode, nameOfPersonAlreadyAdded), model,
                String.format(AddPersonToModuleCommand.MESSAGE_PERSON_ALREADY_EXISTS_IN_MODULE,
                        validModuleCode, nameOfPersonAlreadyAdded));
    }

    @Test
    public void execute_nonexistentPerson_throwsCommandException() {
        Module validModule = TypicalModules.CS2106_WITH_ALICE_BENSON;
        Person nonexistentPerson = TypicalPersons.AMY;

        ModuleCode validModuleCode = validModule.getModuleCode();
        Name nameOfNonexistentPerson = nonexistentPerson.getName();

        assertCommandFailure(new AddPersonToModuleCommand(validModuleCode, nameOfNonexistentPerson), model,
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

        assertCommandFailure(new AddPersonToModuleCommand(nonexistentModuleCode, validName), model,
                Messages.MESSAGE_NO_SUCH_MODULE);
    }

}
