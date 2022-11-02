package seedu.address.logic.parser.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sortcommands.SortBuyerCommand;
import seedu.address.logic.commands.sortcommands.SortDelivererCommand;
import seedu.address.logic.commands.sortcommands.SortOrderCommand;
import seedu.address.logic.commands.sortcommands.SortPetCommand;
import seedu.address.logic.commands.sortcommands.SortSupplierCommand;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalOrders;
import seedu.address.testutil.TypicalPets;
import seedu.address.testutil.TypicalSuppliers;

public class SortCommandParserUtilTest {

    @Test
    public void parseToSelectedBuyerComparator() {
        Comparator<Buyer> buyerComparator;

        //Compare by Address
        for (String param : CommandUtil.ACCEPTABLE_SORT_ADDRESS_PARAMETER) {
            try {
                buyerComparator = SortCommandParserUtil.parseToSelectedBuyerComparator(param);
                int expected = TypicalBuyers.ALICE.getAddress().compareTo(TypicalBuyers.DANIEL.getAddress());
                int result = buyerComparator.compare(TypicalBuyers.ALICE, TypicalBuyers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Email
        for (String param : CommandUtil.ACCEPTABLE_SORT_EMAIL_PARAMETER) {
            try {
                buyerComparator = SortCommandParserUtil.parseToSelectedBuyerComparator(param);
                int expected = TypicalBuyers.ALICE.getEmail().compareTo(TypicalBuyers.DANIEL.getEmail());
                int result = buyerComparator.compare(TypicalBuyers.ALICE, TypicalBuyers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Location
        for (String param : CommandUtil.ACCEPTABLE_SORT_LOCATION_PARAMETER) {
            try {
                buyerComparator = SortCommandParserUtil.parseToSelectedBuyerComparator(param);
                int expected = TypicalBuyers.ALICE.getLocation().compareTo(TypicalBuyers.DANIEL.getLocation());
                int result = buyerComparator.compare(TypicalBuyers.ALICE, TypicalBuyers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Name
        for (String param : CommandUtil.ACCEPTABLE_SORT_NAME_PARAMETER) {
            try {
                buyerComparator = SortCommandParserUtil.parseToSelectedBuyerComparator(param);
                int expected = TypicalBuyers.ALICE.getName().compareTo(TypicalBuyers.DANIEL.getName());
                int result = buyerComparator.compare(TypicalBuyers.ALICE, TypicalBuyers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Phone
        for (String param : CommandUtil.ACCEPTABLE_SORT_PHONE_PARAMETER) {
            try {
                buyerComparator = SortCommandParserUtil.parseToSelectedBuyerComparator(param);
                int expected = TypicalBuyers.ALICE.getPhone().compareTo(TypicalBuyers.DANIEL.getPhone());
                int result = buyerComparator.compare(TypicalBuyers.ALICE, TypicalBuyers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }
    }

    @Test
    public void parseToSelectedBuyer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> SortCommandParserUtil.parseToSelectedBuyerComparator(null));
    }

    @Test
    public void parseToSelectedBuyer_invalidArgs_throwsNullPointerException() {
        String expected = String.format(SortBuyerCommand.MESSAGE_WRONG_ATTRIBUTE, "nfjrf",
                SortBuyerCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, ()
                -> SortCommandParserUtil.parseToSelectedBuyerComparator("nfjrf"));
    }

    @Test
    public void parseToSelectedBuyer_emptyString_returnsBuyerComparator() {
        try {
            Comparator<Buyer> buyerComparator = SortCommandParserUtil.parseToSelectedBuyerComparator("");
            int expected = TypicalBuyers.ALICE.compareTo(TypicalBuyers.DANIEL);
            int result = buyerComparator.compare(TypicalBuyers.ALICE, TypicalBuyers.DANIEL);
            assertEquals(result, expected);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseToSelectedSupplierComparator() {
        Comparator<Supplier> supplierComparator;

        //Compare by Address
        for (String param : CommandUtil.ACCEPTABLE_SORT_ADDRESS_PARAMETER) {
            try {
                supplierComparator = SortCommandParserUtil.parseToSelectedSupplierComparator(param);
                int expected = TypicalSuppliers.ALICE.getAddress().compareTo(TypicalSuppliers.DANIEL.getAddress());
                int result = supplierComparator.compare(TypicalSuppliers.ALICE, TypicalSuppliers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Email
        for (String param : CommandUtil.ACCEPTABLE_SORT_EMAIL_PARAMETER) {
            try {
                supplierComparator = SortCommandParserUtil.parseToSelectedSupplierComparator(param);
                int expected = TypicalSuppliers.ALICE.getEmail().compareTo(TypicalSuppliers.DANIEL.getEmail());
                int result = supplierComparator.compare(TypicalSuppliers.ALICE, TypicalSuppliers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Location
        for (String param : CommandUtil.ACCEPTABLE_SORT_LOCATION_PARAMETER) {
            try {
                supplierComparator = SortCommandParserUtil.parseToSelectedSupplierComparator(param);
                int expected = TypicalBuyers.ALICE.getLocation().compareTo(TypicalBuyers.DANIEL.getLocation());
                int result = supplierComparator.compare(TypicalSuppliers.ALICE, TypicalSuppliers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Name
        for (String param : CommandUtil.ACCEPTABLE_SORT_NAME_PARAMETER) {
            try {
                supplierComparator = SortCommandParserUtil.parseToSelectedSupplierComparator(param);
                int expected = TypicalSuppliers.ALICE.getName().compareTo(TypicalSuppliers.DANIEL.getName());
                int result = supplierComparator.compare(TypicalSuppliers.ALICE, TypicalSuppliers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Phone
        for (String param : CommandUtil.ACCEPTABLE_SORT_PHONE_PARAMETER) {
            try {
                supplierComparator = SortCommandParserUtil.parseToSelectedSupplierComparator(param);
                int expected = TypicalSuppliers.ALICE.getPhone().compareTo(TypicalSuppliers.DANIEL.getPhone());
                int result = supplierComparator.compare(TypicalSuppliers.ALICE, TypicalSuppliers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }
    }

    @Test
    public void parseToSelectedSupplierComparator_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> SortCommandParserUtil.parseToSelectedSupplierComparator(null));
    }

    @Test
    public void parseToSelectedSupplierComparator_invalidArgs_throwsNullPointerException() {
        String expected = String.format(SortSupplierCommand.MESSAGE_WRONG_ATTRIBUTE, "nfjrf",
                SortSupplierCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, ()
                -> SortCommandParserUtil.parseToSelectedSupplierComparator("nfjrf"));
    }

    @Test
    public void parseToSelectedSupplierComparator_emptyString_returnsSupplierComparator() {
        try {
            Comparator<Supplier> supplierComparator = SortCommandParserUtil
                    .parseToSelectedSupplierComparator("");
            int expected = TypicalSuppliers.ALICE.compareTo(TypicalSuppliers.DANIEL);
            int result = supplierComparator.compare(TypicalSuppliers.ALICE, TypicalSuppliers.DANIEL);
            assertEquals(result, expected);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseToSelectedDelivererComparator() {
        Comparator<Deliverer> delivererComparator;

        //Compare by Address
        for (String param : CommandUtil.ACCEPTABLE_SORT_ADDRESS_PARAMETER) {
            try {
                delivererComparator = SortCommandParserUtil.parseToSelectedDelivererComparator(param);
                int expected = TypicalDeliverers.ALICE.getAddress().compareTo(TypicalDeliverers.DANIEL.getAddress());
                int result = delivererComparator.compare(TypicalDeliverers.ALICE, TypicalDeliverers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Email
        for (String param : CommandUtil.ACCEPTABLE_SORT_EMAIL_PARAMETER) {
            try {
                delivererComparator = SortCommandParserUtil.parseToSelectedDelivererComparator(param);
                int expected = TypicalDeliverers.ALICE.getEmail().compareTo(TypicalDeliverers.DANIEL.getEmail());
                int result = delivererComparator.compare(TypicalDeliverers.ALICE, TypicalDeliverers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Location
        for (String param : CommandUtil.ACCEPTABLE_SORT_LOCATION_PARAMETER) {
            try {
                delivererComparator = SortCommandParserUtil.parseToSelectedDelivererComparator(param);
                int expected = TypicalDeliverers.ALICE.getLocation().compareTo(TypicalDeliverers.DANIEL.getLocation());
                int result = delivererComparator.compare(TypicalDeliverers.ALICE, TypicalDeliverers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Name
        for (String param : CommandUtil.ACCEPTABLE_SORT_NAME_PARAMETER) {
            try {
                delivererComparator = SortCommandParserUtil.parseToSelectedDelivererComparator(param);
                int expected = TypicalDeliverers.ALICE.getName().compareTo(TypicalDeliverers.DANIEL.getName());
                int result = delivererComparator.compare(TypicalDeliverers.ALICE, TypicalDeliverers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Phone
        for (String param : CommandUtil.ACCEPTABLE_SORT_PHONE_PARAMETER) {
            try {
                delivererComparator = SortCommandParserUtil.parseToSelectedDelivererComparator(param);
                int expected = TypicalDeliverers.ALICE.getPhone().compareTo(TypicalDeliverers.DANIEL.getPhone());
                int result = delivererComparator.compare(TypicalDeliverers.ALICE, TypicalDeliverers.DANIEL);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }
    }

    @Test
    public void parseToSelectedDelivererComparator_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> SortCommandParserUtil.parseToSelectedDelivererComparator(null));
    }

    @Test
    public void parseToSelectedDelivererComparator_invalidArgs_throwsNullPointerException() {
        String expected = String.format(SortDelivererCommand.MESSAGE_WRONG_ATTRIBUTE, "nfjrf",
                SortSupplierCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, ()
                -> SortCommandParserUtil.parseToSelectedDelivererComparator("nfjrf"));
    }

    @Test
    public void parseToSelectedDelivererComparator_emptyString_returnsDelivererComparator() {
        try {
            Comparator<Deliverer> delivererComparator = SortCommandParserUtil
                    .parseToSelectedDelivererComparator("");
            int expected = TypicalDeliverers.ALICE.compareTo(TypicalDeliverers.DANIEL);
            int result = delivererComparator.compare(TypicalDeliverers.ALICE, TypicalDeliverers.DANIEL);
            assertEquals(result, expected);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseToSelectedOrderComparator() {
        Comparator<Order> orderComparator;

        //Compare by Price Range
        for (String param : CommandUtil.ACCEPTABLE_SORT_PRICE_RANGE_PARAMETER) {
            try {
                orderComparator = SortCommandParserUtil.parseToSelectedOrderComparator(param);
                int expected = TypicalOrders.ORDER_1.getRequestedPriceRange()
                        .compareTo(TypicalOrders.ORDER_2.getRequestedPriceRange());
                int result = orderComparator.compare(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Price
        for (String param : CommandUtil.ACCEPTABLE_SORT_PRICE_PARAMETER) {
            try {
                orderComparator = SortCommandParserUtil.parseToSelectedOrderComparator(param);
                int expected = TypicalOrders.ORDER_1.getSettledPrice()
                        .compareTo(TypicalOrders.ORDER_2.getSettledPrice());
                int result = orderComparator.compare(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Status
        for (String param : CommandUtil.ACCEPTABLE_SORT_STATUS_PARAMETER) {
            try {
                orderComparator = SortCommandParserUtil.parseToSelectedOrderComparator(param);
                int expected = TypicalOrders.ORDER_1.getOrderStatus().compareTo(TypicalOrders.ORDER_2.getOrderStatus());
                int result = orderComparator.compare(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Name
        for (String param : CommandUtil.ACCEPTABLE_SORT_DUE_DATE_PARAMETER) {
            try {
                orderComparator = SortCommandParserUtil.parseToSelectedOrderComparator(param);
                int expected = TypicalOrders.ORDER_1.getByDate().compareTo(TypicalOrders.ORDER_2.getByDate());
                int result = orderComparator.compare(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }
    }

    @Test
    public void parseToSelectedOrderComparator_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> SortCommandParserUtil.parseToSelectedOrderComparator(null));
    }

    @Test
    public void parseToSelectedOrderComparator_invalidArgs_throwsNullPointerException() {
        String expected = String.format(SortOrderCommand.MESSAGE_WRONG_ATTRIBUTE, "nfjrf",
                SortOrderCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, ()
                -> SortCommandParserUtil.parseToSelectedOrderComparator("nfjrf"));
    }

    @Test
    public void parseToSelectedOrderComparator_emptyString_returnsOrderComparator() {
        try {
            Comparator<Order> orderComparator = SortCommandParserUtil.parseToSelectedOrderComparator("");
            int expected = TypicalOrders.ORDER_1.compareTo(TypicalOrders.ORDER_2);
            int result = orderComparator.compare(TypicalOrders.ORDER_1, TypicalOrders.ORDER_2);
            assertEquals(result, expected);
        } catch (ParseException e) {
            assert false;
        }
    }

    @Test
    public void parseToSelectedPetComparator() {
        Comparator<Pet> petComparator;

        //Compare by Name
        for (String param : CommandUtil.ACCEPTABLE_SORT_NAME_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getName().compareTo(TypicalPets.PLUM.getName());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Color
        for (String param : CommandUtil.ACCEPTABLE_SORT_COLOR_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getColor().compareTo(TypicalPets.PLUM.getColor());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Color Pattern
        for (String param : CommandUtil.ACCEPTABLE_SORT_COLOR_PATTERN_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getColorPattern().compareTo(TypicalPets.PLUM.getColorPattern());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Date of Birth
        for (String param : CommandUtil.ACCEPTABLE_SORT_BIRTH_DATE_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getDateOfBirth().compareTo(TypicalPets.PLUM.getDateOfBirth());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Species
        for (String param : CommandUtil.ACCEPTABLE_SORT_SPECIES_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getSpecies().compareTo(TypicalPets.PLUM.getSpecies());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Height
        for (String param : CommandUtil.ACCEPTABLE_SORT_HEIGHT_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getHeight().compareTo(TypicalPets.PLUM.getHeight());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Weight
        for (String param : CommandUtil.ACCEPTABLE_SORT_WEIGHT_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getWeight().compareTo(TypicalPets.PLUM.getWeight());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }

        //Compare by Price
        for (String param : CommandUtil.ACCEPTABLE_SORT_PRICE_PARAMETER) {
            try {
                petComparator = SortCommandParserUtil.parseToSelectedPetComparator(param);
                int expected = TypicalPets.DOJA.getPrice().compareTo(TypicalPets.PLUM.getPrice());
                int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
                assertEquals(result, expected);
            } catch (ParseException e) {
                assert false;
            }
        }
    }

    @Test
    public void parseToSelectedPetComparator_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> SortCommandParserUtil.parseToSelectedPetComparator(null));
    }

    @Test
    public void parseToSelectedPetComparator_invalidArgs_throwsNullPointerException() {
        String expected = String.format(SortPetCommand.MESSAGE_WRONG_ATTRIBUTE, "nfjrf",
                SortPetCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expected, ()
                -> SortCommandParserUtil.parseToSelectedPetComparator("nfjrf"));
    }

    @Test
    public void parseToSelectedPetComparator_emptyString_returnsOrderComparator() {
        try {
            Comparator<Pet> petComparator = SortCommandParserUtil.parseToSelectedPetComparator("");
            int expected = TypicalPets.DOJA.getPrice().compareTo(TypicalPets.PLUM.getPrice());
            int result = petComparator.compare(TypicalPets.DOJA, TypicalPets.PLUM);
            assertEquals(result, expected);
        } catch (ParseException e) {
            assert false;
        }
    }
}
