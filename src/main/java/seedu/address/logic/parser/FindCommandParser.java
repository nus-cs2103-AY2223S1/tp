package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindCommandPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentClass;
import seedu.address.model.person.subject.Subject;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindNameCommand> {

    private static final Name DEFAULT_NAME = new Name("AMY");
    private static final StudentClass DEFAULT_CLASS = new StudentClass("1A");
    private static final Subject DEFAULT_SUBJECT = new Subject("MATH");

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        List<String> findCommandKeywords = new ArrayList<String>();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENTCLASS, PREFIX_SUBJECT);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name foundName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            findCommandKeywords.add(foundName.toString());
        }

        if (argMultimap.getValue(PREFIX_STUDENTCLASS).isPresent()) {
            StudentClass foundClass = ParserUtil.parseStudentClass(argMultimap.getValue(PREFIX_STUDENTCLASS)
                                                                              .get());
            findCommandKeywords.add(foundClass.toString());
        }

        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            Subject foundSubjects = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
            findCommandKeywords.add(foundSubjects.toString());
        }

        return new FindNameCommand(new FindCommandPredicate(findCommandKeywords));
    }

}
