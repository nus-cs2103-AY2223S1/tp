package seedu.address.logic.sortcomparators;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Priority;
import seedu.address.model.pricerange.PriceRange;

/**
 * A comparator to compare two Buyers in the list.
 */
public class BuyerComparator implements Comparator<Buyer> {
    private Optional<Comparator<Name>> nameComparator;
    private Optional<Comparator<PriceRange>> priceRangeComparator;
    private Optional<Comparator<Priority>> priorityComparator;

    /**
     * Creates a BuyerComparator object.
     */
    public BuyerComparator(Comparator<Name> nameComparator,
                           Comparator<PriceRange> priceRangeComparator,
                           Comparator<Priority> priorityComparator) {
        this.nameComparator = Optional.ofNullable(nameComparator);
        this.priceRangeComparator = Optional.ofNullable(priceRangeComparator);
        this.priorityComparator = Optional.ofNullable(priorityComparator);
    }

    @Override
    public int compare(Buyer firstBuyer, Buyer secondBuyer) {
        if (nameComparator.isPresent()) {
            return nameComparator.get().compare(firstBuyer.getName(), secondBuyer.getName());
        } else if (priceRangeComparator.isPresent()) {
            return priceRangeComparator.get()
                    .compare(firstBuyer.getPriceRange().get(), secondBuyer.getPriceRange().get());
        } else {
            return priorityComparator.get()
                    .compare(firstBuyer.getPriority(), secondBuyer.getPriority());
        }


    }

    @Override
    public String toString() {
        if (nameComparator.isPresent()) {
            return nameComparator.get().toString();
        } else if (priceRangeComparator.isPresent()){
            return priceRangeComparator.get().toString();
        } else {
            return priorityComparator.get().toString();
        }
    }
}
