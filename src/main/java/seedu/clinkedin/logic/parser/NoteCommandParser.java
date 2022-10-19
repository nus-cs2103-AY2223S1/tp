package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.commands.NoteCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Note;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class NoteCommandParser implements Parser<NoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * NoteCommand
     */
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NOTE);
        Index index;
        Note note;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE), ive);
        }
        note = new Note(argMultimap.getValue(PREFIX_NOTE).orElse(""));

        return new NoteCommand(index, note);
    }
}
