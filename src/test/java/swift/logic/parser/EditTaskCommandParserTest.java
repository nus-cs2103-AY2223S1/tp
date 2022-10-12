package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.commands.CommandTestUtil.CONTACT_INDEX_TASK1;
import static swift.logic.commands.CommandTestUtil.INVALID_CONTACT_INDEX;
import static swift.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static swift.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static swift.logic.commands.CommandTestUtil.VALID_CONTACT_INDEX;
import static swift.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static swift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;
import static swift.testutil.TypicalTaskIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import swift.commons.core.index.Index;
import swift.logic.commands.EditTaskCommand;
import swift.logic.commands.EditTaskCommand.EditTaskDescriptor;
import swift.model.person.Name;
import swift.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + CONTACT_INDEX_TASK1 , Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + CONTACT_INDEX_TASK1 + NAME_DESC_AMY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
            .withContactIndex(VALID_CONTACT_INDEX).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // contact index
        userInput = targetIndex.getOneBased() + CONTACT_INDEX_TASK1;
        descriptor = new EditTaskDescriptorBuilder().withContactIndex(VALID_CONTACT_INDEX).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + CONTACT_INDEX_TASK1 + NAME_DESC_AMY
                + CONTACT_INDEX_TASK1 + CONTACT_INDEX_TASK1;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
            .withContactIndex(VALID_CONTACT_INDEX)
            .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_CONTACT_INDEX + CONTACT_INDEX_TASK1;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withContactIndex(VALID_CONTACT_INDEX).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_AMY + INVALID_CONTACT_INDEX + CONTACT_INDEX_TASK1;

        descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
            .withContactIndex(VALID_CONTACT_INDEX)
            .build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
