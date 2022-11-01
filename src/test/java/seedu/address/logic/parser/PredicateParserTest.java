package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.COLOR_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.PET_NAME_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.PRICE_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.SPECIES_PREFIX;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.ColorContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PriceContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.SpeciesContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.VaccinationStatusPredicate;
import seedu.address.testutil.Assert;

public class PredicateParserTest {
    @Test
    public void parseBuyer_address_addressContainsKeywordsPredicate() {
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
    public void parseBuyer_email_emailContainsKeywordsPredicate() {
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
    public void parseBuyer_location_locationContainsKeywordsPredicate() {
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
    public void parseBuyer_name_nameContainsKeywordsPredicate() {
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
    public void parseBuyer_phone_phoneContainsKeywordsPredicate() {
        PhoneContainsKeywordsPredicate<Buyer> expected = new PhoneContainsKeywordsPredicate<>(
                Arrays.asList("999"));
        try {
            Predicate<Buyer> result1 = PredicateParser.parseBuyer("ph/ 999");
            Predicate<Buyer> result2 = PredicateParser.parseBuyer("ph/999 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseBuyer_emptyString_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseBuyer(""));
    }

    @Test
    public void parseBuyer_invalidPrefix_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseBuyer("z/"));
    }

    @Test
    public void parseBuyer_emptyArguments_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseBuyer("s/"));
    }

    @Test
    public void parseDeliverer_addresss_addressContainsKeywordsPredicate() {
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
    public void parseDeliverer_email_emailContainsKeywordsPredicate() {
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
    public void parseDeliverer_location_locationContainsKeywordsPredicate() {
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
    public void parseDeliverer_name_nameContainsKeywordsPredicate() {
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
    public void parseDeliverer_phone_phoneContainsKeywordsPredicate() {
        PhoneContainsKeywordsPredicate<Deliverer> expected = new PhoneContainsKeywordsPredicate<>(
                Arrays.asList("999"));
        try {
            Predicate<Deliverer> result1 = PredicateParser.parseDeliverer("ph/ 999");
            Predicate<Deliverer> result2 = PredicateParser.parseDeliverer("ph/999 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseDeliverer_emptyString_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseDeliverer(""));
    }

    @Test
    public void parseDeliverer_invalidPrefix_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseDeliverer("z/"));
    }

    @Test
    public void parseDeliverer_emptyArguments_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseDeliverer("s/"));
    }

    @Test
    public void parseSupplier_address_addressContainsKeywordsPredicate() {
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
    public void parseSupplier_email_emailContainsKeywordsPredicate() {
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
    public void parseSupplier_location_locationContainsKeywordsPredicate() {
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
    public void parseSupplier_name_nameContainsKeywordsPredicate() {
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
    public void parseSupplier_phone_phoneContainsKeywordsPredicate() {
        PhoneContainsKeywordsPredicate<Supplier> expected = new PhoneContainsKeywordsPredicate<>(
                Arrays.asList("999"));
        try {
            Predicate<Supplier> result1 = PredicateParser.parseSupplier("ph/ 999");
            Predicate<Supplier> result2 = PredicateParser.parseSupplier("ph/999 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parseSupplier_emptyString_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseSupplier(""));
    }

    @Test
    public void parseSupplier_invalidPrefix_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseSupplier("z/"));
    }

    @Test
    public void parseSupplier_emptyArguments_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), () -> PredicateParser.parseSupplier("s/"));
    }

    //Pet tests
    @Test
    public void parsePet_color_colorContainsKeywordsPredicate() {
        ColorContainsKeywordsPredicate<Pet> expected = new ColorContainsKeywordsPredicate<>(
                Arrays.asList("white"));
        try {
            Predicate<Pet> result1 = PredicateParser.parsePet(COLOR_PREFIX + "/ white");
            Predicate<Pet> result2 = PredicateParser.parsePet(COLOR_PREFIX + "/white \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parsePet_name_nameContainsKeywordsPredicate() {
        PetNameContainsKeywordsPredicate<Pet> expected = new PetNameContainsKeywordsPredicate<>(
                Arrays.asList("plum"));
        try {
            Predicate<Pet> result1 = PredicateParser.parsePet(PET_NAME_PREFIX + "/ plum");
            Predicate<Pet> result2 = PredicateParser.parsePet(PET_NAME_PREFIX + "/plum \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parsePet_price_priceContainsKeywordsPredicate() {
        PriceContainsKeywordsPredicate<Pet> expected = new PriceContainsKeywordsPredicate<>(
                Arrays.asList(5.67));
        try {
            Predicate<Pet> result1 = PredicateParser.parsePet(PRICE_PREFIX + "/ 5.67");
            Predicate<Pet> result2 = PredicateParser.parsePet(PRICE_PREFIX + "/ 5.67 \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parsePet_species_speciesContainsKeywordsPredicate() {
        SpeciesContainsKeywordsPredicate<Pet> expected = new SpeciesContainsKeywordsPredicate<>(
                Arrays.asList("cat"));
        try {
            Predicate<Pet> result1 = PredicateParser.parsePet(SPECIES_PREFIX + "/ cat");
            Predicate<Pet> result2 = PredicateParser.parsePet(SPECIES_PREFIX + "/cat \n");
            assertEquals(result1, expected);
            assertEquals(result2, expected);
        } catch (IllegalValueException e) {
            assert false;
        }
    }

    @Test
    public void parsePet_emptyString_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterPetCommand.MESSAGE_USAGE), () -> PredicateParser.parsePet(""));
    }

    @Test
    public void parsePet_invalidPrefix_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterPetCommand.MESSAGE_USAGE), () -> PredicateParser.parsePet("z/"));
    }

    @Test
    public void parsePet_emptyArguments_throwParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterPetCommand.MESSAGE_USAGE), () -> PredicateParser.parsePet("s/"));
    }

}
