package modtrekt.logic.parser.tasks;

import static java.util.Objects.requireNonNull;
import static modtrekt.logic.parser.CliSyntax.FLAG_SHOW_ARCHIVED_TASKS;

import modtrekt.logic.commands.tasks.ListTasksCommand;
import modtrekt.logic.parser.ArgumentTokenizer;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * TODO: JAVADOC
 */
public class ListTasksCommandParser {
    /**
     * TODO: JAVADOC
     */
    public ListTasksCommand parse(String args) throws ParseException {
        requireNonNull(args);
        boolean areArchivedTasksShown = ArgumentTokenizer.isFlagPresent(args, FLAG_SHOW_ARCHIVED_TASKS);
        return new ListTasksCommand(areArchivedTasksShown);
    }
}
