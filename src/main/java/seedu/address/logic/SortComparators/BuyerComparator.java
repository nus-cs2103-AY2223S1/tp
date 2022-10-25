package seedu.address.logic.SortComparators;

import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Name;
import seedu.address.model.pricerange.PriceRange;

import java.util.Comparator;
import java.util.Optional;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class BuyerComparator implements Comparator<Buyer> {
    Optional<Comparator<Name>> nameComparator;
    Optional<Comparator<PriceRange>> priceRangeComparator;

    public BuyerComparator(Comparator<Name> nameComparator, Comparator<PriceRange> priceRangeComparator) {
        this.nameComparator = Optional.ofNullable(nameComparator);
        this.priceRangeComparator = Optional.ofNullable(priceRangeComparator);
    }
    
    @Override
    public int compare(Buyer firstBuyer, Buyer secondBuyer) {
        if (nameComparator.isPresent()) {
            return nameComparator.get().compare(firstBuyer.getName(), secondBuyer.getName()); 
        }
        // ...
        return 0;
    }
}
