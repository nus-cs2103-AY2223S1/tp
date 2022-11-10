package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Description;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.UniqueIterationList;
import seedu.address.model.tag.Tag;


/**
 * A default commission stub that has all of its methods failing.
 */
public class CommissionStub extends Commission {

    /**
     * Constructs a CommissionStub with the given CommissionBuilder and Customer.
     */
    public CommissionStub(CommissionBuilder builder, Customer customer) {
        super(builder, customer);
        requireNonNull(builder);
    }

    @Override
    public Title getTitle() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<Description> getDescription() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Fee getFee() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Deadline getDeadline() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public CompletionStatus getCompletionStatus() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public CompletionStatusString getCompletionStatusString() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Set<Tag> getTags() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Customer getCustomer() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public UniqueIterationList getIterations() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Iteration> getIterationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isSameCommission(Commission otherCommission) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addIteration(Iteration iteration) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasIteration(Iteration iteration) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setIteration(Iteration target, Iteration editedIteration) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeIteration(Iteration key) {
        throw new AssertionError("This method should not be called.");
    }
}
