package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.phu.logic.commands.CopyCommand.MESSAGE_SUCCESS;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEXES_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.phu.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Index;
import seedu.phu.commons.core.index.Indexes;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.Internship;

public class CopyCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void equals() throws ParseException {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        List<Internship> forPredicate = new ArrayList<>();
        forPredicate.add(firstInternship);

        CopyCommand firstCopyCommand = new CopyCommand(INDEXES_FIRST_INTERNSHIP);
        CopyCommand secondCopyCommand = new CopyCommand(INDEXES_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(firstCopyCommand.equals(firstCopyCommand));

        // same categories and keywords -> returns true
        CopyCommand firstCopyCommandCopy = new CopyCommand(INDEXES_FIRST_INTERNSHIP);
        assertTrue(firstCopyCommand.equals(firstCopyCommandCopy));

        // different target index -> returns false
        assertFalse(firstCopyCommand.equals(secondCopyCommand));
    }

    @Test
    public void execute_validMessage_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        assertCommandSuccess(new CopyCommand(INDEXES_FIRST_INTERNSHIP), model,
                commandHistory, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws IOException, UnsupportedFlavorException {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        CopyCommand copyCommand = new CopyCommand(INDEXES_FIRST_INTERNSHIP);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        assertCommandSuccess(copyCommand, model, commandHistory, expectedMessage, expectedModel);

        // Object in Clipboard::getContents is not currently used
        // https://docs.oracle.com/javase/7/docs/api/java/awt/datatransfer/Clipboard.html#getContents(java.lang.Object)
        String currentContents = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
        String toCopy = firstInternship.toString();

        assertEquals(toCopy, currentContents);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        CopyCommand copyCommand = new CopyCommand(new Indexes(Set.of(outOfBoundIndex)));

        assertCommandFailure(copyCommand, model, commandHistory, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws IOException, UnsupportedFlavorException {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        showInternshipAtIndex(expectedModel, INDEX_FIRST_INTERNSHIP);

        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        CopyCommand copyCommand = new CopyCommand(INDEXES_FIRST_INTERNSHIP);
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        assertCommandSuccess(copyCommand, model, commandHistory, expectedMessage, expectedModel);

        // Object in Clipboard::getContents is not currently used
        // https://docs.oracle.com/javase/7/docs/api/java/awt/datatransfer/Clipboard.html#getContents(java.lang.Object)
        String currentContents = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
        String toCopy = firstInternship.toString();

        assertEquals(toCopy, currentContents);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        CopyCommand copyCommand = new CopyCommand(new Indexes(Set.of(outOfBoundIndex)));

        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getInternshipList().size());

        assertCommandFailure(copyCommand, model, commandHistory, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }
}
