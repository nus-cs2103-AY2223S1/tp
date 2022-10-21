package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Arrays;

import tuthub.logic.commands.FindByEmailCommand;
import tuthub.logic.commands.FindByModuleCommand;
import tuthub.logic.commands.FindByNameCommand;
import tuthub.logic.commands.FindByPhoneCommand;
import tuthub.logic.commands.FindByPrefixCommand;
import tuthub.logic.commands.FindByRatingCommand;
import tuthub.logic.commands.FindByStudentIdCommand;
import tuthub.logic.commands.FindByTagCommand;
import tuthub.logic.commands.FindByTeachingNominationCommand;
import tuthub.logic.commands.FindByYearCommand;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.EmailContainsKeywordsPredicate;
import tuthub.model.tutor.ModuleContainsKeywordsPredicate;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.model.tutor.PhoneContainsKeywordsPredicate;
import tuthub.model.tutor.RatingContainsKeywordsPredicate;
import tuthub.model.tutor.StudentIdContainsKeywordsPredicate;
import tuthub.model.tutor.TagContainsKeywordsPredicate;
import tuthub.model.tutor.TeachingNominationContainKeywordsPredicate;
import tuthub.model.tutor.YearContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindByPrefixCommand object.
 */
public class FindByPrefixCommandParser implements Parser<FindByPrefixCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByPrefixCommand
     * and returns a FindByPrefixCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByPrefixCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_MODULE,
                        PREFIX_YEAR, PREFIX_STUDENTID, PREFIX_TEACHINGNOMINATION, PREFIX_RATING, PREFIX_TAG);

        if (!argumentMultimap.getPreamble().isEmpty() || argumentMultimap.getArgMultimapSize() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByPrefixCommand.MESSAGE_USAGE));
        }

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_NAME).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByNameCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedKeywords.split("\\s+");

            return new FindByNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_PHONE).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByPhoneCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_EMAIL).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByEmailCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_MODULE).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_MODULE).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByModuleCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByModuleCommand(new ModuleContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_YEAR).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_YEAR).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByYearCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByYearCommand(new YearContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_STUDENTID).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByStudentIdCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByStudentIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_TEACHINGNOMINATION).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_TEACHINGNOMINATION).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByTeachingNominationCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByTeachingNominationCommand(new TeachingNominationContainKeywordsPredicate(
                    Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_RATING).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_RATING).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByRatingCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByRatingCommand(new RatingContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else if (argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            String keywords = argumentMultimap.getValue(PREFIX_TAG).get();
            String trimmedKeywords = keywords.trim();
            if (trimmedKeywords.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByTagCommand.MESSAGE_USAGE));
            }

            String[] moduleKeywords = trimmedKeywords.split("\\s+");

            return new FindByTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(moduleKeywords)));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByPrefixCommand.MESSAGE_USAGE));
        }
    }
}
