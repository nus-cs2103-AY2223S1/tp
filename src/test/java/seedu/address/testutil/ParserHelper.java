package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_COMPLETE_TASKS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_INCOMPLETE_TASKS_STR;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.Order;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Description;
import seedu.address.model.team.LinkName;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;
import seedu.address.model.team.TeamName;
import seedu.address.model.team.Url;

/**
 * Utility class that helps to retrieve fields in command objects.
 */
public class ParserHelper {

    public static <T> T getOption(Class<T> cls, String flag, Command command) {
        CommandLine.Model.CommandSpec commandSpec = CommandLine.Model.CommandSpec.forAnnotatedObject(command);
        T value = commandSpec.findOption(flag).getValue();
        return value;
    }

    public static <T> T getParameterPosition(Class<T> cls, int position, Command command) {
        CommandLine.Model.CommandSpec commandSpec = CommandLine.Model.CommandSpec.forAnnotatedObject(command);
        T value = commandSpec.positionalParameters().get(position).getValue();
        return value;
    }

}
