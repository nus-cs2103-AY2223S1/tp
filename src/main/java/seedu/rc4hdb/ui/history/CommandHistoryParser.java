package seedu.rc4hdb.ui.history;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.input.KeyCode;

/**
 * The component responsible for logging valid commands, and parsing key press actions solely
 * for viewing the command history.
 */
public class CommandHistoryParser {

    private static Logger logger = Logger.getLogger("CommandHistoryParser");
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
        assert(commandHistoryParser != null);
        return commandHistoryParser;
    }

    /**
     * Parses the key press during a key event.
     * @param key the key that is pressed by the user.
     * @return CommandHistory object for the specific key press.
     */
    public CommandHistory parse(KeyCode key) {
        logger.log(Level.INFO, "Parsing user key press.");
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
        logger.log(Level.INFO, "Adding command to history stack");
        assert(commandText != null);
        this.forwardHistory.add(commandText);
    }

}
