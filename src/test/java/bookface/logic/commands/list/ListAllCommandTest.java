package bookface.logic.commands.list;

import static bookface.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bookface.logic.commands.CommandTestUtil;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;
import bookface.testutil.TypicalIndexes;

/**
 * Contains integration tests and unit tests for ListAllCommand.
 */
public class ListAllCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());
        expectedModel = new ModelManager(model.getBookFace(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showBookAtIndex(model, TypicalIndexes.INDEX_FIRST_BOOK);
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
