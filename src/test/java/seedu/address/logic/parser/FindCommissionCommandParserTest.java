package seedu.address.logic.parser;

import static seedu.address.logic.commands.commission.FindCommissionCommandTest.preparePredicate;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindCommissionCommand;
import seedu.address.model.commission.CompositeCommissionPredicate;
import seedu.address.model.tag.Tag;

public class FindCommissionCommandParserTest {
    private FindCommissionCommandParser parser = new FindCommissionCommandParser();

    @Test
    public void execute_throwsNoEmptyKeyword() {
        String expectedMessage = Messages.MESSAGE_KEYWORD_EMPTY;
        assertParseFailure(parser, "k/   ", expectedMessage);
        assertParseFailure(parser, "k/tohou k/   -all t/comic t/cheap -any t/exchange", expectedMessage);
    }

    @Test
    public void execute_throwsInvalidTag() {
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, "-all t/   ", expectedMessage);
        assertParseFailure(parser, "-any t/   ", expectedMessage);
        assertParseFailure(parser, "k/tohou k/arcane -all t/comic t/   -any t/exchange", expectedMessage);
    }

    @Test
    public void execute_throwsInvalidCommandFormat() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommissionCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "\t", expectedMessage);
        assertParseFailure(parser, "abdvwj sdwsgbklfm", expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsFindCommissionCommand() {
        String[] keywords = new String[]{"Joker", "Batman"};
        Tag[] mustTags = new Tag[]{new Tag("villain"), new Tag("crazy")};
        Tag[] anyTags = new Tag[]{new Tag("resourceful"), new Tag("retarded"), new Tag("vengeful")};
        Tag[] emptyTags = new Tag[]{};
        String[] emptyKeywords = new String[]{};

        // Only keywords
        CompositeCommissionPredicate predicate = preparePredicate(keywords, emptyTags, emptyTags);
        FindCommissionCommand expectedFindCommissionCommand = new FindCommissionCommand(predicate);
        assertParseSuccess(parser, "k/Joker k/Batman", expectedFindCommissionCommand);

        // Only must-have tags
        predicate = preparePredicate(emptyKeywords, mustTags, emptyTags);
        expectedFindCommissionCommand = new FindCommissionCommand(predicate);
        assertParseSuccess(parser, "-all t/villain t/crazy", expectedFindCommissionCommand);

        // Only optional tags
        predicate = preparePredicate(emptyKeywords, emptyTags, anyTags);
        expectedFindCommissionCommand = new FindCommissionCommand(predicate);
        assertParseSuccess(parser, "-any t/resourceful t/retarded t/vengeful", expectedFindCommissionCommand);

        // All three criteria present
        predicate = preparePredicate(keywords, mustTags, anyTags);
        expectedFindCommissionCommand = new FindCommissionCommand(predicate);
        assertParseSuccess(parser, "k/Joker k/Batman -all t/villain t/crazy -any t/resourceful t/retarded t/vengeful",
                expectedFindCommissionCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n k/Joker \n \t k/Batman  \t -all \n t/villain t/crazy \n \t -any"
                + " \t t/resourceful \n\n t/retarded t/vengeful", expectedFindCommissionCommand);
    }
}
