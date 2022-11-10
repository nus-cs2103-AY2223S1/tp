package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.checkcommands.CheckBuyerCommand;
import seedu.address.logic.commands.checkcommands.CheckCommand;
import seedu.address.logic.commands.checkcommands.CheckOrderCommand;
import seedu.address.logic.commands.checkcommands.CheckPetCommand;
import seedu.address.logic.commands.checkcommands.CheckSupplierCommand;
import seedu.address.logic.commands.util.CommandUtil;

public class CheckCommandParserTest {
    private CheckCommandParser parser = new CheckCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, " t is is invalid  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validParams_success() {
        //Valid buyer args
        CheckCommand expected = new CheckBuyerCommand(INDEX_FIRST);
        for (String param : CommandUtil.ACCEPTABLE_BUYER_PARAMETER) {
            String input = param + " 1";
            assertParseSuccess(parser, input, expected);
        }

        //Valid supplier args
        expected = new CheckSupplierCommand(INDEX_FIRST);
        for (String param : CommandUtil.ACCEPTABLE_SUPPLIER_PARAMETER) {
            String input = param + " 1";
            assertParseSuccess(parser, input, expected);
        }

        //Valid order args
        expected = new CheckOrderCommand(INDEX_FIRST);
        for (String param : CommandUtil.ACCEPTABLE_ORDER_PARAMETER) {
            String input = param + " 1";
            assertParseSuccess(parser, input, expected);
        }

        //Valid pet args
        expected = new CheckPetCommand(INDEX_FIRST);
        for (String param : CommandUtil.ACCEPTABLE_PET_PARAMETER) {
            String input = param + " 1";
            assertParseSuccess(parser, input, expected);
        }
    }
}
