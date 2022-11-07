package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_7;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteIndexCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditIndexCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCurrModCommand;
import seedu.address.logic.commands.FilterPlanModCommand;
import seedu.address.logic.commands.FilterPrevModCommand;
import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ModuleCommand;
import seedu.address.logic.commands.ModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.ModuleIndexCommand;
import seedu.address.logic.commands.ModulesLeftCommand;
import seedu.address.logic.commands.NextSemCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UserCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CurrModContainsKeywordsPredicate;
import seedu.address.model.module.PlanModContainsKeywordsPredicate;
import seedu.address.model.module.PrevModContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.user.User;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.UserBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_user() throws Exception {
        User user = new UserBuilder().build();
        UserCommand command = (UserCommand) parser.parseCommand(PersonUtil.getUserCommand(user));
        assertEquals(new UserCommand(user), command);
    }

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteIndexCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditIndexCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_module() throws Exception {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCurrentModules(VALID_MODULE_1)
                .withPreviousModules(VALID_MODULE_3).withPreviousModules(VALID_MODULE_5)
                .withModToRemove(VALID_MODULE_7).build();
        ModuleCommand command = (ModuleCommand) parser.parseCommand(ModuleCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditModuleDescriptorDetails(descriptor));
        assertEquals(new ModuleIndexCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_modsLeft() throws Exception {
        ModulesLeftCommand command = (ModulesLeftCommand) parser.parseCommand(
                ModulesLeftCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ModulesLeftCommand(INDEX_FIRST_PERSON), command);
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

    @Test
    public void parseCommand_filterTag() throws Exception {
        String keywords = "foo bar";
        FilterTagCommand command = (FilterTagCommand) parser.parseCommand(
                FilterTagCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new FilterTagCommand(new TagsContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterCurrMod() throws Exception {
        String keywords = "CS2100 CS2103T";
        FilterCurrModCommand command = (FilterCurrModCommand) parser.parseCommand(
                FilterCurrModCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new FilterCurrModCommand(new CurrModContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterPrevMod() throws Exception {
        String keywords = "CS2100 CS2103T";
        FilterPrevModCommand command = (FilterPrevModCommand) parser.parseCommand(
                FilterPrevModCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new FilterPrevModCommand(new PrevModContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_filterPlanMod() throws Exception {
        String keywords = "CS2100 CS2103T";
        FilterPlanModCommand command = (FilterPlanModCommand) parser.parseCommand(
                FilterPlanModCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new FilterPlanModCommand(new PlanModContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_nextSem() throws Exception {
        assertTrue(parser.parseCommand(NextSemCommand.COMMAND_WORD) instanceof NextSemCommand);
        assertTrue(parser.parseCommand(NextSemCommand.COMMAND_WORD + " 3") instanceof NextSemCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }
}
