package seedu.pennywise.logic.parser;

import static seedu.pennywise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennywise.logic.commands.CommandTestUtil.AMT_ALLOWANCE;
import static seedu.pennywise.logic.commands.CommandTestUtil.AMT_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.AMT_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.AMT_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.AMT_MOVIE;
import static seedu.pennywise.logic.commands.CommandTestUtil.DATE_ALLOWANCE;
import static seedu.pennywise.logic.commands.CommandTestUtil.DATE_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.DATE_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.DATE_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.DATE_MOVIE;
import static seedu.pennywise.logic.commands.CommandTestUtil.DESC_ALLOWANCE;
import static seedu.pennywise.logic.commands.CommandTestUtil.DESC_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.DESC_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.DESC_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.DESC_MOVIE;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_AMT;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_DESC;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.pennywise.logic.commands.CommandTestUtil.INVALID_TYPE;
import static seedu.pennywise.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.pennywise.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.pennywise.logic.commands.CommandTestUtil.TAG_ALLOWANCE;
import static seedu.pennywise.logic.commands.CommandTestUtil.TAG_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.TAG_INVESTMENT;
import static seedu.pennywise.logic.commands.CommandTestUtil.TAG_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.TAG_MOVIE;
import static seedu.pennywise.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.pennywise.logic.commands.CommandTestUtil.TYPE_INCOME;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DESC_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;
import static seedu.pennywise.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pennywise.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pennywise.testutil.TypicalEntry.ALLOWANCE;
import static seedu.pennywise.testutil.TypicalEntry.INVESTMENT;
import static seedu.pennywise.testutil.TypicalEntry.LUNCH;
import static seedu.pennywise.testutil.TypicalEntry.MOVIE;

import org.junit.jupiter.api.Test;

