package seedu.pennywise.model.util;

import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Expenditure;
import seedu.pennywise.model.entry.Income;
import seedu.pennywise.model.entry.Tag;

/**
 * Contains utility methods for populating {@code PennyWise} with sample data.
 */
public class SampleDataUtil {
    public static final EntryType EXPENDITURE_TYPE = new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE);
    public static final EntryType INCOME_TYPE = new EntryType(EntryType.ENTRY_TYPE_INCOME);

    public static Expenditure[] getSampleExpenditure() {
        return new Expenditure[] {
            new Expenditure(
                    new Description("Chicken Rice Lunch at Deck"),
                    new Date("20-01-2022"),
                    new Amount("20.00"),
                    new Tag(EXPENDITURE_TYPE, "Food")),
            new Expenditure(
                    new Description("Kungfu Panda Movie"),
                    new Date("18-02-2022"),
                    new Amount("15.00"),
                    new Tag(EXPENDITURE_TYPE, "Entertainment")),
            new Expenditure(
                    new Description("Strawberry Milk"),
                    new Date("21-01-2022"),
                    new Amount("10.00"),
                    new Tag(EXPENDITURE_TYPE, "Groceries")),
            new Expenditure(
                    new Description("Math Assessment Book"),
                    new Date("26-01-2022"),
                    new Amount("20.00"),
                    new Tag(EXPENDITURE_TYPE, "Education")),
            new Expenditure(
                    new Description("Card Fare Topup"),
                    new Date("25-01-2022"),
                    new Amount("50.00"),
                    new Tag(EXPENDITURE_TYPE, "Transport"))
        };
    }

    public static Income[] getSampleIncome() {
        return new Income[] {
            new Income(
                    new Description("English Tuition for Aileen"),
                    new Date("08-01-2022"),
                    new Amount("100.00"),
                    new Tag(INCOME_TYPE, "Salary")),
            new Income(
                    new Description("SpaceX Stocks"),
                    new Date("05-01-2022"),
                    new Amount("200.00"),
                    new Tag(INCOME_TYPE, "Investment")),
            new Income(
                    new Description("Monthly Allowance"),
                    new Date("01-01-2022"),
                    new Amount("250.00"),
                    new Tag(INCOME_TYPE, "Allowance"))
        };
    }

    public static ReadOnlyPennyWise getSamplePennyWise() {
        PennyWise samplePennyWise = new PennyWise();
        for (Entry sampleEntry : getSampleExpenditure()) {
            samplePennyWise.addExpenditure(sampleEntry);
        }

        for (Income sampleIncome : getSampleIncome()) {
            samplePennyWise.addIncome(sampleIncome);
        }
        return samplePennyWise;
    }
}
