package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.ArrayList;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindCommandPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentClass;
import seedu.address.model.person.subject.Subject;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Name DEFAULT_NAME = new Name("TESTNAME");
    private static final StudentClass DEFAULT_CLASS = new StudentClass("0A");
    private static final Subject DEFAULT_SUBJECT = new Subject("TESTSUBJECT");

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        Name foundName = DEFAULT_NAME;
        StudentClass foundClass = DEFAULT_CLASS;
        Subject foundSubjects = DEFAULT_SUBJECT;

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENTCLASS, PREFIX_SUBJECT);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            foundName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }

        if (argMultimap.getValue(PREFIX_STUDENTCLASS).isPresent()) {
            foundClass = ParserUtil.parseStudentClass(argMultimap.getValue(PREFIX_STUDENTCLASS).get());
        }

        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            foundSubjects = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        }

        ArrayList<String> foundArgs = new ArrayList<>();
        if (foundName != DEFAULT_NAME) {
            foundArgs.add(foundName.toString());
        }
        if (foundClass != DEFAULT_CLASS) {
            foundArgs.add(foundClass.toString());
        }
        if (foundSubjects != DEFAULT_SUBJECT) {
            foundArgs.add(foundSubjects.toString());
        }
        return new FindCommand(new FindCommandPredicate(foundArgs));
    }

}
