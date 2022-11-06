package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCUPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddToFavCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EmailAllCommand;
import seedu.address.logic.commands.ExcludeCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.IncludeCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.OpenCommand;
import seedu.address.logic.commands.PreferCommand;
import seedu.address.logic.commands.SocialCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywords;
import seedu.address.model.person.PersonContainsSocial;
import seedu.address.storage.HistoryList;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_deleteOccupation() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + PREFIX_OCCUPATION.getPrefix());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON, "o/"), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_deletePhone() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_PHONE.getPrefix());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON, "p/"), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_deleteEmail() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_EMAIL.getPrefix());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON, "e/"), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_deleteAddress() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_ADDRESS.getPrefix());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON, "a/"), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_deleteTag() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG.getPrefix());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON, "t/"), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().withGroups("friends").build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));

        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new PersonContainsKeywords(keywords)), command);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_sortName() throws Exception {
        assertTrue(parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_NAME.getPrefix()) instanceof SortCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_sortPhone() throws Exception {
        assertTrue(parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_PHONE.getPrefix()) instanceof SortCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_sortEmail() throws Exception {
        assertTrue(parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_EMAIL.getPrefix()) instanceof SortCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_include() throws Exception {
        String social = "TELEGRAM";
        String link = "Alex";
        assertTrue(parser.parseCommand(
                IncludeCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_SOCIAL.getPrefix() + social + " #/" + link) instanceof IncludeCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_exclude() throws Exception {
        String social = "TELEGRAM";
        String link = "Alex";
        assertTrue(parser.parseCommand(
                ExcludeCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_SOCIAL.getPrefix() + social + " #/" + link) instanceof ExcludeCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_prefer() throws Exception {
        String social = "TELEGRAM";
        assertTrue(parser.parseCommand(
                PreferCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_SOCIAL.getPrefix() + social) instanceof PreferCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_open() throws Exception {
        String social = "TELEGRAM";
        assertTrue(parser.parseCommand(
                OpenCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PREFIX_SOCIAL.getPrefix() + social) instanceof OpenCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_social() throws Exception {
        String social = "TELEGRAM";
        PersonContainsSocial personContainsSocial = new PersonContainsSocial(social);
        assertTrue(parser.parseCommand(
                SocialCommand.COMMAND_WORD + " " + social) instanceof SocialCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_addToFav() throws Exception {
        assertTrue(parser.parseCommand(
                AddToFavCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()) instanceof AddToFavCommand);
        assertFalse(HistoryList.isEmpty());
    }

    @Test
    public void parseCommand_emailAll() throws Exception {
        assertTrue(parser.parseCommand(
                EmailAllCommand.COMMAND_WORD + " " + "test") instanceof EmailAllCommand);
        assertFalse(HistoryList.isEmpty());
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
