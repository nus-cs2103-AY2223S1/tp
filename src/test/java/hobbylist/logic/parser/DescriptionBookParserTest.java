package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.AddCommand;
import hobbylist.logic.commands.ClearCommand;
import hobbylist.logic.commands.DeleteCommand;
import hobbylist.logic.commands.EditCommand;
import hobbylist.logic.commands.ExitCommand;
import hobbylist.logic.commands.FindCommand;
import hobbylist.logic.commands.HelpCommand;
import hobbylist.logic.commands.ListCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.DateMatchesGivenDatePredicate;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.model.activity.RatingMatchesGivenValuePredicate;
import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.ActivityUtil;
import hobbylist.testutil.Assert;
import hobbylist.testutil.EditActivityDescriptorBuilder;
import hobbylist.testutil.TypicalIndexes;

public class DescriptionBookParserTest {

    private final HobbyListParser parser = new HobbyListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Activity activity = new ActivityBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ActivityUtil.getAddCommand(activity));
        assertEquals(new AddCommand(activity), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.getCommandWord()) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.getCommandWord() + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.getCommandWord() + " " + TypicalIndexes.INDEX_FIRST_ACTIVITY.getOneBased());
        assertEquals(new DeleteCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Activity activity = new ActivityBuilder().build();
        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder(activity).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.getCommandWord() + " "
                + TypicalIndexes.INDEX_FIRST_ACTIVITY.getOneBased() + " "
                + ActivityUtil.getEditActivityDescriptorDetails(descriptor));
        EditCommand test = new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY, descriptor);
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_ACTIVITY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.getCommandWord()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.getCommandWord() + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.getCommandWord() + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameOrDescContainsKeywordsPredicate(keywords),
                                    new DateMatchesGivenDatePredicate(""),
                                    new RatingMatchesGivenValuePredicate(-1)
                ), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.getCommandWord()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.getCommandWord() + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.getCommandWord()) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.getCommandWord() + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
                -> parser.parseCommand("unknownCommand"));
    }
}
