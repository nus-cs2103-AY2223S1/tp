package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Income;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;



/**
 * A utility class to help with building Income objects.
 */
public class IncomeBuilder {

    public static final String DEFAULT_DESC = "Food";
    public static final String DEFAULT_AMT = "5.30";
    public static final String DEFAULT_DATE = "22-09-2022";

    private Description description;
    private Amount amount;
    private Date date;
    private Set<Tag> tags;

    /**
     * Creates a {@code IncomeBuilder} with the default details.
     */
    public IncomeBuilder() {
        this.description = new Description(DEFAULT_DESC);
        this.amount = new Amount(DEFAULT_AMT);
        this.date = new Date(DEFAULT_DATE);
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the IncomeBuilder with the data of {@code incomeToCopy}.
     */
    public IncomeBuilder(Entry incomeToCopy) {
        description = incomeToCopy.getDescription();
        amount = incomeToCopy.getAmount();
        date = incomeToCopy.getDate();
        tags = new HashSet<>(incomeToCopy.getTags());
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
     * Sets the {@code tags} of the {@code Income} that we are building.
     */
    public IncomeBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Income build() {
        return new Income(this.description, this.date, this.amount, this.tags);
    }
}
