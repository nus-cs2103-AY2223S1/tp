package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SUPPER;
import static paymelah.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static paymelah.logic.commands.CommandTestUtil.INVALID_MONEY_DESC;
import static paymelah.logic.commands.CommandTestUtil.MONEY_DESC_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.MONEY_DESC_SUPPER;
import static paymelah.logic.commands.CommandTestUtil.MONEY_DESC_TEN_DOLLARS;
import static paymelah.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SUPPER;
import static paymelah.logic.commands.CommandTestUtil.VALID_MONEY_MCDONALDS;
import static paymelah.logic.commands.CommandTestUtil.VALID_MONEY_SUPPER;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.SplitDebtCommand;
import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.testutil.DebtBuilder;

public class SplitDebtCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SplitDebtCommand.MESSAGE_USAGE);
    private SplitDebtCommandParser parser = new SplitDebtCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, DESCRIPTION_DESC_SUPPER + MONEY_DESC_SUPPER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // missing description prefix
        assertParseFailure(parser, "1" + VALID_DESCRIPTION_SUPPER + MONEY_DESC_SUPPER,
                MESSAGE_INVALID_FORMAT);

        // missing money prefix
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + VALID_MONEY_SUPPER,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-1" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 -5" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "some random string", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // note that zero index is a valid index if it is not the only unique valid index
        assertParseFailure(parser, "0" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0 0" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0 -2" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0 a" + VALID_DESCRIPTION_SUPPER, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        // invalid spacing between indexes
        assertParseFailure(parser, "1  2", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + MONEY_DESC_SUPPER,
                Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + INVALID_MONEY_DESC,
                Money.MESSAGE_CONSTRAINTS); // invalid money

        // valid money followed by invalid money.
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + MONEY_DESC_SUPPER + INVALID_MONEY_DESC,
                Money.MESSAGE_CONSTRAINTS);

        // valid description followed by invalid description.
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SUPPER + INVALID_DESCRIPTION_DESC
                + MONEY_DESC_SUPPER, Description.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parse_singleIndexAllFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_MCDONALDS + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        Set<Index> personIndexSet = new HashSet<>(List.of(targetIndex));
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleIndexAllFieldsSpecified_success() {
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON));
        StringBuilder personIndexes = new StringBuilder();
        personIndexSet.forEach(x -> personIndexes.append(x.getOneBased()).append(" "));
        String userInput = personIndexes + DESCRIPTION_DESC_MCDONALDS + MONEY_DESC_TEN_DOLLARS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney("3.34").build();
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleIndexWithZeroAllFieldsSpecified_success() {
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON));
        StringBuilder personIndexes = new StringBuilder();
        personIndexSet.forEach(x -> personIndexes.append(x.getOneBased()).append(" "));
        String userInput = "0 " + personIndexes + DESCRIPTION_DESC_MCDONALDS
                + MONEY_DESC_TEN_DOLLARS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney("2.50").build();
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleIndexWithRepeatsAllFieldsSpecified_success() {
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON));
        StringBuilder personIndexes = new StringBuilder();
        personIndexSet.forEach(x -> personIndexes.append(x.getOneBased()).append(" "));
        String userInput = "0 3 2 " + personIndexes + DESCRIPTION_DESC_MCDONALDS
                + MONEY_DESC_TEN_DOLLARS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney("2.50").build();
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_singleIndexInvalidDescriptionFollowedByValidDescription_success() {
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON));
        StringBuilder personIndexes = new StringBuilder();
        personIndexSet.forEach(x -> personIndexes.append(x.getOneBased()).append(" "));
        String userInput = personIndexes + INVALID_DESCRIPTION_DESC + DESCRIPTION_DESC_MCDONALDS
                + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_singleIndexInvalidMoneyFollowedByValidMoney_success() {
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON));
        StringBuilder personIndexes = new StringBuilder();
        personIndexSet.forEach(x -> personIndexes.append(x.getOneBased()).append(" "));
        String userInput = personIndexes + DESCRIPTION_DESC_MCDONALDS + INVALID_MONEY_DESC
                + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Set<Index> personIndexSet = new HashSet<>(List.of(INDEX_FIRST_PERSON));
        StringBuilder personIndexes = new StringBuilder();
        personIndexSet.forEach(x -> personIndexes.append(x.getOneBased()).append(" "));
        String userInput = personIndexes + DESCRIPTION_DESC_SUPPER + DESCRIPTION_DESC_MCDONALDS
                + MONEY_DESC_SUPPER + MONEY_DESC_MCDONALDS;

        Debt expectedDebt = new DebtBuilder().withDescription(VALID_DESCRIPTION_MCDONALDS)
                .withMoney(VALID_MONEY_MCDONALDS).build();
        SplitDebtCommand expectedCommand = new SplitDebtCommand(personIndexSet, expectedDebt);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
