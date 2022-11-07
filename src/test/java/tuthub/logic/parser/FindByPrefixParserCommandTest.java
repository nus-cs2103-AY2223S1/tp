package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

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
import tuthub.model.tutor.EmailContainsKeywordsPredicate;
import tuthub.model.tutor.ModuleContainsKeywordsPredicate;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.model.tutor.PhoneContainsKeywordsPredicate;
import tuthub.model.tutor.RatingContainsKeywordsPredicate;
import tuthub.model.tutor.StudentIdContainsKeywordsPredicate;
import tuthub.model.tutor.TagContainsKeywordsPredicate;
import tuthub.model.tutor.TeachingNominationContainKeywordsPredicate;
import tuthub.model.tutor.YearContainsKeywordsPredicate;

public class FindByPrefixParserCommandTest {
    private FindByPrefixCommandParser parser = new FindByPrefixCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByPrefixCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        assertParseFailure(parser, " n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindByNameCommand() {
        FindByNameCommand expectedFindByNameCommand =
                new FindByNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("alex", "alice")));
        assertParseSuccess(parser, " n/alex alice", expectedFindByNameCommand);
    }

    @Test
    public void parse_invalidPhoneArgs_throwsParseException() {
        assertParseFailure(parser, " p/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByPhoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validPhoneArgs_returnsFindByNameCommand() {
        FindByPhoneCommand expectedFindByPhoneCommand =
                new FindByPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("99999999", "98765432")));
        assertParseSuccess(parser, " p/99999999 98765432", expectedFindByPhoneCommand);
    }

    @Test
    public void parse_invalidEmailArgs_throwsParseException() {
        assertParseFailure(parser, " e/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByEmailCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validEmailArgs_returnsFindByEmailCommand() {
        FindByEmailCommand expectedFindByEmailCommand =
                new FindByEmailCommand(new EmailContainsKeywordsPredicate(
                        Arrays.asList("alice@example.com", "benson@example.com")));
        assertParseSuccess(parser, " e/alice@example.com benson@example.com", expectedFindByEmailCommand);
    }

    @Test
    public void parse_invalidModuleArgs_throwsParseException() {
        assertParseFailure(parser, " m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validModuleArgs_returnsFindByModuleCommand() {
        FindByModuleCommand expectedFindByModuleCommand =
                new FindByModuleCommand(new ModuleContainsKeywordsPredicate(Arrays.asList("cs2100", "cs2105")));
        assertParseSuccess(parser, " m/cs2100 cs2105", expectedFindByModuleCommand);
    }

    @Test
    public void parse_invalidYearArgs_throwsParseException() {
        assertParseFailure(parser, " y/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByYearCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validYearArgs_returnsFindByYearCommand() {
        FindByYearCommand expectedFindByYearCommand =
                new FindByYearCommand(new YearContainsKeywordsPredicate(Arrays.asList("2", "3")));
        assertParseSuccess(parser, " y/2 3", expectedFindByYearCommand);
    }

    @Test
    public void parse_invalidStudentIdArgs_throwsParseException() {
        assertParseFailure(parser, " s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByStudentIdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validStudentIdArgs_returnsFindByStudentIdCommand() {
        FindByStudentIdCommand expectedFindByStudentIdCommand =
                new FindByStudentIdCommand(new StudentIdContainsKeywordsPredicate(
                        Arrays.asList("A0123456X", "A0123456Y")));
        assertParseSuccess(parser, " s/A0123456X A0123456Y", expectedFindByStudentIdCommand);
    }

    @Test
    public void parse_invalidTeachingNominationArgs_throwsParseException() {
        assertParseFailure(parser, " tn/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByTeachingNominationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTeachingNominationArgs_returnsFindByTeachingNominationCommand() {
        FindByTeachingNominationCommand expectedFindByTeachingNominationCommand =
                new FindByTeachingNominationCommand(new TeachingNominationContainKeywordsPredicate(
                        Arrays.asList("1", "2")));
        assertParseSuccess(parser, " tn/1 2", expectedFindByTeachingNominationCommand);
    }

    @Test
    public void parse_invalidRatingArgs_throwsParseException() {
        assertParseFailure(parser, " r/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByRatingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validRatingArgs_returnsFindByRatingCommand() {
        FindByRatingCommand expectedFindByRatingCommand =
                new FindByRatingCommand(new RatingContainsKeywordsPredicate(Arrays.asList("2.5", "3.5")));
        assertParseSuccess(parser, " r/2.5 3.5", expectedFindByRatingCommand);
    }

    @Test
    public void parse_invalidTagArgs_throwsParseException() {
        assertParseFailure(parser, " t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTagArgs_returnsFindByTagCommand() {
        FindByTagCommand expectedFindByTagCommand =
                new FindByTagCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "senior")));
        assertParseSuccess(parser, " t/friends senior", expectedFindByTagCommand);
    }
}
