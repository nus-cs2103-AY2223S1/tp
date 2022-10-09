package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BETA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_BETA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BETA;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HIGH_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALPHA;
import static seedu.address.testutil.TypicalPersons.BETA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BETA).withTags(VALID_TAG_TUTORIAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BETA + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                + TAG_DESC_TUTORIAL, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ALPHA + NAME_DESC_BETA + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                + TAG_DESC_TUTORIAL, new AddCommand(expectedPerson));

        // multiple modules - last module accepted
        assertParseSuccess(parser, NAME_DESC_BETA + MODULE_DESC_ALPHA + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                + TAG_DESC_TUTORIAL, new AddCommand(expectedPerson));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_BETA + MODULE_DESC_BETA + DEADLINE_DESC_ALPHA + DEADLINE_DESC_BETA
                + TAG_DESC_TUTORIAL, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BETA)
                .withTags(VALID_TAG_TUTORIAL, VALID_TAG_HIGH_PRIORITY)
                .build();
        assertParseSuccess(parser, NAME_DESC_BETA + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                + TAG_DESC_HIGH_PRIORITY + TAG_DESC_TUTORIAL, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(ALPHA).withTags().build();
        assertParseSuccess(parser, NAME_DESC_ALPHA + MODULE_DESC_ALPHA + DEADLINE_DESC_ALPHA,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_TASK_BETA + MODULE_DESC_BETA + DEADLINE_DESC_BETA,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_BETA + VALID_MODULE_BETA + DEADLINE_DESC_BETA,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_BETA + MODULE_DESC_BETA + VALID_DEADLINE_BETA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_TASK_BETA + VALID_MODULE_BETA + VALID_DEADLINE_BETA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                + TAG_DESC_HIGH_PRIORITY + TAG_DESC_TUTORIAL, Name.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BETA + INVALID_MODULE_DESC + DEADLINE_DESC_BETA
                + TAG_DESC_HIGH_PRIORITY + TAG_DESC_TUTORIAL, Module.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_BETA + MODULE_DESC_BETA + INVALID_DEADLINE_DESC
                + TAG_DESC_HIGH_PRIORITY + TAG_DESC_TUTORIAL, Deadline.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BETA + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                + INVALID_TAG_DESC + VALID_TAG_TUTORIAL, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MODULE_DESC_BETA + DEADLINE_DESC_BETA,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BETA + MODULE_DESC_BETA + DEADLINE_DESC_BETA
                        + TAG_DESC_HIGH_PRIORITY + TAG_DESC_TUTORIAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
