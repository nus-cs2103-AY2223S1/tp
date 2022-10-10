package seedu.address.testutil;

import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.Entry;

public class EntryBuilder {
    public static final String DEFAULT_TYPE = "i";
    public static final String DEFAULT_DESC = "Food";
    public static final String DEFAULT_AMT = "5.30";
    public static final String DEFAULT_DATE = "22-09-2022";

//    private Type type;
    private Description description;
    private Amount amount;
    private Date date;

    public EntryBuilder() {
//        type = new Type(DEFAULT_TYPE);
        this.description = new Description(DEFAULT_DESC);
        this.amount = new Amount(DEFAULT_AMT);
        this.date = new Date(DEFAULT_DATE);
    }

    public EntryBuilder(Entry entryToCopy) {
//        type = entryToCopy.getType();
        description = entryToCopy.getDescription();
        amount = entryToCopy.getAmount();
        date = entryToCopy.getDate();
    }

    public EntryBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

//    public EntryBuilder withAmount()
}
