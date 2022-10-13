package seedu.address.model.person;

/**
 * A list of suppliers that enforces uniqueness between its elements and does not allow nulls.
 * A supplier is considered unique by comparing using {@code Supplier#isSameSupplier(Supplier)}.
 * As such, adding and updating of
 * suppliers uses PSupplier#isSameSupplier(Supplier)
 * for equality so as to ensure that the supplier being added or updated is
 * unique in terms of identity in the UniqueSupplierList.
 * However, the removal of a person uses Supplier#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 */
public class UniqueSupplierList extends UniquePersonList<Supplier>{
}
