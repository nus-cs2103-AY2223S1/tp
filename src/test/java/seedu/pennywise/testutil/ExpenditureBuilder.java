package seedu.pennywise.testutil;

import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TAG_MEAL;

import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Expenditure;
import seedu.pennywise.model.entry.Tag;

/**
 * A utility class to help with building Expenditure objects.
 */
public class ExpenditureBuilder {

    public static final String DEFAULT_TYPE = "e";
    public static final String DEFAULT_DESC = "Food";
    public static final String DEFAULT_AMT = "5.30";
    public static final String DEFAULT_DATE = "22-09-2022";

    private Description description;
    private Amount amount;
    private Date date;
    private Tag tag;
    private final EntryType type = new EntryType(DEFAULT_TYPE);
    /**
     * Creates a {@code ExpenditureBuilder} with the default details.
     */
    public ExpenditureBuilder() {
        this.description = new Description(DEFAULT_DESC);
        this.amount = new Amount(DEFAULT_AMT);
        this.date = new Date(DEFAULT_DATE);
        this.tag = new Tag(type, VALID_TAG_MEAL);
    }

    /**
     * Initializes the ExpenditureBuilder with the data of {@code expenditureToCopy}.
     */
    public ExpenditureBuilder(Entry expenditureToCopy) {
        description = expenditureToCopy.getDescription();
        amount = expenditureToCopy.getAmount();
        date = expenditureToCopy.getDate();
        tag = expenditureToCopy.getTag();
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
     * Sets the {@code tag} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withTag(String tag) {
        this.tag = new Tag(new EntryType(DEFAULT_TYPE), tag);
        return this;
    }

    public Expenditure build() {
        return new Expenditure(this.description, this.date, this.amount, this.tag);
    }
}
