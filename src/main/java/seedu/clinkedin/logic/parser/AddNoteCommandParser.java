package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.function.BinaryOperator;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.commands.AddNoteCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Note;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddNoteCommand
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NOTE);
        Index index;
        Note note;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddNoteCommand.MESSAGE_USAGE), ive);
        }
        BinaryOperator<String> combiner = (x, y) -> x + "\n" + y;
        note = ParserUtil.parseNote(argMultimap.getAllValues(PREFIX_NOTE).stream().reduce(combiner).get());
        return new AddNoteCommand(index, note);
    }
}
