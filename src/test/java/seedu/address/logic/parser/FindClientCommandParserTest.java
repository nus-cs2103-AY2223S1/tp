package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.FindClientCommand;
import seedu.address.logic.parser.predicates.ClientContainsKeywordsPredicate;
import seedu.address.model.Name;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientMobile;

/**
 * Represents the tests for parsing the FindClientCommand.
 */
public class FindClientCommandParserTest {

    private ClientCommandParser parser = new ClientCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG,
                "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindClientCommand.MESSAGE_FIND_CLIENT_USAGE));
    }

    @Test
    public void parse_argsWithNoPrefix_throwsParseException() {
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG,
                "abcd", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindClientCommand.MESSAGE_FIND_CLIENT_USAGE));
    }

    @Test
    public void parse_validMultiPrefixWithNoRepetitionArgs_returnsFindCommand() {

        List<String> name = Arrays.asList("Harry Jaime");
        List<String> email = Arrays.asList("potter@reddit.com-sg");
        List<String> phone = Arrays.asList("103835180");
        List<String> clientId = Arrays.asList("1");


        FindClientCommand expectedFindCommand =
                new FindClientCommand(new ClientContainsKeywordsPredicate(name, email, phone, clientId));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " n/Harry Jaime m/103835180 e/potter@reddit.com-sg c/1",
                expectedFindCommand);
    }

    @Test
    public void parse_validSinglePrefixWithNoRepetitionArgs_returnsFindCommand() {

        List<String> name = Arrays.asList("Harry");
        List<String> email = Arrays.asList("potter@reddit.com-sg");
        List<String> phone = Arrays.asList("103835180");
        List<String> clientId = Arrays.asList("1");
        List<String> empty = new ArrayList<>();

        //name
        FindClientCommand expectedFindCommandName =
                new FindClientCommand(new ClientContainsKeywordsPredicate(name, empty, empty, empty));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " n/Harry", expectedFindCommandName);

        //email
        FindClientCommand expectedFindCommandEmail =
                new FindClientCommand(new ClientContainsKeywordsPredicate(empty, email, empty, empty));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " e/potter@reddit.com-sg", expectedFindCommandEmail);

        //phone (mobile)
        FindClientCommand expectedFindCommandMobile =
                new FindClientCommand(new ClientContainsKeywordsPredicate(empty, empty, phone, empty));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " m/103835180", expectedFindCommandMobile);

        //client id
        FindClientCommand expectedFindCommandId =
                new FindClientCommand(new ClientContainsKeywordsPredicate(empty, empty, empty, clientId));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " c/1", expectedFindCommandId);
    }

    @Test
    public void parse_validMultiPrefixWithRepetitionArgs_returnsFindCommand() {

        List<String> name = Arrays.asList("Harry Jaime", "Steve Rogers");
        List<String> email = Arrays.asList("potter@reddit.com-sg", "test@gmail.com");
        List<String> phone = Arrays.asList("103835180", "1234");
        List<String> clientId = Arrays.asList("1", "3");


        FindClientCommand expectedFindCommand =
                new FindClientCommand(new ClientContainsKeywordsPredicate(name, email, phone, clientId));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " n/Harry Jaime n/Steve Rogers e/potter@reddit.com-sg e/test@gmail.com m/103835180 m/1234 "
                        + "c/1 c/3", expectedFindCommand);
    }

    @Test
    public void parse_validMultiPrefixInMixedOrder_returnsFindCommand() {

        List<String> name = Arrays.asList("Harry Jaime", "Steve Rogers");
        List<String> email = Arrays.asList("potter@reddit.com-sg", "test@gmail.com");
        List<String> phone = Arrays.asList("103835180", "1234");
        List<String> clientId = Arrays.asList("1", "3");


        FindClientCommand expectedFindCommand =
                new FindClientCommand(new ClientContainsKeywordsPredicate(name, email, phone, clientId));
        assertParseSuccess(parser, FindClientCommand.COMMAND_FLAG,
                " n/Harry Jaime c/1 e/potter@reddit.com-sg n/Steve Rogers "
                        + "m/103835180 e/test@gmail.com m/1234 c/3", expectedFindCommand);
    }

    @Test
    public void parse_invalidMultiPrefix_throwsException() {
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " n/&&& invalid#name",
                Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG,
                " e/invalidemail@.com", ClientEmail.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " m/12",
                ClientMobile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " c/0",
                ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " c/-1",
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyMultiPrefix_throwsException() {
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " n/", Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " e/", ClientEmail.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " m/", ClientMobile.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, FindClientCommand.COMMAND_FLAG, " c/", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
