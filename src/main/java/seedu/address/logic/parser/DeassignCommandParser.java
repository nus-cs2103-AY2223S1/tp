package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.logic.parser.ParserUtil.parseIndexes;
import static seedu.address.logic.parser.ParserUtil.parseUid;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeassignCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Uid;

/**
 * Parses input arguments and creates a new Assign object
 */
public class DeassignCommandParser implements Parser<DeassignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeassignCommand
     * and returns a DeassignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeassignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_UID, PREFIX_DATE_AND_SLOT_INDEX);

        Uid uid;

        if (argMultimap.getValue(PREFIX_UID).isPresent()) {
            uid = parseUid(argMultimap.getValue(PREFIX_UID).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        List<Index> indexList = parseIndexes(argMultimap.getAllValues(PREFIX_DATE_AND_SLOT_INDEX));

        return new DeassignCommand(uid, indexList);
    }

}
