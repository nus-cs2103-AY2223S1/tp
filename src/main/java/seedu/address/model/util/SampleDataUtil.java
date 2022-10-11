package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PennyWise;
import seedu.address.model.ReadOnlyPennyWise;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Expenditure;
import seedu.address.model.entry.Income;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PennyWise} with sample data.
 */
public class SampleDataUtil {
    public static Expenditure[] getSampleExpenditure() {
        return new Expenditure[] {
            new Expenditure(
                    new Description("Lunch"),
                    new Date("20-01-2022"),
                    new Amount("20"),
                    getTagSet("Lunch")),
            new Expenditure(
                    new Description("Dinner"),
                    new Date("20-02-2022"),
                    new Amount("30"),
                    getTagSet("Dinner")),
            new Expenditure(
                    new Description("Breakfast"),
                    new Date("21-01-2022"),
                    new Amount("40"),
                    getTagSet("Breakfast")),
            new Expenditure(
                    new Description("Paid this guy"),
                    new Date("21-01-2022"),
                    new Amount("0.10"),
                    getTagSet("paid"))
        };
    }

    public static Income[] getSampleIncome() {
        return new Income[] {
            new Income(
                    new Description("Tutoring"),
                    new Date("01-01-2022"),
                    new Amount("100"),
                    getTagSet("tutoring"))
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

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
