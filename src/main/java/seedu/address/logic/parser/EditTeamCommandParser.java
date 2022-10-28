package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Name;

/**
 * Edit team command parser class
 */
public class EditTeamCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the UnAssignMemberCommand
     * and returns a UnAssignMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTeamCommand parse(String args) throws ParseException {

        requireNonNull(args);
        Index teamIndex;
        Name newTeamName;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TEAM_INDEX, PREFIX_TEAM_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAM_INDEX, PREFIX_TEAM_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamCommand.MESSAGE_USAGE));
        }

        try {
            teamIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());
            newTeamName = ParserUtil.parseTeamName(argMultimap.getValue(PREFIX_TEAM_NAME).get());
        } catch (ParseException p) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamCommand.MESSAGE_USAGE), p);
        }

        return new EditTeamCommand(teamIndex, newTeamName);
    }
}
