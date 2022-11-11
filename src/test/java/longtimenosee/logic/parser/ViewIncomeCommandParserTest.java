package longtimenosee.logic.parser;

import static longtimenosee.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.ViewIncomeCommand;


public class ViewIncomeCommandParserTest {
    private final ViewIncomeCommandParser parser = new ViewIncomeCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", "Invalid command format! \n"
                + String.format(ViewIncomeCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidLimit_throwsParseException() {
        assertParseFailure(parser, "2101", "Please pick a year between 1900 and 2100!");
        assertParseFailure(parser, " 2101    ", "Please pick a year between 1900 and 2100!");
        assertParseFailure(parser, "1899", "Please pick a year between 1900 and 2100!");
    }
}
