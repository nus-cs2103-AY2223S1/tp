package seedu.address.testutil;

import static seedu.address.testutil.TypicalCustomers.AMY;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commission.Commission;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Description;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Customer objects.
 */
public class CommissionBuilder {


    public static final String DEFAULT_TITLE = "Dog";
    public static final String DEFAULT_DESCRIPTION = "Picture of Dog";
    public static final Double DEFAULT_FEE = 10.0;
    public static final LocalDate DEFAULT_DEADLINE = LocalDate.of(2022, 1, 1);
    public static final Boolean DEFAULT_COMPLETION_STATUS = false;
    public static final Customer DEFAULT_CUSTOMER = AMY;

    private Title title;
    private Description description;
    private Fee fee;
    private final Deadline deadline;
    private CompletionStatus completionStatus;
    private Customer customer;
    private Set<Tag> tags;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CommissionBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        fee = new Fee(DEFAULT_FEE);
        deadline = new Deadline(DEFAULT_DEADLINE);
        completionStatus = new CompletionStatus(DEFAULT_COMPLETION_STATUS);
        tags = new HashSet<>();
        customer = DEFAULT_CUSTOMER;
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CommissionBuilder(Commission commissionToCopy) {
        title = commissionToCopy.getTitle();
        description = commissionToCopy.getDescription();
        fee = commissionToCopy.getFee();
        deadline = commissionToCopy.getDeadline();
        completionStatus = commissionToCopy.getCompletionStatus();
        tags = new HashSet<>(commissionToCopy.getTags());
        customer = commissionToCopy.getCustomer();
    }

    /**
     * Sets the {@code Title} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Commission} that we are building.
     */
    public CommissionBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Commission} that we are building to null.
     */
    public CommissionBuilder withoutDescription() {
        this.description = null;
        return this;
    }

    /**
     * Sets the {@code Fee} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withFee(Double fee) {
        this.fee = new Fee(fee);
        return this;
    }

    /**
     * Sets the {@code Customer} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CommissionBuilder withCompletionStatus(boolean status) {
        this.completionStatus = new CompletionStatus(status);
        return this;
    }

    /**
     * Builds a customer with the given details.
     *
     * @return a customer with the given details.
     */
    public Commission build() {
        return new Commission(title, description, fee, deadline, tags, completionStatus, customer);
    }

}
