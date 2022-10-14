package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SUPPER;
import static paymelah.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static paymelah.logic.commands.CommandTestUtil.INVALID_MONEY_DESC;
import static paymelah.logic.commands.CommandTestUtil.MONEY_DESC_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.MONEY_DESC_SUPPER;
import static paymelah.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SUPPER;
import static paymelah.logic.commands.CommandTestUtil.VALID_MONEY_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.VALID_MONEY_SUPPER;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.AddDebtCommand;
import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.testutil.DebtBuilder;

public class AddDebtCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDebtCommand.MESSAGE_USAGE);
    private AddDebtCommandParser parser = new AddDebtCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, DESCRIPTION_DESC_SUPPER + MONEY_DESC_SUPPER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // missing description prefix
        assertParseFailure(parser, "1" + VALID_DESCRIPTION_SUPPER + MONEY_DESC_SUPPER, MESSAGE_INVALID_FORMAT);

        // missing money prefix
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + VALID_MONEY_SUPPER, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + MONEY_DESC_SUPPER,
                Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + INVALID_MONEY_DESC,
                Money.MESSAGE_CONSTRAINTS); // invalid money

        // invalid description followed by valid money
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + MONEY_DESC_SUPPER,
                Description.MESSAGE_CONSTRAINTS);

        // valid money followed by invalid money. The test case for invalid money followed by valid money
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + MONEY_DESC_SUPPER + INVALID_MONEY_DESC,
                Money.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + INVALID_MONEY_DESC,
                Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MCDONALDS + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        AddDebtCommand expectedCommand = new AddDebtCommand(targetIndex, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_SUPPER + DESCRIPTION_DESC_MCDONALDS
                + MONEY_DESC_SUPPER + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        AddDebtCommand expectedCommand = new AddDebtCommand(targetIndex, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC + DESCRIPTION_DESC_MCDONALDS
                + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        AddDebtCommand expectedCommand = new AddDebtCommand(targetIndex, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
