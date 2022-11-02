package seedu.rc4hdb.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import seedu.rc4hdb.ui.history.CommandHistoryParser;

public class CommandHistoryTest {

    private static final String LIST_COMMAND = "list";
    private static final String FIND_COMMAND = "find Captain America";
    private static final String ADD_COMMAND = "add n/Beth p/91234567 e/Beth@example.com r/03-01 g/F h/D m/A1234123A";
    private static final String EMPTY_TEXT = "";
    private CommandHistoryParser commandHistoryParser;

    @BeforeEach
    public void setUp() {
        this.commandHistoryParser = CommandHistoryParser.getInstance();
    }

    @Test
    public void parserSingletonTest() {
        assertEquals(this.commandHistoryParser, CommandHistoryParser.getInstance());
    }

    @Test
    public void addRetrieveSingleCommand() {
        this.commandHistoryParser.parse(LIST_COMMAND);
        String commandText = commandHistoryParser.parse(KeyCode.UP).execute();
        assertEquals(LIST_COMMAND, commandText);
    }

    @Test
    public void multipleCommandPreserveTraverseOrder() {
        this.commandHistoryParser.parse(FIND_COMMAND);
        this.commandHistoryParser.parse(ADD_COMMAND);

        // forward traversal - expects commands in reverse order of input
        assertEquals(ADD_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute()); // most recent ADD_COMMAND
        assertEquals(FIND_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute()); // second most recent FIND_COMMAND
        assertEquals(LIST_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute()); // oldest LIST_COMMAND

        // backward traversal - expects most recent traversed command
        assertEquals(FIND_COMMAND, commandHistoryParser.parse(KeyCode.DOWN).execute()); // recent traversed LIST_COMMAND
        assertEquals(ADD_COMMAND, commandHistoryParser.parse(KeyCode.DOWN).execute()); // oldest traversed ADD_COMMAND

        // mixed order traversal - when going up followed by down, a double down tap is required as the first sets the
        // input cursor to back of text. But if you're at the top of the stack, only one down tap is required
        assertEquals(ADD_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute());
        assertEquals(FIND_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute());
        assertEquals(LIST_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute());
        assertEquals(FIND_COMMAND, commandHistoryParser.parse(KeyCode.DOWN).execute());
        assertEquals(ADD_COMMAND, commandHistoryParser.parse(KeyCode.DOWN).execute());


    }

    @Test
    public void traverseToOldestAndRecentCommand() {
        commandHistoryParser.parse(KeyCode.UP).execute(); // hits add command
        commandHistoryParser.parse(KeyCode.UP).execute(); // hits find command
        assertEquals(LIST_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute()); // hits the oldest command
        assertEquals(LIST_COMMAND, commandHistoryParser.parse(KeyCode.UP).execute()); // text remains the oldest command

        commandHistoryParser.parse(KeyCode.DOWN).execute();
        commandHistoryParser.parse(KeyCode.DOWN).execute();
        assertEquals(EMPTY_TEXT, commandHistoryParser.parse(KeyCode.DOWN).execute()); // hits empty text
        assertEquals(EMPTY_TEXT, commandHistoryParser.parse(KeyCode.DOWN).execute()); // hits empty text
    }


}
