package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_BILL_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BILL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYMENT_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailureForPrefix;
import static seedu.address.testutil.TypicalBills.BILL_1;
import static seedu.address.testutil.TypicalBills.BILL_2;
import static seedu.address.testutil.TypicalBills.getTypicalBillsHealthContact;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindBillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindBillCommandParserTest {
    private FindBillCommandParser parser = new FindBillCommandParser();
    private Model model = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBillCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindBillCommand() throws ParseException {
        // no leading and trailing whitespaces
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 1);
        FindBillCommand firstCommand = parser.parse(" n/Pauline");
        expectedModel.updateFilteredBillList(firstCommand.getPredicate());
        assertCommandSuccess(firstCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_1), model.getFilteredBillList());

        // multiple whitespaces between keywords
        FindBillCommand secondCommand = parser.parse(" n/    pauline  ");
        expectedModel.updateFilteredBillList(secondCommand.getPredicate());
        assertCommandSuccess(secondCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_1), model.getFilteredBillList());

        // multiple fields
        FindBillCommand thirdCommand = parser.parse(" n/ben p/unpaid");
        expectedModel.updateFilteredBillList(thirdCommand.getPredicate());
        assertCommandSuccess(thirdCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_2), model.getFilteredBillList());
    }

    @Test
    public void checkNumberOfPrefixes() {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" n/ Alice n/Bob", PREFIX_NAME, PREFIX_PAYMENT_STATUS,
                        PREFIX_AMOUNT, PREFIX_BILL_DATE);
        assertParseFailureForPrefix(parser, PREFIX_NAME, argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBillCommand.MESSAGE_USAGE));
    }
}
