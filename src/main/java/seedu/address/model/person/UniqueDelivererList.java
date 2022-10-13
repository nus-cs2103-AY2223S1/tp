package seedu.address.model.person;

/**
 * A list of deliverers that enforces uniqueness between its elements and does not allow nulls.
 * A deliverer is considered unique by comparing using {@code Deliverer#isSameDeliverer(Deliverer)}.
 * As such, adding and updating of
 * deliverers uses Deliverer#isSameDeliverer(Deliverer)
 * for equality so as to ensure that the deliverer being added or updated is
 * unique in terms of identity in the UniqueDelivererList.
 * However, the removal of a deliverer uses Deliverer#equals(Object) so
 * as to ensure that the deliverer with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueDelivererList extends UniquePersonList<Deliverer>{
}
