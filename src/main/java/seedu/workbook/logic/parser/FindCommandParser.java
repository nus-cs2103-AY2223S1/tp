package seedu.workbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.workbook.logic.commands.FindCommand;
import seedu.workbook.logic.parser.exceptions.ParseException;
import seedu.workbook.model.internship.CompanyContainsKeywordsPredicate;
import seedu.workbook.model.internship.RoleContainsKeywordsPredicate;
import seedu.workbook.model.internship.StageContainsKeywordsPredicate;

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
        requireNonNull(args);


        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_ROLE,
                PREFIX_STAGE);

        int numberOfPrefixes = ArgumentTokenizer.numberOfPrefixes(args, PREFIX_COMPANY, PREFIX_ROLE,
                PREFIX_STAGE);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        } else if (numberOfPrefixes > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        } else if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            String arguments = argMultimap.getValue(PREFIX_COMPANY).get();
            if (arguments.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = arguments.split(" ");
            return new FindCommand(new CompanyContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else if (argMultimap.getValue(PREFIX_STAGE).isPresent()) {
            String arguments = argMultimap.getValue(PREFIX_STAGE).get();
            if (arguments.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = arguments.split(" ");
            return new FindCommand(new StageContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            String arguments = argMultimap.getValue(PREFIX_ROLE).get();
            if (arguments.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            String[] nameKeywords = arguments.split(" ");
            return new FindCommand(new RoleContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
