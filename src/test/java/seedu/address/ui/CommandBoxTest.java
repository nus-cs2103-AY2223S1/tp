package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guitests.guihandles.CommandBoxHandle;
import javafx.collections.ObservableList;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;

// @@author pyokagan-reused
// Test code adapted from AddressBook Level 4 https://se-education.org/addressbook-level4/ with modifications
public class CommandBoxTest extends GuiUnitTest {

    private static final String COMMAND_THAT_SUCCEEDS = ListCommand.COMMAND_WORD;
    private static final String COMMAND_THAT_FAILS = "invalid command";

    private ArrayList<String> defaultStyleOfCommandBox;
    private ArrayList<String> errorStyleOfCommandBox;

    private CommandBoxHandle commandBoxHandle;

    @BeforeEach
    public void setUp() {
        CommandBox commandBox = new CommandBox(commandText -> {
            if (commandText.equals(COMMAND_THAT_SUCCEEDS)) {
                return new CommandResult("Command successful");
            }
            throw new CommandException("Command failed");
        });
        assert getChildNode(commandBox.getRoot(), "#test") instanceof SplitPane;
        SplitPane s = (SplitPane) getChildNode(commandBox.getRoot(), "#test");
        commandBoxHandle = new CommandBoxHandle(getChildNode(s.getItems().get(0),
                CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        uiPartExtension.setUiPart(commandBox);

        defaultStyleOfCommandBox = new ArrayList<>(commandBoxHandle.getStyleClass());

        errorStyleOfCommandBox = new ArrayList<>(defaultStyleOfCommandBox);
        errorStyleOfCommandBox.add(CommandBox.ERROR_STYLE_CLASS);
    }

    @Test
    public void commandBox_startingWithSuccessfulCommand() {
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
    }

    @Test
    public void commandBox_startingWithFailedCommand() {
        assertBehaviorForFailedCommand();
        assertBehaviorForSuccessfulCommand();

        // verify that style is changed correctly even after multiple consecutive failed commands
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
        assertBehaviorForFailedCommand();
    }

    @Test
    public void commandBox_handleKeyPress() {
        commandBoxHandle.run(COMMAND_THAT_FAILS);
        assertEquals(errorStyleOfCommandBox, commandBoxHandle.getStyleClass());
        guiRobot.push(KeyCode.ESCAPE);
        assertEquals(errorStyleOfCommandBox, commandBoxHandle.getStyleClass());

        guiRobot.push(KeyCode.A);
        assertEquals(defaultStyleOfCommandBox, commandBoxHandle.getStyleClass());
    }

    /**
     * Runs a command that fails, then verifies that <br>
     *      - the text remains <br>
     *      - the command box's style is the same as {@code errorStyleOfCommandBox}.
     */
    private void assertBehaviorForFailedCommand() {
        commandBoxHandle.run(COMMAND_THAT_FAILS);
        assertEquals(COMMAND_THAT_FAILS, commandBoxHandle.getInput());
        assertEquals(errorStyleOfCommandBox, commandBoxHandle.getStyleClass());
    }

    /**
     * Runs a command that succeeds, then verifies that <br>
     *      - the text is cleared <br>
     *      - the command box's style is the same as {@code defaultStyleOfCommandBox}.
     */
    private void assertBehaviorForSuccessfulCommand() {
        commandBoxHandle.run(COMMAND_THAT_SUCCEEDS);
        String t = commandBoxHandle.getInput();
        ObservableList<String> temp = commandBoxHandle.getStyleClass();
        assertEquals("", t);
        assertEquals(defaultStyleOfCommandBox, temp);
    }
}
// @@author
