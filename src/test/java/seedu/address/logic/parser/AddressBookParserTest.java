package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser.ADDITIONAL_REQUEST_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser.ORDER_STATUS_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterOrderCommandParser.PRICE_RANGE_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.COLOR_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.PET_NAME_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.PRICE_PREFIX;
import static seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser.VACCINATION_PREFIX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MatchCommand;
import seedu.address.logic.commands.addcommands.AddBuyerCommand;
import seedu.address.logic.commands.addcommands.AddDelivererCommand;
//import seedu.address.logic.commands.addcommands.AddPetCommand;
import seedu.address.logic.commands.addcommands.AddPetCommand;
import seedu.address.logic.commands.addcommands.AddSupplierCommand;
import seedu.address.logic.commands.deletecommands.DeleteBuyerCommand;
import seedu.address.logic.commands.deletecommands.DeleteDelivererCommand;
import seedu.address.logic.commands.deletecommands.DeleteSupplierCommand;
import seedu.address.logic.commands.editcommands.EditBuyerCommand;
import seedu.address.logic.commands.editcommands.EditCommand;
import seedu.address.logic.commands.editcommands.EditDelivererCommand;
import seedu.address.logic.commands.editcommands.EditSupplierCommand;
import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.findcommandparser.FindBuyerCommandParser;
import seedu.address.logic.parser.findcommandparser.FindDelivererCommandParser;
import seedu.address.logic.parser.findcommandparser.FindSupplierCommandParser;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.predicates.AdditionalRequestPredicate;
import seedu.address.model.order.predicates.OrderStatusPredicate;
import seedu.address.model.order.predicates.PriceRangePredicate;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
//import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.ColorContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PriceContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.SpeciesContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.VaccinationStatusPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.PetBuilder;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalPersonCategories;
import seedu.address.testutil.TypicalSuppliers;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addBuyer() throws Exception {
        Buyer buyer = TypicalBuyers.ALICE;
        AddBuyerCommand command = (AddBuyerCommand) parser.parseCommand(AddBuyerCommand.COMMAND_WORD
                    + " " + PersonUtil.getAddCommandBody(buyer));
        assertEquals(new AddBuyerCommand(buyer, new ArrayList<>()), command);
    }

    @Test
    public void parseCommand_addDeliverer() throws Exception {
        Deliverer deliverer = TypicalDeliverers.BENSON;
        String toBeParsed = AddDelivererCommand.COMMAND_WORD + " " + PersonUtil.getAddCommandBody(deliverer);
        AddDelivererCommand command = (AddDelivererCommand) parser.parseCommand(toBeParsed);
        assertEquals(new AddDelivererCommand(deliverer), command);
    }


    @Test
    public void parseCommand_addSupplier() throws Exception {
        Supplier supplier = TypicalSuppliers.ALICE;
        AddSupplierCommand command = (AddSupplierCommand) parser.parseCommand(AddSupplierCommand.COMMAND_WORD
                + " " + PersonUtil.getAddCommandBody(supplier));
        assertEquals(new AddSupplierCommand(supplier, new ArrayList<>()), command);
    }

    @Test
    public void parseCommand_addPet() throws Exception {
        Pet pet = new PetBuilder().withName("NyankoSensei").build();

        String input = AddPetCommand.COMMAND_WORD
                + "  i/1 "
                + " p_n/" + pet.getName()
                + " p_d/" + pet.getDateOfBirth().getPreferredDateInString()
                + " p_c/" + pet.getColor().getValue()
                + " p_cp/" + pet.getColorPattern().getValue()
                + " p_h/" + pet.getHeight().getValue()
                + " p_w/" + pet.getWeight().getValue()
                + " p_s/" + pet.getSpecies().getValue()
                + " p_v/" + pet.getVaccinationStatus().getVaccinationStatus()
                + " p_p" + pet.getPrice().getPrice();

        AddPetCommand command = (AddPetCommand) parser.parseCommand(input);
        AddPetCommand expected = new AddPetCommand(pet, INDEX_FIRST);
        assertEquals(command, expected);
    }


    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteBuyer() throws Exception {
        DeleteBuyerCommand command = (DeleteBuyerCommand) parser.parseCommand(
                DeleteBuyerCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteBuyerCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteDeliverer() throws Exception {
        DeleteDelivererCommand command = (DeleteDelivererCommand) parser.parseCommand(
                DeleteDelivererCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteDelivererCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteSupplier() throws Exception {
        DeleteSupplierCommand command = (DeleteSupplierCommand) parser.parseCommand(
                DeleteSupplierCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteSupplierCommand(INDEX_FIRST), command);
    }


    @Test
    public void parseCommand_editBuyer() throws Exception {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName("Amy")
                .withAddress("NUS CELC")
                .withEmail("amytoh@gmail.com")
                .build();
        EditCommand command = (EditCommand) parser.parseCommand(EditBuyerCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditBuyerCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_editDeliverer() throws Exception {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName("Amy")
                .withAddress("NUS CELC")
                .withEmail("amytoh@gmail.com")
                .build();
        EditCommand command = (EditCommand) parser.parseCommand(EditDelivererCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditDelivererCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_editSupplier() throws Exception {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName("Amy")
                .withAddress("NUS CELC")
                .withEmail("amytoh@gmail.com")
                .build();
        EditCommand command = (EditCommand) parser.parseCommand(EditSupplierCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditSupplierCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filterPet() throws Exception {
        ColorContainsKeywordsPredicate colorContainsKeywordsPredicate = new ColorContainsKeywordsPredicate(
                Arrays.asList("grey"));
        PetNameContainsKeywordsPredicate petNameContainsKeywordsPredicate = new PetNameContainsKeywordsPredicate(
                Arrays.asList("ashy"));
        PriceContainsKeywordsPredicate priceContainsKeywordsPredicate = new PriceContainsKeywordsPredicate(
                Arrays.asList(5.5));
        SpeciesContainsKeywordsPredicate speciesContainsKeywordsPredicate = new SpeciesContainsKeywordsPredicate(
                Arrays.asList("cat"));
        VaccinationStatusPredicate vaccinationStatusPredicate = new VaccinationStatusPredicate(true);
        FilterPetCommand command = new FilterPetCommand(
                colorContainsKeywordsPredicate,
                petNameContainsKeywordsPredicate,
                priceContainsKeywordsPredicate,
                speciesContainsKeywordsPredicate,
                vaccinationStatusPredicate);
        String input = FilterPetCommand.COMMAND_WORD + " " + COLOR_PREFIX + "/grey "
                + PET_NAME_PREFIX + "/ashy "
                + PRICE_PREFIX + "/5.5 "
                + VACCINATION_PREFIX + "/true";
        assertEquals(parser.parseCommand(input), command);
    }

    @Test
    public void parseCommand_filterOrder() throws Exception {
        AdditionalRequestPredicate additionalRequestPredicate = new AdditionalRequestPredicate(Arrays.asList("fat"));
        OrderStatusPredicate orderStatusPredicate = new OrderStatusPredicate(OrderStatus.DELIVERING);
        PriceRangePredicate priceRangePredicate = new PriceRangePredicate(new Price(34.5), new Price(79.9));
        FilterOrderCommand command = new FilterOrderCommand(
                additionalRequestPredicate,
                orderStatusPredicate,
                priceRangePredicate);
        String input = FilterOrderCommand.COMMAND_WORD + " "
                + ADDITIONAL_REQUEST_PREFIX + "/fat "
                + ORDER_STATUS_PREFIX + "/Delivering "
                + PRICE_RANGE_PREFIX + "/34.5-79.9";
        assertEquals(parser.parseCommand(input), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = PredicateParser.parseBuyer(input);
        Predicate<Deliverer> delivererPredicate = PredicateParser.parseDeliverer(input);
        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(input);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_BUYER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findBuyer() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = PredicateParser.parseBuyer(input);
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Supplier> supplierPredicate = new Predicate<Supplier>() {
            @Override
            public boolean test(Supplier supplier) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        FindCommand command = (FindCommand) parser.parseCommand(
                FindBuyerCommandParser.PARSE_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_BUYER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findDeliverer() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Deliverer> delivererPredicate = PredicateParser.parseDeliverer(input);
        Predicate<Supplier> supplierPredicate = new Predicate<Supplier>() {
            @Override
            public boolean test(Supplier supplier) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        FindCommand command = (FindCommand) parser.parseCommand(
                FindDelivererCommandParser.PARSE_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_DELIVERER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findSupplier() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(input);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindSupplierCommandParser.PARSE_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_SUPPLIER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " all") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " b") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " d") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " s") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " o") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " p") instanceof SortCommand);
    }

    @Test
    public void parseCommand_checkCommand() throws Exception {
        CheckCommand expected = new CheckCommand("BUYER", INDEX_FIRST);
        String input = CheckCommand.COMMAND_WORD + " BUYER 1";
        CheckCommand result = (CheckCommand) parser.parseCommand(input);
        assertEquals(result, expected);

        expected = new CheckCommand("SUPPLIER", INDEX_FIRST);
        input = CheckCommand.COMMAND_WORD + " SUPPLIER 1";
        result = (CheckCommand) parser.parseCommand(input);
        assertEquals(result, expected);

        expected = new CheckCommand("ORDER", INDEX_FIRST);
        input = CheckCommand.COMMAND_WORD + " ORDER 1";
        result = (CheckCommand) parser.parseCommand(input);
        assertEquals(result, expected);
    }

    @Test
    public void parseCommand_matchCommand() throws Exception {
        // index 1 -> success
        MatchCommand expected = new MatchCommand(INDEX_FIRST);
        String input = MatchCommand.COMMAND_WORD + " i/1";
        MatchCommand result = (MatchCommand) parser.parseCommand(input);
        assertEquals(result, expected);

        // index 2 -> success
        expected = new MatchCommand(INDEX_SECOND);
        input = MatchCommand.COMMAND_WORD + " i/2";
        result = (MatchCommand) parser.parseCommand(input);
        assertEquals(result, expected);

        // index 3 -> success
        expected = new MatchCommand(INDEX_THIRD);
        input = MatchCommand.COMMAND_WORD + " i/3";
        result = (MatchCommand) parser.parseCommand(input);
        assertEquals(result, expected);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
