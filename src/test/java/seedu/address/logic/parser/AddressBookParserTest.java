package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteInternshipCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.logic.commands.EditInternshipCommand.EditInternshipDescriptor;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindInternshipCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListInternshipCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortInternshipCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.testutil.EditInternshipDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.InternshipUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add_person() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_add_internship() throws Exception {
        Internship internship = new InternshipBuilder().build();
        AddInternshipCommand expected = new AddInternshipCommand(internship);
        AddInternshipCommand command = (AddInternshipCommand) parser.parseCommand(
                InternshipUtil.getAddInternshipCommand(internship));

        assertEquals(expected, command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete_person() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_delete_internship() throws Exception {
        DeleteInternshipCommand command = (DeleteInternshipCommand) parser.parseCommand(
                DeleteInternshipCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new DeleteInternshipCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_edit_person() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(
                EditPersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                        + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_edit_internship() throws Exception {
        Internship internship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(internship).build();
        EditInternshipCommand command = (EditInternshipCommand) parser.parseCommand(
                EditInternshipCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_INTERNSHIP.getOneBased() + " "
                        + InternshipUtil.getEditInternshipDescriptorDetails(descriptor));

        assertEquals(new EditInternshipCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findPerson() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " "
                        + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPersonCommand(new PersonContainsKeywordsPredicate(
                keywords,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList())), command);
    }

    @Test
    public void parseCommand_findInternship() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindInternshipCommand command = (FindInternshipCommand) parser.parseCommand(
                FindInternshipCommand.COMMAND_WORD + " "
                        + PREFIX_COMPANY_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindInternshipCommand(new InternshipContainsKeywordsPredicate(
                keywords,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList())), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listPerson() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " 3") instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_listInternship() throws Exception {
        assertTrue(parser.parseCommand(ListInternshipCommand.COMMAND_WORD) instanceof ListInternshipCommand);
        assertTrue(parser.parseCommand(
                ListInternshipCommand.COMMAND_WORD + " some nonsense params") instanceof ListInternshipCommand);
    }

    @Test
    public void parseCommand_sortPerson_incorrectFormat() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortPersonCommand.MESSAGE_USAGE), () -> parser.parseCommand(SortPersonCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_sortInternship_incorrectFormat() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortInternshipCommand.MESSAGE_USAGE), () -> parser.parseCommand(SortInternshipCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_sortPerson_correctFormat() throws Exception {
        assertTrue(parser.parseCommand(
                SortPersonCommand.COMMAND_WORD + " " + PREFIX_NAME) instanceof SortPersonCommand);
        assertTrue(parser.parseCommand(
                SortPersonCommand.COMMAND_WORD + " " + PREFIX_COMPANY_NAME) instanceof SortPersonCommand);
    }

    @Test
    public void parseCommand_sortInternship_correctFormat() throws Exception {
        assertTrue(parser.parseCommand(
                SortInternshipCommand.COMMAND_WORD + " " + PREFIX_COMPANY_NAME) instanceof SortInternshipCommand);
        assertTrue(parser.parseCommand(
                SortInternshipCommand.COMMAND_WORD + " " + PREFIX_INTERVIEW_DATE) instanceof SortInternshipCommand);
        assertTrue(parser.parseCommand(
                SortInternshipCommand.COMMAND_WORD + " " + PREFIX_INTERNSHIP_STATUS) instanceof SortInternshipCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
