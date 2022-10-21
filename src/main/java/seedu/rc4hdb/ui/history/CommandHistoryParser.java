package seedu.rc4hdb.ui.history;

import javafx.scene.input.KeyCode;

/**
 * The component responsible for logging valid commands, and parsing key press actions solely
 * for viewing the command history.
 */
public class CommandHistoryParser {

    private static CommandHistoryParser commandHistoryParser = null;
    private static ForwardHistory forwardHistory;
    private static BackwardHistory backwardHistory;

    private CommandHistoryParser() {
        this.forwardHistory = new ForwardHistory();
        this.backwardHistory = new BackwardHistory();
    }

    /**
     * Factory constructor for the {@code CommandHistoryParser} to prevent
     * multiple instances of the parser and stacks.
     * @return the same instance of CommandHistoryParser every time.
     */
    public static CommandHistoryParser getInstance() {
        if (commandHistoryParser == null) {
            commandHistoryParser = new CommandHistoryParser();
        }
        return commandHistoryParser;
    }

    /**
     * Parses the key press during a key event.
     * @param key the key that is pressed by the user.
     * @return CommandHistory object for the specific key press.
     */
    public CommandHistory parse(KeyCode key) {
        if (key == KeyCode.DOWN) {
            return this.backwardHistory;
        } else {
            return this.forwardHistory;
        }
    }

    /**
     * Overloaded method to store any valid command from the user input.
     * @param commandText user input.
     */
    public void parse(String commandText) {
        this.forwardHistory.add(commandText);
    }

}
