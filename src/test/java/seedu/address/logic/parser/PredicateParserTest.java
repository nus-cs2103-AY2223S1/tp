package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.*;

public class PredicateParserTest {
    @Test
    public void parseBuyer_a_addressContainsKeywordsPredicate() {
        AddressContainsKeywordsPredicate<Buyer> expected = new AddressContainsKeywordsPredicate<>(
                Arrays.asList("Wall Street"));
        try {
            Predicate<Buyer> result1 = PredicateParser.parseBuyer("a/ Wall Street");
            Predicate<Buyer> result2 = PredicateParser.parseBuyer("a/Wall Street \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseBuyer_e_emailContainsKeywordsPredicate() {
        EmailContainsKeywordsPredicate<Buyer> expected = new EmailContainsKeywordsPredicate<>(
                Arrays.asList("jane@gmail.com"));
        try {
            Predicate<Buyer> result1 = PredicateParser.parseBuyer("e/ jane@gmail.com");
            Predicate<Buyer> result2 = PredicateParser.parseBuyer("e/jane@gmail.com \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseBuyer_l_locationContainsKeywordsPredicate() {
        LocationContainsKeywordsPredicate<Buyer> expected = new LocationContainsKeywordsPredicate<>(
                Arrays.asList("New York"));
        try {
            Predicate<Buyer> result1 = PredicateParser.parseBuyer("l/ New York");
            Predicate<Buyer> result2 = PredicateParser.parseBuyer("l/New York \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseBuyer_n_nameContainsKeywordsPredicate() {
        NameContainsKeywordsPredicate<Buyer> expected = new NameContainsKeywordsPredicate<>(
                Arrays.asList("Liz"));
        try {
            Predicate<Buyer> result1 = PredicateParser.parseBuyer("n/ Liz");
            Predicate<Buyer> result2 = PredicateParser.parseBuyer("n/Liz \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseBuyer_p_phoneContainsKeywordsPredicate() {
        PhoneContainsKeywordsPredicate<Buyer> expected = new PhoneContainsKeywordsPredicate<>(
                Arrays.asList("999"));
        try {
            Predicate<Buyer> result1 = PredicateParser.parseBuyer("p/ 999");
            Predicate<Buyer> result2 = PredicateParser.parseBuyer("p/999 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseDeliverer_a_addressContainsKeywordsPredicate() {
        AddressContainsKeywordsPredicate<Deliverer> expected = new AddressContainsKeywordsPredicate<>(
                Arrays.asList("Wall Street"));
        try {
            Predicate<Deliverer> result1 = PredicateParser.parseDeliverer("a/ Wall Street");
            Predicate<Deliverer> result2 = PredicateParser.parseDeliverer("a/Wall Street \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseDeliverer_e_emailContainsKeywordsPredicate() {
        EmailContainsKeywordsPredicate<Deliverer> expected = new EmailContainsKeywordsPredicate<>(
                Arrays.asList("jane@gmail.com"));
        try {
            Predicate<Deliverer> result1 = PredicateParser.parseDeliverer("e/ jane@gmail.com");
            Predicate<Deliverer> result2 = PredicateParser.parseDeliverer("e/jane@gmail.com \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseDeliverer_l_locationContainsKeywordsPredicate() {
        LocationContainsKeywordsPredicate<Deliverer> expected = new LocationContainsKeywordsPredicate<>(
                Arrays.asList("New York"));
        try {
            Predicate<Deliverer> result1 = PredicateParser.parseDeliverer("l/ New York");
            Predicate<Deliverer> result2 = PredicateParser.parseDeliverer("l/New York \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseDeliverer_n_nameContainsKeywordsPredicate() {
        NameContainsKeywordsPredicate<Deliverer> expected = new NameContainsKeywordsPredicate<>(
                Arrays.asList("Liz"));
        try {
            Predicate<Deliverer> result1 = PredicateParser.parseDeliverer("n/ Liz");
            Predicate<Deliverer> result2 = PredicateParser.parseDeliverer("n/Liz \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseDeliverer_p_phoneContainsKeywordsPredicate() {
        PhoneContainsKeywordsPredicate<Deliverer> expected = new PhoneContainsKeywordsPredicate<>(
                Arrays.asList("999"));
        try {
            Predicate<Deliverer> result1 = PredicateParser.parseDeliverer("p/ 999");
            Predicate<Deliverer> result2 = PredicateParser.parseDeliverer("p/999 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseSupplier_a_addressContainsKeywordsPredicate() {
        AddressContainsKeywordsPredicate<Supplier> expected = new AddressContainsKeywordsPredicate<>(
                Arrays.asList("Wall Street"));
        try {
            Predicate<Supplier> result1 = PredicateParser.parseSupplier("a/ Wall Street");
            Predicate<Supplier> result2 = PredicateParser.parseSupplier("a/Wall Street \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseSupplier_e_emailContainsKeywordsPredicate() {
        EmailContainsKeywordsPredicate<Supplier> expected = new EmailContainsKeywordsPredicate<>(
                Arrays.asList("jane@gmail.com"));
        try {
            Predicate<Supplier> result1 = PredicateParser.parseSupplier("e/ jane@gmail.com");
            Predicate<Supplier> result2 = PredicateParser.parseSupplier("e/jane@gmail.com \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseSupplier_l_locationContainsKeywordsPredicate() {
        LocationContainsKeywordsPredicate<Supplier> expected = new LocationContainsKeywordsPredicate<>(
                Arrays.asList("New York"));
        try {
            Predicate<Supplier> result1 = PredicateParser.parseSupplier("l/ New York");
            Predicate<Supplier> result2 = PredicateParser.parseSupplier("l/New York \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseSupplier_n_nameContainsKeywordsPredicate() {
        NameContainsKeywordsPredicate<Supplier> expected = new NameContainsKeywordsPredicate<>(
                Arrays.asList("Liz"));
        try {
            Predicate<Supplier> result1 = PredicateParser.parseSupplier("n/ Liz");
            Predicate<Supplier> result2 = PredicateParser.parseSupplier("n/Liz \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseSupplier_p_phoneContainsKeywordsPredicate() {
        PhoneContainsKeywordsPredicate<Supplier> expected = new PhoneContainsKeywordsPredicate<>(
                Arrays.asList("999"));
        try {
            Predicate<Supplier> result1 = PredicateParser.parseSupplier("p/ 999");
            Predicate<Supplier> result2 = PredicateParser.parseSupplier("p/999 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

}
