package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTags.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.tag.DeleteTagCommand;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class DeleteTagCommandParserTest {
    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    private static boolean deleteTagFromContact;
    private static boolean deleteTagFromTask;
    private static Index contactIndex;
    private static Index taskIndex;
    private static List<String> tagList;
    private static EditPersonDescriptor editPersonDescriptor;
    private static EditTaskDescriptor editTaskDescriptor;

    public void initialise() {
        tagList = new ArrayList<>();
        editPersonDescriptor = new EditPersonDescriptor();
        editTaskDescriptor = new EditTaskDescriptor();
        deleteTagFromContact = true;
        deleteTagFromTask = true;
        contactIndex = Index.fromZeroBased(0);
        taskIndex = Index.fromZeroBased(0);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        initialise();
        Tag expectedTag = new TagBuilder(CS2101).build();
        tagList.add("CS2101");
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(expectedTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);


        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CONTACT_INDEX + TASK_INDEX + TAG_DESC_2101,
                new DeleteTagCommand(contactIndex, taskIndex, editPersonDescriptor,
                        editTaskDescriptor, deleteTagFromContact, deleteTagFromTask, tagList));

    }

    @Test
    public void parse_optionalContactFieldMissing_success() {
        // zero tags
        initialise();
        Tag expectedTag = new TagBuilder(CS2101).build();
        tagList.add("CS2101");
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(expectedTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_INDEX + TAG_DESC_2101,
                new DeleteTagCommand(contactIndex, taskIndex, editPersonDescriptor,
                        editTaskDescriptor, deleteTagFromContact, false, tagList));
    }

    @Test
    public void parse_optionalTaskFieldMissing_success() {
        // zero tags
        initialise();
        Tag expectedTag = new TagBuilder(CS2101).build();
        tagList.add("CS2101");
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(expectedTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CONTACT_INDEX + TAG_DESC_2101,
                new DeleteTagCommand(contactIndex, taskIndex, editPersonDescriptor,
                        editTaskDescriptor, false, deleteTagFromTask, tagList));
    }

    @Test
    public void parse_compulsoryLabelFieldMissing_failure() {
        String expectedMessage = DeleteTagCommand.MESSAGE_TAG_NOT_DELETED;

        initialise();

        assertParseFailure(parser, PREAMBLE_WHITESPACE + CONTACT_INDEX + TASK_INDEX,
                expectedMessage);
    }

    @Test
    public void parse_bothContactAndTaskFieldMissing_failure() {
        String expectedMessage = DeleteTagCommand.MESSAGE_MISSING_INDEX;

        initialise();

        assertParseFailure(parser, PREAMBLE_WHITESPACE + TAG_DESC_2101,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid special character tag
        assertParseFailure(parser, PREAMBLE_WHITESPACE + CONTACT_INDEX + TASK_INDEX + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid multi-word tag
        assertParseFailure(parser, PREAMBLE_WHITESPACE + CONTACT_INDEX + TASK_INDEX + INVALID_TAG_DESC_2,
                Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CONTACT_INDEX + TASK_INDEX + TAG_DESC_2101,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }
}
