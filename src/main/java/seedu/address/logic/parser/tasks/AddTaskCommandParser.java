package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.tasks.AddTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attribute.Name;
import seedu.address.model.task.Task;

// @@author connlim

/**
 * Parses input arguments and creates a new TaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    @Override
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DESCRIPTION) || !argMultimap.getPreamble()
            .isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_TITLE).get();
        if (name.length() == 0) {
            throw new ParseException(String.format(MESSAGE_EMPTY_NAME, AddTaskCommand.MESSAGE_USAGE));
        }
        Name parsedName = ParserUtil.parseName(name);
        String address = argMultimap.getValue(PREFIX_DESCRIPTION).get();

        Task task = new Task(parsedName.fullName, address);

        return new AddTaskCommand(task);
    }

}
