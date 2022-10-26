package seedu.address.logic.parser.task;

import seedu.address.logic.commands.task.ListTaskProjectsCommand;

/**
 * Creates a new EditTaskCommand object
 */
public class ListTaskProjectsCommandParser {
    /**
     * No arguments taken, return ListTaskProjectsCommand object
     * for execution.
     */
    public ListTaskProjectsCommand parse() {
        return new ListTaskProjectsCommand();
    }
}
