package seedu.watson.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_STUDENTCLASS;
import static seedu.watson.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.ArrayList;

import seedu.watson.logic.commands.FindCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.FindCommandPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        System.out.println(args);
        requireNonNull(args);
        ArrayList<String> findCommandKeywords = new ArrayList<>();
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENTCLASS, PREFIX_SUBJECT);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            System.out.println("name");
            String foundName = String.join(" ", argMultimap.getAllValues(PREFIX_NAME));
            findCommandKeywords.add(0, foundName);
        } else {
            findCommandKeywords.add(0, "");
        }

        if (argMultimap.getValue(PREFIX_STUDENTCLASS).isPresent()) {
            String foundClass = String.join(" ", argMultimap.getAllValues(PREFIX_STUDENTCLASS));
            findCommandKeywords.add(1, foundClass);
        } else {
            findCommandKeywords.add(1, "");
        }

        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            String foundSubjects = String.join(" ", argMultimap.getAllValues(PREFIX_SUBJECT));
            findCommandKeywords.add(2, foundSubjects);
        } else {
            findCommandKeywords.add(2, "");
        }

        if (findCommandKeywords.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new FindCommandPredicate(findCommandKeywords));
    }

}
