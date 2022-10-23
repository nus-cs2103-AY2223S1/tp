package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddItemCommand;

public class AddItemCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

    private static final String VALID_CURRENT_AND_MIN_STOCK = " c/5 + m/2";



    private AddItemCommandParser parser = new AddItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "c/5", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "m/5", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, VALID_CURRENT_AND_MIN_STOCK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // only one field specified

        assertParseFailure(parser, "1 c/5", MESSAGE_INVALID_FORMAT);

        assertParseFailure(parser, "1 m/2", MESSAGE_INVALID_FORMAT);


        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_CURRENT_AND_MIN_STOCK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_CURRENT_AND_MIN_STOCK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 x/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + " c/-5 m/2", MESSAGE_INVALID_FORMAT); // invalid currentStock

        assertParseFailure(parser, "1" + " c/5 m/-2", MESSAGE_INVALID_FORMAT); // invalid minStock

        assertParseFailure(parser, "one" + VALID_CURRENT_AND_MIN_STOCK, MESSAGE_INVALID_FORMAT); // invalid index

        assertParseFailure(parser, "random string"
                + VALID_CURRENT_AND_MIN_STOCK, MESSAGE_INVALID_FORMAT); // invalid index


    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " c/5" + " m/2";
        System.out.println(userInput);
        AddItemCommand expectedCommand = new AddItemCommand(INDEX_SECOND_PERSON, 5, 2);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
