package seedu.phu.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import seedu.phu.logic.commands.CommandResult;
import seedu.phu.logic.commands.HelpCommand;
import seedu.phu.logic.commands.ListCommand;
import seedu.phu.logic.commands.exceptions.CommandException;

public class CommandBoxTest extends GuiUnitTest {
    private static final String CHILD_ID = "#commandTextField"; // id to handle fxml query
    private static final String INVALID_COMMAND = "invalid command";

    private CommandBox commandBox;
    private TextField childTextField;

    @BeforeEach
    public void setUp() {
        commandBox = new CommandBox(commandText -> {
            if (commandText.equals(INVALID_COMMAND)) {
                throw new CommandException("invalid");
            }

            return new CommandResult("valid");
        });
        childTextField = getChildNode(commandBox.getRoot(), CHILD_ID);
        assert childTextField != null; // make sure that childTextField is not null

        uiPartExtension.setUiPart(commandBox);
        robot.clickOn(childTextField);
    }

    @Test
    public void inputCommand_valid_commandErased() {
        executeInput(HelpCommand.COMMAND_WORD);
        checkCurrentDisplay("");
    }

    @Test
    public void inputCommand_invalid_commandStillExist() {
        executeInput(INVALID_COMMAND);
        checkCurrentDisplay(INVALID_COMMAND);
    }

    @Test
    public void getPreviousCommandInHistory_noCommandPreviously_success() {
        robot.push(KeyCode.UP);
        checkCurrentDisplay("");
    }

    @Test
    public void getPreviousCommandInHistory_commandExists_success() {
        // setup
        executeInput(HelpCommand.COMMAND_WORD);
        executeInput(ListCommand.COMMAND_WORD);
        executeInput(ListCommand.COMMAND_WORD);

        robot.push(KeyCode.UP);
        checkCurrentDisplay(ListCommand.COMMAND_WORD);

        robot.push(KeyCode.UP);
        checkCurrentDisplay(HelpCommand.COMMAND_WORD);
    }

    @Test
    public void getNextCommandInHistory_noCommandInHistory_success() {
        robot.push(KeyCode.UP);
        checkCurrentDisplay("");
    }

    @Test
    public void getNextCommandInHistory_commandExists_success() {
        // setup
        executeInput(HelpCommand.COMMAND_WORD);
        executeInput(ListCommand.COMMAND_WORD);
        robot.push(KeyCode.UP);
        robot.push(KeyCode.UP);

        robot.push(KeyCode.DOWN);
        checkCurrentDisplay(ListCommand.COMMAND_WORD);

        robot.push(KeyCode.DOWN);
        checkCurrentDisplay(ListCommand.COMMAND_WORD);
    }

    private void executeInput(String input) {
        robot.write(input);
        robot.push(KeyCode.ENTER);
    }

    private void checkCurrentDisplay(String expectedText) {
        assertEquals(expectedText, childTextField.getText());
        assertEquals(expectedText.length(), childTextField.getCaretPosition());
    }
}
