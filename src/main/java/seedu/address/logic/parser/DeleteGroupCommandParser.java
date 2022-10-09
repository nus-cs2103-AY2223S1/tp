package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.HashSet;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/** Parses user input in context of DeleteGroupCommand */
public class DeleteGroupCommandParser implements Parser<DeleteGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns a DeleteGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE));
        }

        GroupName name = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_GROUP).get());

        Group group = new Group(name, new HashSet<>());

        return new DeleteGroupCommand(group);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
