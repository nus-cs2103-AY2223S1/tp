package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Supplier;


/**
 * Provides utils for Sort related classes.
 */
public class SortCommandUtil {
    //Comparators for person
    public static final Comparator<Address> ADDRESS_COMPARATOR = (o1, o2) -> o1.compareTo(o2);
    public static final Comparator<Email> EMAIL_COMPARATOR = (o1, o2) -> o1.compareTo(o2);
    public static final Comparator<Location> LOCATION_COMPARATOR = (o1, o2) -> o1.compareTo(o2);
    public static final Comparator<Name> NAME_COMPARATOR = (o1, o2) -> o1.compareTo(o2);
    public static final Comparator<Phone> PHONE_COMPARATOR = (o1, o2) -> o1.compareTo(o2);

    //Comparators for buyer
    public static final Comparator<Buyer> BUYER_COMPARATOR = Buyer::compareTo;

    //Comparators for supplier
    public static final Comparator<Supplier> SUPPLIER_COMPARATOR = Supplier::compareTo;

    //Comparators for deliverer
    public static final Comparator<Deliverer> DELIVERER_COMPARATOR = Deliverer::compareTo;
}