import seedu.pennywise.logic.commands.AddCommand;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Tag;
import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.IncomeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();
    private EntryType expenditureType = new EntryType(VALID_TYPE_EXPENDITURE);
    private EntryType incomeType = new EntryType(VALID_TYPE_INCOME);
    @Test
    public void parse_allFieldsPresent_success() {
        Entry expectedExpenditure = new ExpenditureBuilder(LUNCH).build();
        Entry expectedIncome = new ExpenditureBuilder(ALLOWANCE).build();

        //Waiting for income builder
        // whitespace only preamble
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_EXPENDITURE + DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                new AddCommand(expectedExpenditure, expenditureType));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_INCOME + DESC_ALLOWANCE + AMT_ALLOWANCE + DATE_ALLOWANCE + TAG_ALLOWANCE,
                new AddCommand(expectedIncome, incomeType));

        // multiple desc - last desc accepted
        assertParseSuccess(
                parser,
                TYPE_EXPENDITURE + DESC_DINNER + DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                new AddCommand(expectedExpenditure, expenditureType));
        assertParseSuccess(
                parser,
                TYPE_INCOME + DESC_DINNER + DESC_ALLOWANCE + AMT_ALLOWANCE + DATE_ALLOWANCE + TAG_ALLOWANCE,
                new AddCommand(expectedIncome, incomeType));

        // multiple type - last type accepted
        assertParseSuccess(
                parser,
                TYPE_INCOME + TYPE_EXPENDITURE + DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                new AddCommand(expectedExpenditure, expenditureType));
        assertParseSuccess(
                parser,
                TYPE_INCOME + TYPE_INCOME + DESC_ALLOWANCE + AMT_ALLOWANCE + DATE_ALLOWANCE + TAG_ALLOWANCE,
                new AddCommand(expectedIncome, incomeType));
        //  multiple amount - last amount accepted

        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_INCOME + DESC_ALLOWANCE
                        + AMT_DINNER + AMT_ALLOWANCE + DATE_ALLOWANCE + TAG_ALLOWANCE,
                new AddCommand(expectedIncome, expenditureType));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_EXPENDITURE + DESC_LUNCH
                        + AMT_DINNER + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                new AddCommand(expectedExpenditure, incomeType));

        // multiple date - last date accepted
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_EXPENDITURE + DESC_LUNCH
                        + AMT_LUNCH + DATE_DINNER + DATE_LUNCH + TAG_LUNCH,
                new AddCommand(expectedExpenditure, expenditureType));
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_INCOME + DESC_ALLOWANCE
                        + AMT_ALLOWANCE + DATE_DINNER + DATE_ALLOWANCE + TAG_ALLOWANCE,
                new AddCommand(expectedIncome, incomeType));
        // multiple tag - last tag accepted
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + TYPE_EXPENDITURE + DESC_LUNCH
                        + AMT_LUNCH + DATE_LUNCH + TAG_MOVIE
                        + TAG_LUNCH,
                new AddCommand(expectedExpenditure, expenditureType));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // add expenditure parsing
        Entry expectedExpenditure = new ExpenditureBuilder(MOVIE).build();
        assertParseSuccess(parser, TYPE_EXPENDITURE + DESC_MOVIE + AMT_MOVIE + DATE_MOVIE + TAG_MOVIE,
                new AddCommand(expectedExpenditure, expenditureType));

        // add income parsing
        Entry expectedIncome = new IncomeBuilder(INVESTMENT).build();
        assertParseSuccess(parser, TYPE_INCOME + DESC_INVESTMENT + AMT_INVESTMENT
                        + DATE_INVESTMENT + TAG_INVESTMENT,
                new AddCommand(expectedIncome, incomeType));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);


        // missing type prefix
        assertParseFailure(
                parser,
                VALID_TYPE_EXPENDITURE + DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                expectedMessage);
        assertParseFailure(
                parser,
                VALID_TYPE_INCOME + DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                expectedMessage);

        // missing description prefix
        assertParseFailure(
                parser,
                TYPE_EXPENDITURE + VALID_DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                expectedMessage);
        assertParseFailure(
                parser,
                TYPE_INCOME + VALID_DESC_LUNCH + AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(
                parser,
                TYPE_EXPENDITURE + DESC_LUNCH + VALID_AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                expectedMessage);
        assertParseFailure(
                parser,
                TYPE_INCOME + DESC_LUNCH + VALID_AMT_LUNCH + DATE_LUNCH + TAG_LUNCH,
                expectedMessage);

        // missing date prefix
        assertParseFailure(
                parser,
                TYPE_EXPENDITURE + DESC_LUNCH + AMT_LUNCH + VALID_DATE_LUNCH + TAG_LUNCH,
                expectedMessage);
        assertParseFailure(
                parser,
                TYPE_INCOME + DESC_LUNCH + AMT_LUNCH + VALID_DATE_LUNCH + TAG_LUNCH,
                expectedMessage);

        // all prefixes missingVALID_
        assertParseFailure(
                parser,
                TYPE_EXPENDITURE + VALID_DESC_LUNCH + VALID_AMT_LUNCH + VALID_DATE_LUNCH + VALID_TAG_LUNCH,
                expectedMessage);
        assertParseFailure(
                parser,
                TYPE_INCOME + VALID_DESC_LUNCH + VALID_AMT_LUNCH + VALID_DATE_LUNCH + VALID_TAG_LUNCH,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Type
        assertParseFailure(
                parser, INVALID_TYPE + DESC_DINNER + AMT_DINNER + DATE_DINNER + TAG_DINNER,
                EntryType.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(
                parser, TYPE_EXPENDITURE + INVALID_DESC + AMT_DINNER + DATE_DINNER + TAG_DINNER,
                Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, TYPE_INCOME + INVALID_DESC + AMT_INVESTMENT + DATE_INVESTMENT + TAG_INVESTMENT,
                Description.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(
                parser, TYPE_EXPENDITURE + DESC_DINNER + INVALID_AMT + DATE_DINNER + TAG_DINNER,
                Amount.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, TYPE_INCOME + DESC_INVESTMENT + INVALID_AMT + DATE_INVESTMENT + TAG_INVESTMENT,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(
                parser, TYPE_EXPENDITURE + DESC_DINNER + AMT_DINNER + INVALID_DATE + TAG_DINNER,
                Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, TYPE_INCOME + DESC_INVESTMENT + AMT_INVESTMENT + INVALID_DATE + TAG_INVESTMENT,
                Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(
                parser, TYPE_EXPENDITURE + DESC_DINNER + AMT_DINNER + DATE_DINNER + INVALID_TAG,
                Tag.EXPENDITURE_CONSTRAINTS);
        assertParseFailure(
                parser, TYPE_INCOME + DESC_INVESTMENT + AMT_INVESTMENT + DATE_INVESTMENT + INVALID_TAG,
                Tag.INCOME_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(
                parser, TYPE_EXPENDITURE + DESC_DINNER + INVALID_AMT + INVALID_DATE + TAG_DINNER,
                Amount.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, TYPE_INCOME + DESC_DINNER + INVALID_AMT + INVALID_DATE + TAG_DINNER,
                Amount.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(
                parser, PREAMBLE_NON_EMPTY + TYPE_EXPENDITURE + DESC_DINNER + AMT_DINNER + DATE_DINNER
                + TAG_DINNER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        assertParseFailure(
                parser, PREAMBLE_NON_EMPTY + TYPE_INCOME + DESC_DINNER + AMT_DINNER + DATE_DINNER
                        + TAG_DINNER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
