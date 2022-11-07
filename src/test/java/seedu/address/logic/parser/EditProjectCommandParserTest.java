package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.EditClientCommand;
import seedu.address.logic.commands.project.EditProjectCommand;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.project.Repository;

/**
 * Represents tests for parsing Edit Client commands.
 */
public class EditProjectCommandParserTest {

    private ProjectCommandParser parser = new ProjectCommandParser();

    @Test
    public void parse_argumentsAbsent_failure() {

        //extraneous words
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, "abcd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE));

        //missing all arguments
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE));

        //missing compulsory id
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, " n/ab4 r/tp/repo c/3 d/2022-10-10",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE));

        //has only id and no optionals
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, " p/1",
                String.format(MESSAGE_MISSING_ARGUMENTS, EditProjectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArguments_failure() {

        //invalid name
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, " n/%%%ab3 p/1",
                String.format(Name.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, " n/ p/1",
                String.format(Name.MESSAGE_CONSTRAINTS));

        //invalid repository
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, " r/invalidrepo p/1",
                String.format(Repository.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, EditProjectCommand.COMMAND_FLAG, " r/ p/1",
                String.format(Repository.MESSAGE_CONSTRAINTS));

        //invalid deadline
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " d/10102003 p/1",
                String.format(Deadline.MESSAGE_CONSTRAINTS));
        assertParseFailure(parser, EditClientCommand.COMMAND_FLAG, " d/ p/1",
                String.format(Deadline.MESSAGE_CONSTRAINTS));
    }

}
