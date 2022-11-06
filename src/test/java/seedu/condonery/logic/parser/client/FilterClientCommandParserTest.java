package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.condonery.logic.commands.client.FilterClientCommand;
import seedu.condonery.model.client.ClientTagContainsKeywordsPredicate;

public class FilterClientCommandParserTest {
    private final FilterClientCommandParser parser = new FilterClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        List<String> keywords = new ArrayList<>();
        keywords.add("testing");
        ClientTagContainsKeywordsPredicate clientTagContainsKeywordsPredicate =
                new ClientTagContainsKeywordsPredicate(keywords);
        assertParseSuccess(parser, "testing",
                new FilterClientCommand(clientTagContainsKeywordsPredicate));
    }

    @Test
    public void parse_missingField_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClientCommand.MESSAGE_USAGE));
    }

}
