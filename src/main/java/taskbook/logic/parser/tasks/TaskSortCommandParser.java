package taskbook.logic.parser.tasks;

import taskbook.logic.commands.tasks.TaskSortAddedChronologicalCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.commands.tasks.TaskSortDescriptionAlphabeticalCommand;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.logic.parser.tasks.enums.SortTypes;

/**
 * Parses input arguments and creates a new TaskSortCommand
 */
public class TaskSortCommandParser implements Parser<TaskSortCommand> {
    public final String unrecognisedArgs = "Unrecognised sorting flag.";

    @Override
    public TaskSortCommand parse(String userInput) throws ParseException {
        SortTypes flag = SortTypes.parse(userInput);
        switch (flag) {
        case DESC_ALPHA:
            return new TaskSortDescriptionAlphabeticalCommand();
        case DESC_ADDED_CHRON:
            return new TaskSortAddedChronologicalCommand();
        default:
            throw new ParseException(unrecognisedArgs);
        }
    }
}
