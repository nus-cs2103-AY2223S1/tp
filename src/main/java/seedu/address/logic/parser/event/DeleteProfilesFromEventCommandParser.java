package seedu.address.logic.parser.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.DeleteProfilesFromEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteProfilesFromEventCommand object
 */
public class DeleteProfilesFromEventCommandParser implements Parser<DeleteProfilesFromEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProfilesFromEventCommand
     * and returns an DeleteProfilesFromEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteProfilesFromEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OPTION, PREFIX_PROFILE);
        Index index;
        Set<Index> indexList;

        try {
            index = ParserUtil.parseIndex(argMultimap.getOptionArgs());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_INDEX_FORMAT,
                    DeleteProfilesFromEventCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getPreamble().isEmpty() || argMultimap.getOptionArgs().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteProfilesFromEventCommand.MESSAGE_USAGE));
        }

        try {
            indexList = ParserUtil.parseIndexes(argMultimap.getAllValues(PREFIX_PROFILE));
        } catch (ParseException pe) {
            throw new ParseException(DeleteProfilesFromEventCommand.MESSAGE_INVALID_PROFILE_INDEX);
        }

        if (indexList.isEmpty()) {
            throw new ParseException(DeleteProfilesFromEventCommand.MESSAGE_ATTENDEES_NOT_DELETED);
        }

        return new DeleteProfilesFromEventCommand(index, indexList);
    }
}
