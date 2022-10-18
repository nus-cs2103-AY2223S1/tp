//package seedu.address.logic.parser;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.logic.commands.ClearCommand;
//import seedu.address.logic.commands.ExitCommand;
//import seedu.address.logic.commands.FindCommand;
//import seedu.address.logic.commands.HelpCommand;
//import seedu.address.logic.commands.client.AddClientCommand;
//import seedu.address.logic.commands.client.ClientCommand;
//import seedu.address.logic.commands.client.DeleteClientCommand;
//import seedu.address.logic.commands.client.EditClientCommand;
//import seedu.address.logic.commands.client.EditClientCommand.EditPersonDescriptor;
//import seedu.address.logic.commands.client.ListClientCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.client.NameContainsKeywordsPredicate;
//import seedu.address.model.client.Person;
//import seedu.address.testutil.EditPersonDescriptorBuilder;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.testutil.PersonUtil;
//
//public class AddressBookParserTest {
//
//    private final AddressBookParser parser = AddressBook.getNewParser();
//
//    @Test
//    public void parseCommand_add() throws Exception {
//        Person person = new PersonBuilder().build();
//        AddClientCommand command = (AddClientCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
//        assertEquals(new AddClientCommand(person), command);
//    }
//
//    @Test
//    public void parseCommand_clear() throws Exception {
//        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
//        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
//    }
//
//    @Test
//    public void parseCommand_delete() throws Exception {
//        DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
//                DeleteClientCommand.COMMAND_WORD + " " + DeleteClientCommand.COMMAND_FLAG + " "
//                        + INDEX_FIRST_PERSON.getOneBased());
//        assertEquals(new DeleteClientCommand(INDEX_FIRST_PERSON), command);
//    }
//
//    @Test
//    public void parseCommand_edit() throws Exception {
//        Person person = new PersonBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
//        EditClientCommand command = (EditClientCommand) parser.parseCommand(EditClientCommand.COMMAND_WORD + " "
//                + EditClientCommand.COMMAND_FLAG + " " + INDEX_FIRST_PERSON.getOneBased() + " "
//                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
//        assertEquals(new EditClientCommand(INDEX_FIRST_PERSON, descriptor), command);
//    }
//
//    @Test
//    public void parseCommand_exit() throws Exception {
//        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
//        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
//    }
//
//    @Test
//    public void parseCommand_find() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindCommand command = (FindCommand) parser.parseCommand(
//                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
//        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }
//
//    @Test
//    public void parseCommand_help() throws Exception {
//        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
//        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
//    }
//
//    @Test
//    public void parseCommand_list() throws Exception {
//        assertTrue(parser.parseCommand(
//                ListClientCommand.COMMAND_WORD + " " + ListClientCommand.COMMAND_FLAG)
//                instanceof ClientCommand);
//        assertTrue(parser.parseCommand(
//                ListClientCommand.COMMAND_WORD + " " + ListClientCommand.COMMAND_FLAG + " 3")
//                instanceof ClientCommand);
//    }
//
//    @Test
//    public void parseCommand_unrecognisedInput_throwsParseException() {
//        assertThrows(ParseException.class,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
//                        -> parser.parseCommand(""));
//    }
//
//    @Test
//    public void parseCommand_unknownCommand_throwsParseException() {
//        assertThrows(ParseException.class,
//                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
//    }
//
//}
