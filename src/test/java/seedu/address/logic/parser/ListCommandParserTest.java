package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Module;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;
import seedu.address.model.task.TagContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskByDeadlinePredicate;
import seedu.address.model.task.TaskIsDonePredicate;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseCommand_listAll() throws Exception {
        String str = ListCommand.COMMAND_WORD + " " + ListAllCommand.COMMAND_WORD;
        assertTrue(parser.parse(str) instanceof ListCommand);
        assertTrue(parser.parse(str + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_listUnmarked() {
        ListCommand expectedCommand =
                new ListCommand(List.of(new TaskIsDonePredicate(List.of("false"))));
        assertParseSuccess(parser, " -u", expectedCommand);
    }

    @Test
    public void parseCommand_listUnmarkedAndModule() {
        ListCommand expectedCommand =
                new ListCommand(List.of(new TaskIsDonePredicate(List.of("false")),
                        new ModuleContainsKeywordsPredicate(List.of("cs2103t"))));
        assertParseSuccess(parser, " -u --module cs2103t", expectedCommand);
    }

    @Test
    public void parseCommand_listMarked() {
        ArrayList<Predicate<Task>> predicates = new ArrayList<>();
        predicates.add(new TaskIsDonePredicate(List.of("true")));
        ListCommand expectedCommand =
                new ListCommand(predicates);
        assertParseSuccess(parser, " -m", expectedCommand);
    }

    @Test
    public void parseCommand_listModule() {
        ArrayList<Predicate<Task>> predicates = new ArrayList<>();
        predicates.add(new ModuleContainsKeywordsPredicate(List.of("CS2100")));
        ListCommand expectedCommand =
                new ListCommand(predicates);
        assertParseSuccess(parser, " --module CS2100", expectedCommand);
    }

    @Test
    public void parseListModuleCommand_invalidModule_throwsParseException() {
        assertParseFailure(parser, " --module cs 2100", String.format(Module.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parseListModuleCommand_emptyModule_throwsParseException() {
        assertParseFailure(parser, " --module",
                String.format(Module.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parseCommand_listTags() throws ParseException {
        // no leading and trailing whitespaces
        ListCommand expectedCommand =
                new ListCommand(List.of(new TagContainsKeywordsPredicate(List.of("urgent"))));
        // System.out.println(parser.parse("-t urgent").predicates);
        assertParseSuccess(parser, " -t urgent", expectedCommand);

        // with leading and trailing whitespaces
        expectedCommand =
                new ListCommand(List.of(new TagContainsKeywordsPredicate(List.of("urgent"))));
        assertParseSuccess(parser, " -t urgent ", expectedCommand);
    }

    @Test
    public void parseListTagCommand_invalidTag_throwsParseException() {
        assertParseFailure(parser, " -t low priority", String.format(Tag.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, " -t &", String.format(Tag.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parseListTagCommand_emptyTag_throwsParseException() {
        assertParseFailure(parser, " -t",
                String.format(Tag.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parseCommand_listDeadline() {
        ListCommand expectedCommand =
                new ListCommand(List.of(new TaskByDeadlinePredicate(List.of("2022-10-20"))));
        assertParseSuccess(parser, " -d 2022-10-20", expectedCommand);
    }
}
