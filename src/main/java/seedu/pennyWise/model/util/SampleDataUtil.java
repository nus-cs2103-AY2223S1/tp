package seedu.pennyWise.model.util;

import seedu.pennyWise.model.PennyWise;
import seedu.pennyWise.model.ReadOnlyPennyWise;
import seedu.pennyWise.model.entry.Amount;
import seedu.pennyWise.model.entry.Date;
import seedu.pennyWise.model.entry.Description;
import seedu.pennyWise.model.entry.Entry;
import seedu.pennyWise.model.entry.EntryType;
import seedu.pennyWise.model.entry.Expenditure;
import seedu.pennyWise.model.entry.Income;
import seedu.pennyWise.model.entry.Tag;

/**
 * Contains utility methods for populating {@code PennyWise} with sample data.
 */
public class SampleDataUtil {
    public static final EntryType EXPENDITURE_TYPE = new EntryType("e");
    public static final EntryType INCOME_TYPE = new EntryType("i");

    public static Expenditure[] getSampleExpenditure() {
        return new Expenditure[] {
            new Expenditure(
                    new Description("Lunch"),
                    new Date("20-01-2022"),
                    new Amount("20"),
                    new Tag(EXPENDITURE_TYPE, "Food")),
            new Expenditure(
                    new Description("Dinner"),
                    new Date("20-02-2022"),
                    new Amount("30"),
                    new Tag(EXPENDITURE_TYPE, "Food")),
            new Expenditure(
                    new Description("Breakfast"),
                    new Date("21-01-2022"),
                    new Amount("40"),
                    new Tag(EXPENDITURE_TYPE, "Food")),
            new Expenditure(
                    new Description("Paid this guy"),
                    new Date("21-01-2022"),
                    new Amount("0.10"),
                    new Tag(EXPENDITURE_TYPE, "Others"))
        };
    }

    public static Income[] getSampleIncome() {
        return new Income[] {
            new Income(
                    new Description("Tutoring"),
                    new Date("01-01-2022"),
                    new Amount("100"),
                    new Tag(INCOME_TYPE, "Salary"))
        };
    }

    public static ReadOnlyPennyWise getSamplePennyWise() {
        PennyWise sampleAb = new PennyWise();
        for (Entry sampleEntry : getSampleExpenditure()) {
            sampleAb.addExpenditure(sampleEntry);
        }

        for (Income sampleIncome : getSampleIncome()) {
            sampleAb.addIncome(sampleIncome);
        }
        return sampleAb;
    }
}
