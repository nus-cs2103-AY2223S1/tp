package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.model.person.Person;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;
import seedu.address.testutil.LinkBuilder;
import seedu.address.testutil.LinkUtil;
import seedu.address.testutil.ParserHelper;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;
import seedu.address.testutil.TeamBuilder;
import seedu.address.testutil.TeamUtil;

public class TruthTableParserTest {

    private final TruthTableParser parser = new TruthTableParser();

    @Test
    public void parseCommand_add() throws Exception {
        AddCommand command = (AddCommand) parser.parseCommand(AddCommand.COMMAND_WORD);
        assertEquals(new AddCommand(), command);
    }

    @Test
    public void parseCommand_addLink() throws Exception {
        Link link = new LinkBuilder().build();
        AddLinkCommand command =
                (AddLinkCommand) parser.parseCommand(LinkUtil.getAddLinkCommand(link));
        assertEquals(link.getDisplayedName(), ParserHelper.getLinkName(command));
        assertEquals(link.getUrl(), ParserHelper.getUrl(command));
    }
    @Test
    public void parseCommand_addMember() throws Exception {
        AddMemberCommand command =
                (AddMemberCommand) parser.parseCommand(
                        AddMemberCommand.FULL_COMMAND + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(INDEX_FIRST_PERSON, ParserHelper.getIndex(command));
    }
    @Test
    public void parseCommand_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command =
                (AddPersonCommand) parser.parseCommand(PersonUtil.getAddPersonCommand(person));
        assertEquals(person.getName(), ParserHelper.getName(command));
        assertEquals(person.getPhone(), ParserHelper.getPhone(command));
        assertEquals(person.getEmail(), ParserHelper.getEmail(command));
        assertEquals(person.getAddress(), ParserHelper.getAddress(command));
    }
    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command =
                (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task));
        assertEquals(task.getName(), ParserHelper.getTaskName(command));
        assertTrue(Arrays.equals(new String[] {"1","2"},
                ParserHelper.getAssignees(command)));
        assertEquals(task.getDeadline().get(), ParserHelper.getDeadline(command));
    }

    @Test
    public void parseCommand_addTeam() throws Exception {
        Team team = new TeamBuilder().build();
        String debug = TeamUtil.getAddTeamCommand(team);
        AddTeamCommand command =
                (AddTeamCommand) parser.parseCommand(TeamUtil.getAddTeamCommand(team));
        assertEquals(team.getTeamName(), ParserHelper.getTeamName(command));
        assertEquals(team.getDescription(), ParserHelper.getDescription(command));
    }

    @Test
    public void parseCommand_assign() throws Exception {

    }
    @Test
    public void parseCommand_assignTask() throws Exception {

    }
    @Test
    public void parseCommand_assignRandom() throws Exception {

    }

    @Test
    public void parseCommand_clear() throws Exception {
    }

    @Test
    public void parseCommand_delete() throws Exception {
    }

    @Test
    public void parseCommand_deleteLink() throws Exception {
    }
    @Test
    public void parseCommand_deleteMember() throws Exception {
    }
    @Test
    public void parseCommand_deletePerson() throws Exception {
    }
    @Test
    public void parseCommand_deleteTask() throws Exception {
    }
    @Test
    public void parseCommand_deleteTeam() throws Exception {
    }

    @Test
    public void parseCommand_edit() throws Exception {
    }

    @Test
    public void parseCommand_editLink() throws Exception {
    }

    @Test
    public void parseCommand_editMember() throws Exception {
    }

    @Test
    public void parseCommand_editPerson() throws Exception {
    }

    @Test
    public void parseCommand_editTask() throws Exception {
    }
    @Test
    public void parseCommand_editTeam() throws Exception {
    }

    @Test
    public void parseCommand_exit() throws Exception {
    }

    @Test
    public void parseCommand_find() throws Exception {
    }

    @Test
    public void parseCommand_findMember() throws Exception {
    }

    @Test
    public void parseCommand_findPerson() throws Exception {
    }
    @Test
    public void parseCommand_findTask() throws Exception {
    }

    @Test
    public void parseCommand_help() throws Exception {
    }

    @Test
    public void parseCommand_list() throws Exception {
    }

    @Test
    public void parseCommand_listMembers() throws Exception {
    }
    @Test
    public void parseCommand_listPersons() throws Exception {
    }
    @Test
    public void parseCommand_listTasks() throws Exception {
    }

    @Test
    public void parseCommand_mark() throws Exception {
    }

    @Test
    public void parseCommand_set() throws Exception {
    }
    @Test
    public void parseCommand_setDeadline() throws Exception {
    }

    @Test
    public void parseCommand_setTeam() throws Exception {
    }

    @Test
    public void parseCommand_sort() throws Exception {
    }
    @Test
    public void parseCommand_sortMember() throws Exception {
    }
    @Test
    public void parseCommand_sortTask() throws Exception {
    }

    @Test
    public void parseCommand_taskOf() throws Exception {
    }
    @Test
    public void parseCommand_taskSummary() throws Exception {
    }
    @Test
    public void parseCommand_theme() throws Exception {
    }

    @Test
    public void parseCommand_unmark() throws Exception {
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
    }
}
