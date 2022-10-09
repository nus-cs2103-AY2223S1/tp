package bookface.logic.parser.delete;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import bookface.commons.core.Messages;
import bookface.logic.commands.delete.DeleteBookCommand;
import bookface.logic.parser.ArgumentMultimap;
import bookface.logic.parser.ArgumentTokenizer;
import bookface.logic.parser.ArgumentsParsable;
import bookface.logic.parser.Prefix;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.book.Author;
import bookface.model.book.Book;
import bookface.model.book.Title;

/**
 * Parses input arguments and creates the relevant new AddCommand object for the relevant entity to be added
 */
public class DeleteBookArgumentsParser implements ArgumentsParsable<DeleteBookCommand> {
    /**
     * Parses the given arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     */
    @Override
    public DeleteBookCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_AUTHOR) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteBookCommand.MESSAGE_USAGE));
        }

        Author author = new Author(argMultimap.getValue(PREFIX_AUTHOR).get());
        Title title = new Title(argMultimap.getValue(PREFIX_TITLE).get());

        Book book = new Book(title, author);
        return new DeleteBookCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
