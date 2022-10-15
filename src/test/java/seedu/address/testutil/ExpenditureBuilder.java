package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Expenditure;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;



/**
 * A utility class to help with building Expenditure objects.
 */
public class ExpenditureBuilder {

    public static final String DEFAULT_DESC = "Food";
    public static final String DEFAULT_AMT = "5.30";
    public static final String DEFAULT_DATE = "22-09-2022";
    public static final String DEFAULT_TAG = "Meal";

    private Description description;
    private Amount amount;
    private Date date;
    private Set<Tag> tags;

    /**
     * Creates a {@code ExpenditureBuilder} with the default details.
     */
    public ExpenditureBuilder() {
        this.description = new Description(DEFAULT_DESC);
        this.amount = new Amount(DEFAULT_AMT);
        this.date = new Date(DEFAULT_DATE);
        this.tags = new HashSet<>();
        this.tags.add(new seedu.address.model.tag.Tag(DEFAULT_TAG));
    }

    /**
     * Initializes the ExpenditureBuilder with the data of {@code expenditureToCopy}.
     */
    public ExpenditureBuilder(Entry expenditureToCopy) {
        description = expenditureToCopy.getDescription();
        amount = expenditureToCopy.getAmount();
        date = expenditureToCopy.getDate();
        tags = new HashSet<>(expenditureToCopy.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Expenditure build() {
        return new Expenditure(this.description, this.date, this.amount, this.tags);
    }
}
