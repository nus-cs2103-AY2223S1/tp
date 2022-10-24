package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;

import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.Income;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Income objects.
 */
public class IncomeBuilder {

    public static final String DEFAULT_DESC = "Sell Clothes";
    public static final String DEFAULT_AMT = "10.80";
    public static final String DEFAULT_DATE = "08-10-2022";
    public static final String DEFAULT_TAG = "Profit";
    public static final String DEFAULT_TYPE = VALID_TYPE_INCOME;

    private Description description;
    private Amount amount;
    private Date date;
    private Tag tag;

    /**
     * Creates a {@code IncomeBuilder} with the default details.
     */
    public IncomeBuilder() {
        this.description = new Description(DEFAULT_DESC);
        this.amount = new Amount(DEFAULT_AMT);
        this.date = new Date(DEFAULT_DATE);
        this.tag = new Tag(new EntryType(DEFAULT_TYPE), DEFAULT_TAG);
    }

    /**
     * Initializes the IncomeBuilder with the data of {@code incomeToCopy}.
     */
    public IncomeBuilder(Entry incomeToCopy) {
        description = incomeToCopy.getDescription();
        amount = incomeToCopy.getAmount();
        date = incomeToCopy.getDate();
        tag = incomeToCopy.getTag();
    }

    /**
     * Sets the {@code Description} of the {@code Income} that we are building.
     */
    public IncomeBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Income} that we are building.
     */
    public IncomeBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Income} that we are building.
     */
    public IncomeBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code tag} of the {@code Income} that we are building.
     */
    public IncomeBuilder withTag(String tag) {
        this.tag = new Tag(new EntryType(DEFAULT_TYPE), tag);
        return this;
    }

    public Income build() {
        return new Income(this.description, this.date, this.amount, this.tag);
    }
}
