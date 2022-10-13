package seedu.address.model.person;

/**
 * A list of buyers that enforces uniqueness between its elements and does not allow nulls.
 * A buyer is considered unique by comparing using {@code Buyer#isSameBuyer(Buyer)}.
 * As such, adding and updating of
 * buyers uses Buyer#isSameBuyer(Buyer)
 * for equality so as to ensure that the buyer being added or updated is
 * unique in terms of identity in the UniqueBuyerList.
 * However, the removal of a buyer uses Buyer#equals(Object) so
 * as to ensure that the buyer with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueBuyerList extends UniquePersonList<Buyer> {

}
