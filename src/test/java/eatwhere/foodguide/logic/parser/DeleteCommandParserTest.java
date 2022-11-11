package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.HELP_DESC;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.DeleteCommand;
import eatwhere.foodguide.testutil.TypicalIndexes;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new DeleteCommand(TypicalIndexes.INDEX_FIRST_EATERY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_displayHelp_success() {
        // only help parameter
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser, HELP_DESC, DeleteCommand.MESSAGE_USAGE);

        // help parameter overrides index
        CommandParserTestUtil.assertParseDisplayCommandHelp(parser, "1" + HELP_DESC,
                DeleteCommand.MESSAGE_USAGE);
    }
}
