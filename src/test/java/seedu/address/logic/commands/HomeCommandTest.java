package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import seedu.address.testutil.TypicalAddressBook;

public class HomeCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalAddressBook.getTypicalAddressBook(), new UserPrefs());
        model.setHomeStatus(false);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new HomeCommand(), model, HomeCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(expectedModel.getHomeStatus(), model.getHomeStatus());
    }

    @Test
    public void execute_moduleAndPersonListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new HomeCommand(), model, HomeCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(expectedModel.getHomeStatus(), model.getHomeStatus());
    }
}
