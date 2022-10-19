package seedu.rc4hdb.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import seedu.rc4hdb.ui.history.CommandHistory;

public class CommandHistoryTest {

    private static final String LIST_COMMAND = "list";
    private static final String FIND_COMMAND = "find Captain America";
    private static final String ADD_COMMAND = "add n/Beth p/91234567 e/Beth@example.com r/03-01 g/F h/D m/A1234123A";
    private static final String EMPTY_TEXT = "";

    @Test
    public void addRetrieveSingleCommand() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add(LIST_COMMAND);
        String output = commandHistory.handle(KeyCode.UP);
        assertEquals(LIST_COMMAND, output);
    }


    @Test
    public void multipleCommandPreserveTraverseOrder() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add(LIST_COMMAND);
        commandHistory.add(FIND_COMMAND);
        commandHistory.add(ADD_COMMAND);

        // forward traversal - expects commands in reverse order of input
        assertEquals(ADD_COMMAND, commandHistory.handle(KeyCode.UP)); // most recent ADD_COMMAND
        assertEquals(FIND_COMMAND, commandHistory.handle(KeyCode.UP)); // second most recent FIND_COMMAND
        assertEquals(LIST_COMMAND, commandHistory.handle(KeyCode.UP)); // oldest LIST_COMMAND

        // backward traversal - expects most recent traversed command
        assertEquals(FIND_COMMAND, commandHistory.handle(KeyCode.DOWN)); // most recent traversed LIST_COMMAND
        assertEquals(ADD_COMMAND, commandHistory.handle(KeyCode.DOWN)); // oldest traversed ADD_COMMAND

        // mixed order traversal - when going up followed by down, a double down tap is required as the first sets the
        // input cursor to back of text. But if you're at the top of the stack, only one down tap is required
        assertEquals(ADD_COMMAND, commandHistory.handle(KeyCode.UP));
        assertEquals(FIND_COMMAND, commandHistory.handle(KeyCode.UP));
        assertEquals(LIST_COMMAND, commandHistory.handle(KeyCode.UP));
        assertEquals(FIND_COMMAND, commandHistory.handle(KeyCode.DOWN));
        assertEquals(ADD_COMMAND, commandHistory.handle(KeyCode.DOWN));
    }

    @Test
    public void traverseToOldestAndRecentCommand() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add(LIST_COMMAND);
        commandHistory.add(FIND_COMMAND);

        commandHistory.handle(KeyCode.UP); // hits find command
        assertEquals(LIST_COMMAND, commandHistory.handle(KeyCode.UP)); // hits the oldest command
        assertEquals(LIST_COMMAND, commandHistory.handle(KeyCode.UP)); // text return remains the oldest command

        commandHistory.handle(KeyCode.DOWN); // hits "most recent" find command
        assertEquals(EMPTY_TEXT, commandHistory.handle(KeyCode.DOWN)); // hits empty text
        assertEquals(EMPTY_TEXT, commandHistory.handle(KeyCode.DOWN)); // hits empty text
    }

    @Test
    public void noHistory() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals(EMPTY_TEXT, commandHistory.handle(KeyCode.UP)); // hits the oldest command
        assertEquals(EMPTY_TEXT, commandHistory.handle(KeyCode.DOWN)); // hits empty text
    }





}
