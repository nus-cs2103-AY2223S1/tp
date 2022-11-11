package longtimenosee.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.ViewPinCommand;
import longtimenosee.logic.parser.exceptions.ParseException;


public class ViewPinCommandParserTest {
    private final ViewPinCommandParser parser = new ViewPinCommandParser();
    @Test
    public void parse_withArgument_throwsParseException() throws ParseException {
        assertTrue(parser.parse(ViewPinCommand.COMMAND_WORD) instanceof ViewPinCommand);
    }
}
