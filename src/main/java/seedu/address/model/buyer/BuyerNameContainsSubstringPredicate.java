package seedu.address.model.buyer;

import java.util.function.Predicate;

/**
 * Tests that a {@code Buyer}'s {@code Name} contains the given string.
 */
public class BuyerNameContainsSubstringPredicate implements Predicate<Buyer> {
    private final String string;

    public BuyerNameContainsSubstringPredicate(String string) {
        this.string = string.toLowerCase();
    }

    @Override
    public boolean test(Buyer buyer) {
        String name = buyer.getName().fullName.toLowerCase();
        return name.contains(string);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerNameContainsSubstringPredicate // instanceof handles nulls
                && string.equals(((BuyerNameContainsSubstringPredicate) other).string)); // state check
    }
}

