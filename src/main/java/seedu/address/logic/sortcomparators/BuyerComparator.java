package seedu.address.logic.sortcomparators;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Name;
import seedu.address.model.pricerange.PriceRange;

/**
 * A comparator to compare two Buyers in the list.
 */
public class BuyerComparator implements Comparator<Buyer> {
    private Optional<Comparator<Name>> nameComparator;
    private Optional<Comparator<PriceRange>> priceRangeComparator;

    /**
     * Creates a BuyerComparator object.
     */
    public BuyerComparator(Comparator<Name> nameComparator, Comparator<PriceRange> priceRangeComparator) {
        this.nameComparator = Optional.ofNullable(nameComparator);
        this.priceRangeComparator = Optional.ofNullable(priceRangeComparator);
    }

    @Override
    public int compare(Buyer firstBuyer, Buyer secondBuyer) {
        if (nameComparator.isPresent()) {
            return nameComparator.get().compare(firstBuyer.getName(), secondBuyer.getName());
        }
        // else statement
        // remove "return 0" once implemented
        return 0;
    }

    @Override
    public String toString() {
        if (nameComparator.isPresent()) {
            return nameComparator.get().toString();
        }
        // else statement
        // remove "return """ once implemented
        return "";
    }
}
