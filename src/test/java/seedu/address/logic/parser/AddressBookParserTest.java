package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EXTRANEOUS_PARAM;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_NUMBER_ONE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TASK_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_URL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteLinkCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.GoToCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.commands.ListModuleCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.OpenLinkCommand;
import seedu.address.logic.commands.SwapTaskNumbersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;
import seedu.address.model.module.ModuleCodeStartsWithKeywordPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameStartsWithKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TypicalModules;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_addLink() throws Exception {
        assertTrue(parser.parseCommand(AddLinkCommand.COMMAND_WORD + " "
                + PREFIX_MODULE_CODE + VALID_MODULE_CODE + " " + PREFIX_MODULE_LINK_URL
                + VALID_MODULE_LINK_URL + " " + PREFIX_MODULE_LINK_ALIAS + VALID_MODULE_LINK_ALIAS
                ) instanceof AddLinkCommand);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        assertTrue(parser.parseCommand(AddTaskCommand.COMMAND_WORD
                + MODULE_CODE_DESC_CS2106 + MODULE_TASK_DESC_A) instanceof AddTaskCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + NAME_DESC_AMY);
        assertEquals(new DeletePersonCommand(new Name(VALID_NAME_AMY)), command);
    }

    @Test
    public void parseCommand_deleteLink() throws Exception {
        assertTrue(parser.parseCommand(DeleteLinkCommand.COMMAND_WORD + " "
                + PREFIX_MODULE_CODE + VALID_MODULE_CODE + " " + PREFIX_MODULE_LINK_ALIAS
                + VALID_MODULE_LINK_ALIAS) instanceof DeleteLinkCommand);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        assertTrue(parser.parseCommand(DeleteTaskCommand.COMMAND_WORD
                + MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_NUMBER_ONE)
                instanceof DeleteTaskCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " " + EXTRANEOUS_PARAM)
                instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " " + EXTRANEOUS_PARAM)
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_findPerson() throws Exception {
        String keyword = VALID_NAME_AMY;
        FindPersonCommand command = (FindPersonCommand) parser.parseCommand(
                FindPersonCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new FindPersonCommand(new NameStartsWithKeywordPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_findModule() throws Exception {
        String keyword = VALID_CS2106_MODULE_CODE;
        FindModuleCommand command = (FindModuleCommand) parser.parseCommand(
                FindModuleCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new FindModuleCommand(new ModuleCodeStartsWithKeywordPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_goto() throws Exception {
        String keyword = VALID_CS2106_MODULE_CODE;
        ModuleCode moduleCode = TypicalModules.CS2106.getModuleCode();
        GoToCommand command = (GoToCommand) parser.parseCommand(GoToCommand.COMMAND_WORD + " " + keyword);
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(keyword);
        assertEquals(new GoToCommand(predicate, moduleCode), command);
    }

    @Test
    public void parseCommand_home() throws Exception {
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD) instanceof HomeCommand);
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD + " " + EXTRANEOUS_PARAM)
                instanceof HomeCommand);
    }

    @Test
    public void parseCommand_listModule() throws Exception {
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD + " " + EXTRANEOUS_PARAM)
                instanceof ListModuleCommand);
    }

    @Test
    public void parseCommand_listPerson() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD) instanceof ListPersonCommand);
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD + " " + EXTRANEOUS_PARAM)
                instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_swapTaskNumbers() throws Exception {
        assertTrue(parser.parseCommand(SwapTaskNumbersCommand.COMMAND_WORD
                + MODULE_CODE_DESC_CS2106 + MODULE_TASKLIST_DESC_SWAP_ONE_AND_TWO)
                instanceof SwapTaskNumbersCommand);
    }

    @Test
    public void parseCommand_openLink() throws Exception {
        assertTrue(parser.parseCommand(OpenLinkCommand.COMMAND_WORD + " "
                + PREFIX_MODULE_CODE + VALID_MODULE_CODE + " " + PREFIX_MODULE_LINK_ALIAS
                + VALID_MODULE_LINK_ALIAS) instanceof OpenLinkCommand);
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
