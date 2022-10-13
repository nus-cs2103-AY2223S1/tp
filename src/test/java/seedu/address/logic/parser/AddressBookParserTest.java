package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterLocCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPetCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.LocationContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.pet.PetNameContainsKeywordsPredicate;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalPersonCategories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addBuyer() throws Exception {
        Buyer buyer = TypicalBuyers.ALICE;
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(buyer));
        assertEquals(new AddBuyerCommand(buyer), command);
    }

    //Uncomment the test below when AddDelivererCommand is done
    /*
    @Test
    public void parseCommand_addDeliverer() throws Exception {
        Deliverer deliverer = TypicalDeliverers.BENSON;
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(deliverer));
        assertEquals(new AddDelivererCommand(deliverer), command);
    }

     */

    //Uncomment the test below when AddSupplierCommand is done
    /*
    @Test
    public void parseCommand_addSupplier() throws Exception {
        Supplier supplier = TypicalSuppliers.ALICE;
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(supplier));
        assertEquals(new AddSupplierCommand(supplier), command);
    }

     */

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " c/Buyer i/" + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(TypicalPersonCategories.PERSON_CATEGORY_BUYER, INDEX_FIRST_PERSON), command);
    }

    /*
    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

     */

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filterLoc() throws Exception {
        FilterLocCommand command = new FilterLocCommand(new LocationContainsKeywordsPredicate<>("Singapore"),
                new LocationContainsKeywordsPredicate<>("Singapore"),
                new LocationContainsKeywordsPredicate<>("Singapore"));
        assertEquals(parser.parseCommand(FilterLocCommand.COMMAND_WORD + " Singapore"), command);
    }

    @Test
    public void parseCommand_findBuyer() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " b/ " + keywords.stream().collect(Collectors.joining(" ")));
        FindCommand otherCommand = new FindCommand(new NameContainsKeywordsPredicate<>(keywords),
                new NameContainsKeywordsPredicate<>(keywords),
                new NameContainsKeywordsPredicate<>(keywords), TypicalPersonCategories.PERSON_CATEGORY_BUYER
                );
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findDeliverer() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " d/ " + keywords.stream().collect(Collectors.joining(" ")));
        FindCommand otherCommand = new FindCommand(new NameContainsKeywordsPredicate<>(keywords),
                new NameContainsKeywordsPredicate<>(keywords),
                new NameContainsKeywordsPredicate<>(keywords), TypicalPersonCategories.PERSON_CATEGORY_DELIVERER
        );
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findSupplier() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " s/ " + keywords.stream().collect(Collectors.joining(" ")));
        FindCommand otherCommand = new FindCommand(new NameContainsKeywordsPredicate<>(keywords),
                new NameContainsKeywordsPredicate<>(keywords),
                new NameContainsKeywordsPredicate<>(keywords), TypicalPersonCategories.PERSON_CATEGORY_SUPPLIER
        );
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findPet() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPetCommand command = (FindPetCommand) parser.parseCommand(
                FindPetCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        FindPetCommand otherCommand = new FindPetCommand(new PetNameContainsKeywordsPredicate<>(keywords));
        assertTrue(parser.parseCommand(FindPetCommand.COMMAND_WORD + " Ashy") instanceof FindPetCommand);
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
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
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