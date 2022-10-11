package modtrekt.logic.parser.tasks;

import static java.util.Objects.requireNonNull;
import static modtrekt.logic.parser.CliSyntax.FLAG_SHOW_ARCHIVED_TASKS;

import modtrekt.logic.commands.tasks.ListTasksCommand;
import modtrekt.logic.parser.ArgumentTokenizer;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * Parser for a command string input for a ListTasksCommand object.
 */
public class ListTasksCommandParser {
    /**
     * Returns a new ListTasksCommand object parsed from the given command string.
     */
    public ListTasksCommand parse(String args) throws ParseException {
        requireNonNull(args);
        boolean areArchivedTasksShown = ArgumentTokenizer.isFlagPresent(args, FLAG_SHOW_ARCHIVED_TASKS);
        return new ListTasksCommand(areArchivedTasksShown);
    }
}
