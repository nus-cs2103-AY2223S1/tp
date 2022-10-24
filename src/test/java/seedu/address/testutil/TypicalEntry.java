
package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_ALLOWANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_ALLOWANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_ALLOWANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALLOWANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MOVIE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.PennyWise;
import seedu.address.model.entry.Expenditure;
import seedu.address.model.entry.Income;

/**
 *  A utility class containing a list of {@code Entry} objects to be used in tests.
 */
public class TypicalEntry {

    public static final Expenditure LUNCH = new ExpenditureBuilder()
            .withDescription(VALID_DESC_LUNCH)
            .withAmount(VALID_AMT_LUNCH)
            .withDate(VALID_DATE_LUNCH)
            .withTag(VALID_TAG_LUNCH)
            .build();
    public static final Expenditure DINNER = new ExpenditureBuilder()
            .withDescription(VALID_DESC_DINNER)
            .withAmount(VALID_AMT_DINNER)
            .withDate(VALID_DATE_DINNER)
            .withTag(VALID_TAG_DINNER)
            .build();
    public static final Expenditure MOVIE = new ExpenditureBuilder()
            .withDescription(VALID_DESC_MOVIE)
            .withAmount(VALID_AMT_MOVIE)
            .withDate(VALID_DATE_MOVIE)
            .withTag(VALID_TAG_MOVIE)
            .build();
    public static final Expenditure GROCERIES = new ExpenditureBuilder()
            .withDescription("groceries")
            .withAmount("15.20")
            .withDate("24-09-2022")
            .build();

    public static final Expenditure SUPPER = new ExpenditureBuilder()
            .withDescription("supper")
            .withAmount("6.80")
            .withDate("25-09-2022")
            .build();
    // add typical income

    public static final Income ALLOWANCE = new IncomeBuilder()
            .withDescription(VALID_DESC_ALLOWANCE)
            .withAmount(VALID_AMT_ALLOWANCE)
            .withDate(VALID_DATE_ALLOWANCE)
            .withTag(VALID_TAG_ALLOWANCE)
            .build();

    public static final Income INVESTMENT = new IncomeBuilder()
            .withDescription(VALID_DESC_INVESTMENT)
            .withAmount(VALID_AMT_INVESTMENT)
            .withDate(VALID_DATE_INVESTMENT)
            .withTag(VALID_TAG_INVESTMENT)
            .build();

    public static PennyWise getTypicalPennyWise() {
        PennyWise ab = new PennyWise();

        for (Expenditure expenditure : getTypicalExpenditure()) {
            ab.addExpenditure(expenditure);
        }

        for (Income income: getTypicalIncome()) {
            ab.addIncome(income);
        }
        return ab;
    }

    public static List<Expenditure> getTypicalExpenditure() {
        return new ArrayList<>(Arrays.asList(LUNCH, DINNER, MOVIE));
    }

    public static List<Income> getTypicalIncome() {
        return new ArrayList<>(Arrays.asList(ALLOWANCE, INVESTMENT));
    }
}
