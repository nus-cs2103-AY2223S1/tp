package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.note.Title;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     *
     * @param args Argument to be parsed.
     * @return AddNoteCommand to be executed.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NOTES_TITLE, PREFIX_NOTES_CONTENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NOTES_TITLE, PREFIX_NOTES_CONTENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NOTES_TITLE).get());
        Content content = ParserUtil.parseContent(argMultimap.getValue(PREFIX_NOTES_CONTENT).get());


        Note note = new Note(title, content);

        return new AddNoteCommand(note);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
