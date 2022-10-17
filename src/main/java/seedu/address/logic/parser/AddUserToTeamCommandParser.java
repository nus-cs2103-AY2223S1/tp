package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USER;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.teams.AddUserToTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddUserToTeamCommand object
 */
public class AddUserToTeamCommandParser implements Parser<AddUserToTeamCommand> {
    @Override
    public AddUserToTeamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_USER, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_USER, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddUserToTeamCommand.MESSAGE_USAGE));
        }

        Index userIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_USER).get());
        Index grpIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_GROUP).get());

        return new AddUserToTeamCommand(userIndex, grpIndex);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
