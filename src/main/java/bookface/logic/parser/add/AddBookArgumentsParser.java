package bookface.logic.parser.add;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import bookface.commons.core.Messages;
import bookface.logic.commands.add.AddBookCommand;
import bookface.logic.parser.ArgumentMultimap;
import bookface.logic.parser.ArgumentTokenizer;
import bookface.logic.parser.ArgumentsParsable;
import bookface.logic.parser.ParserUtil;
import bookface.logic.parser.Prefix;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.book.Author;
import bookface.model.book.Book;
import bookface.model.book.Title;

/**
 * Parses input arguments and creates the relevant new AddCommand object for the relevant entity to be added
 */
public class AddBookArgumentsParser implements ArgumentsParsable<AddBookCommand> {
    @Override
    public AddBookCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR);

        //todo might be better to rewrite these checks using Optional.isPresent and Optional.orElseThrow
        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_AUTHOR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddBookCommand.MESSAGE_USAGE));
        }

        Author author = ParserUtil.parseAuthor(argMultimap.getValue(PREFIX_AUTHOR).get());
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());

        Book book = new Book(title, author);
        return new AddBookCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
