package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.StringUtil.getIntFromString;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CURRENT_STOCK_NEGATIVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NO_VALUE_CURRENT_STOCK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CURRENT_STOCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CURRENT_STOCK_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SUPPLY_ITEM;
import static seedu.address.testutil.TypicalPersons.ALICE_SUPPLIER;
import static seedu.address.testutil.TypicalSupplyItems.GINGER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStockCommand;
import seedu.address.logic.commands.EditStockCommand.EditStockDescriptor;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditStockDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditStockCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStockCommand.MESSAGE_USAGE);
    private EditStockCommandParser parser = new EditStockCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditStockCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingValue_failure() {
        // no value specified after prefix
        assertParseFailure(parser, "1 c/", EditStockCommand.MESSAGE_NOT_EDITED_PREFIX_DETECTED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 x/string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_SUPPLY_ITEM;
        String userInput = targetIndex.getOneBased() + " " + VALID_CURRENT_STOCK_DESC;
        Person supplier = new PersonBuilder(ALICE_SUPPLIER).build();
        SupplyItem editedSupplyItem = new SupplyItem(GINGER.getName(), getIntFromString(VALID_CURRENT_STOCK),
                GINGER.getMinStock(), supplier, GINGER.getTags());
        EditStockDescriptor descriptor = new EditStockDescriptorBuilder(editedSupplyItem).build();
        EditStockCommand editStockCommand = new EditStockCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, editStockCommand);
    }

    @Test
    public void parse_invalidValueNegative_failure() {
        Index targetIndex = INDEX_FIRST_SUPPLY_ITEM;
        String userInput = targetIndex.getOneBased() + " " + INVALID_CURRENT_STOCK_NEGATIVE_DESC;
        assertParseFailure(parser, userInput, EditStockCommand.MESSAGE_COUNT_NEGATIVE);
    }

    @Test
    public void parse_invalidValuePrefixNoValue_failure() {
        Index targetIndex = INDEX_FIRST_SUPPLY_ITEM;
        String userInput = targetIndex.getOneBased() + " " + INVALID_NO_VALUE_CURRENT_STOCK_DESC;
        assertParseFailure(parser, userInput, EditStockCommand.MESSAGE_NOT_EDITED_PREFIX_DETECTED);
    }
}
