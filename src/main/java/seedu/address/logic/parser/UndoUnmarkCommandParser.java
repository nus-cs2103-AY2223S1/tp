package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UndoUnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Uid;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class UndoUnmarkCommandParser implements Parser<UndoUnmarkCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the
     * UndoUnmarkCommand
     * and returns an UndoUnmarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoUnmarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_UID, PREFIX_DATE_AND_SLOT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_UID, PREFIX_DATE_AND_SLOT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoUnmarkCommand.MESSAGE_USAGE));
        }
        Uid uid = ParserUtil.parseUid(argMultimap.getValue(PREFIX_UID).get());
        Index dateSlotIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DATE_AND_SLOT_INDEX).get());

        return new UndoUnmarkCommand(uid, dateSlotIndex);
    }
}
