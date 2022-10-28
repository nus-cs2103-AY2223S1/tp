package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.commission.CompositeCustomerPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    // TODO Update since keyword can be empty now
    //    @Test
    //    public void parse_emptyArg_throwsParseException() {
    //        assertParseFailure(parser, "     ",
    //        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    //    }

    // TODO Update
    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("Alice", "Bob");
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompositeCustomerPredicate(keywords, new ArrayList<>(), new ArrayList<>()));
        assertParseSuccess(parser, "k/Alice k/Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n k/Alice \n \t k/Bob  \t", expectedFindCommand);
    }

}
