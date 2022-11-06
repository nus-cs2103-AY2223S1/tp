package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_PATIENT_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditTagCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditTagCommand object.
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {
    /**
     * Parses the given arguments in the context of the EditTagCommand
     * and returns an EditTagCommand object for execution.
     *
     * @param args The given string of arguments.
     * @return EditTagCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTagCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIXES_PATIENT_ALL);

        if (!ParserUtil.parametersOnlyContains(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTagCommand.MESSAGE_USAGE));
        }

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());
            Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).orElseThrow());

            return new EditTagCommand(indices.get(0), indices.get(1), tag);
        } catch (NoSuchElementException nse) {
            // Handles missing prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTagCommand.MESSAGE_USAGE));
        }
    }
}
