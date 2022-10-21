package taskbook.logic;

/**
 * API of the CommandHistory component.
 */
public interface CommandHistory {

    /**
     * Gets the previous command in the history.
     * Returns null if there is no previous command.
     *
     * @return Previous command or null.
     */
    String getPreviousCommand();

    /**
     * Gets the next command in the history.
     * Returns null if there is no next command.
     *
     * @return Next command or null.
     */
    String getNextCommand();

    /**
     * Adds the given command to the history.
     *
     * @param command The command to be added.
     */
    void addCommand(String command);
}
