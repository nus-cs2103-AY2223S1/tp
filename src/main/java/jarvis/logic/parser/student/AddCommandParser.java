package jarvis.logic.parser.student;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import jarvis.logic.commands.student.AddCommand;
import jarvis.logic.parser.ArgumentMultimap;
import jarvis.logic.parser.ArgumentTokenizer;
import jarvis.logic.parser.Parser;
import jarvis.logic.parser.ParserUtil;
import jarvis.logic.parser.Prefix;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.student.Name;
import jarvis.model.student.Student;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Student student = new Student(name);

        return new AddCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
