package seedu.pennywise.testutil;

import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.entry.Expenditure;
import seedu.pennywise.model.entry.Income;

/**
 * A utility class to help with building PennyWise objects.
 * Example usage: <br>
 *     {@code PennyWise pennywise = new PennyWiseBuilder().withIncome(
 *          new Expenditure("Lunch", "10-10-2022", "7.20", "Food")).build();}
 */
public class PennyWiseBuilder {

    private PennyWise pennyWise;

    public PennyWiseBuilder() {
        pennyWise = new PennyWise();
    }

    public PennyWiseBuilder(PennyWise pennyWise) {
        this.pennyWise = pennyWise;
    }

    /**
     * Adds a new {@code Expenditure} to the {@code PennyWise} that we are building.
     */
    public PennyWiseBuilder withExpenditure(Expenditure expenditure) {
        pennyWise.addExpenditure(expenditure);
        return this;
    }

    /**
     * Adds a new {@code Income} to the {@code PennyWise} that we are building.
     */
    public PennyWiseBuilder withIncome(Income income) {
        pennyWise.addIncome(income);
        return this;
    }

    public PennyWise build() {
        return pennyWise;
    }
}
