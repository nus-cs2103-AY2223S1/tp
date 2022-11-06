package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.client.SelectClientCommand;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;

public class SelectClientCommandParserTest {
    private final SelectClientCommandParser parser = new SelectClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        try {
            Index index = ParserUtil.parseIndex("2");
            assertParseSuccess(parser, "2",
                    new SelectClientCommand(index));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectClientCommand.MESSAGE_USAGE), pe);
        }
    }

    @Test
    public void parse_missingField_failure() throws ParseException {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectClientCommand.MESSAGE_USAGE));
    }
}

