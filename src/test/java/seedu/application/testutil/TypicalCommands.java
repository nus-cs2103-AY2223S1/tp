package seedu.application.testutil;

import static seedu.application.testutil.TypicalApplications.GOOGLE;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.ClearCommand;
import seedu.application.logic.commands.Command;
import seedu.application.logic.commands.DeleteCommand;
import seedu.application.logic.commands.RedoCommand;
import seedu.application.logic.commands.UndoCommand;

/**
 * A utility class containing a list of {@code Command} objects to be used in tests.
 */
public class TypicalCommands {
    public static final Command ADD_COMMAND = new AddCommand(new ApplicationBuilder(GOOGLE).build());
    public static final Command CLEAR_COMMAND = new ClearCommand();
    public static final Command DELETE_COMMAND = new DeleteCommand(INDEX_FIRST_APPLICATION);
    public static final Command REDO_COMMAND = new RedoCommand();
    public static final Command UNDO_COMMAND = new UndoCommand();
}
