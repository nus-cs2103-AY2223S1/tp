package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.model.Deadline;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;

/**
 * Represents tests for parsing Edit Issue commands.
 */
public class EditIssueCommandParserTest {

    private IssueCommandParser parser = new IssueCommandParser();

    @Test
    public void parse_argumentsAbsent_failure() {

        //extraneous words
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, "abcd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE));

        //missing all arguments
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE));


        //missing compulsory id
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " t/title u/1 d/2022-10-10",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE));

        //has only id and no optionals
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, " i/1",
                String.format(MESSAGE_MISSING_ARGUMENTS, EditIssueCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArguments_failure() {

        //invalid title
        assertParseFailure(parser, EditIssueCommand.COMMAND_FLAG, " t/ i/1",
                String.format(Title.MESSAGE_CONSTRAINTS));

        //invalid deadline
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " d/10-10-2003 i/1",
                String.format(Deadline.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " d/ i/1",
                String.format(Deadline.MESSAGE_CONSTRAINTS));

        //invalid urgency
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " u/12 i/1",
                Urgency.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " u/ i/1",
                Urgency.MESSAGE_CONSTRAINTS);
    }
}
