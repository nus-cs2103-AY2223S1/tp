package seedu.address.logic.parser;

import static seedu.address.logic.commands.FindCommandTest.preparePredicate;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.commission.CompositeCustomerPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void execute_throwsNoEmptyKeyword() {
        String expectedMessage = Messages.MESSAGE_KEYWORD_EMPTY;
        assertParseFailure(parser, "k/   ", expectedMessage);
        assertParseFailure(parser, "k/animal k/   -all t/toy t/cheap -any t/gift", expectedMessage);
    }

    @Test
    public void execute_throwsInvalidTag() {
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, "-all t/   ", expectedMessage);
        assertParseFailure(parser, "-any t/   ", expectedMessage);
        assertParseFailure(parser, "k/animal k/mammal -all t/toy t/   -any t/gift", expectedMessage);
    }

    @Test
    public void execute_throwsInvalidCommandFormat() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "\t", expectedMessage);
        assertParseFailure(parser, "abdvwj sdwsgbklfm", expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        String[] keywords = new String[]{"Denise", "Kurt"};
        Tag[] mustTags = new Tag[]{new Tag("enemy"), new Tag("bully")};
        Tag[] anyTags = new Tag[]{new Tag("rich"), new Tag("caring"), new Tag("forgetful")};
        Tag[] emptyTags = new Tag[]{};
        String[] emptyKeywords = new String[]{};

        // Only keywords
        CompositeCustomerPredicate predicate = preparePredicate(keywords, emptyTags, emptyTags);
        FindCommand expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, "k/Denise k/Kurt", expectedFindCommand);

        // Only must-have tags
        predicate = preparePredicate(emptyKeywords, mustTags, emptyTags);
        expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, "-all t/enemy t/bully", expectedFindCommand);

        // Only optional tags
        predicate = preparePredicate(emptyKeywords, emptyTags, anyTags);
        expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, "-any t/rich t/caring t/forgetful", expectedFindCommand);

        // All three criteria present
        predicate = preparePredicate(keywords, mustTags, anyTags);
        expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser, "k/Denise k/Kurt -all t/enemy t/bully -any t/rich t/caring t/forgetful",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n k/Denise \n \t k/Kurt  \t -all \n t/enemy t/bully \n \t -any"
                + " \t t/rich \n\n t/caring t/forgetful", expectedFindCommand);
    }
}
