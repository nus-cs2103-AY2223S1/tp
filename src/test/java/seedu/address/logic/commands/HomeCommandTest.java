package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalPersons;

public class HomeCommandTest {
    private Model moduleModel;
    private Model personModel;
    private Model expectedModuleModel;
    private Model expectedPersonModel;

    @BeforeEach
    public void setUp() {
        moduleModel = new ModelManager(TypicalModules.getTypicalAddressBook(), new UserPrefs());
        personModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        expectedModuleModel = new ModelManager(moduleModel.getAddressBook(), new UserPrefs());
        expectedPersonModel = new ModelManager(personModel.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_moduleListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new HomeCommand(), moduleModel, HomeCommand.MESSAGE_SUCCESS, expectedModuleModel);
    }

    @Test
    public void execute_personListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new HomeCommand(), personModel, HomeCommand.MESSAGE_SUCCESS, expectedPersonModel);
    }

    @Test
    public void execute_moduleListIsFiltered_showsEverything() {
        // To be added with filtered model
        showModuleAtIndex(moduleModel, INDEX_FIRST_MODULE);
        assertCommandSuccess(new HomeCommand(), moduleModel, HomeCommand.MESSAGE_SUCCESS, expectedModuleModel);
    }

    @Test
    public void execute_personListIsFiltered_showsSameList() {
        showPersonAtIndex(personModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(new HomeCommand(), personModel, HomeCommand.MESSAGE_SUCCESS, expectedPersonModel);
    }
}
