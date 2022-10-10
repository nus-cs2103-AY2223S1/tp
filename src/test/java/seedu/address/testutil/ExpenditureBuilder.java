package seedu.address.testutil;

import seedu.address.model.entry.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

public class ExpenditureBuilder {

    public static final String DEFAULT_DESC = "Food";
    public static final String DEFAULT_AMT = "5.30";
    public static final String DEFAULT_DATE = "22-09-2022";

    private Description description;
    private Amount amount;
    private Date date;
    private Set<Tag> tags;
    public ExpenditureBuilder() {
        this.description = new Description(DEFAULT_DESC);
        this.amount = new Amount(DEFAULT_AMT);
        this.date = new Date(DEFAULT_DATE);
        this.tags = new HashSet<>();
    }

    public ExpenditureBuilder(Entry entryToCopy) {
        description = entryToCopy.getDescription();
        amount = entryToCopy.getAmount();
        date = entryToCopy.getDate();
        tags = new HashSet<>(entryToCopy.getTags());
    }

    public ExpenditureBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public ExpenditureBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    public ExpenditureBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }
    public ExpenditureBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Expenditure build() {
        return new Expenditure(this.description, this.date, this.amount, this.tags);
    }
}
