package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.BUDGET_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.COUNTRY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.DURATION_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_BUDGET_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COUNTRY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PEOPLE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITINERARY_DESC_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.PEOPLE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.START_DATE_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_BUDGET_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PEOPLE;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_SECOND_ITINERARY;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_THIRD_ITINERARY;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;
import seedu.waddle.testutil.EditItineraryDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_PEOPLE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ITINERARY_DESC_SUMMER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ITINERARY_DESC_DESC_SUMMER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ITINERARY_DESC_DESC_SUMMER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Description.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_COUNTRY_DESC, Country.MESSAGE_CONSTRAINTS); // invalid country
        assertParseFailure(parser, "1" + INVALID_START_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid start date
        //invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_DESC, ItineraryDuration.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PEOPLE_DESC, People.MESSAGE_CONSTRAINTS); // invalid people
        assertParseFailure(parser, "1" + INVALID_BUDGET_DESC, Budget.MESSAGE_CONSTRAINTS); // invalid budget

        // invalid country followed by valid start date
        assertParseFailure(parser, "1" + INVALID_COUNTRY_DESC + INVALID_START_DATE_DESC,
                Country.MESSAGE_CONSTRAINTS);

        // valid country followed by invalid country. The test case for invalid country followed by valid country
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + COUNTRY_DESC_WINTER + INVALID_COUNTRY_DESC,
                Country.MESSAGE_CONSTRAINTS);

        // TODO
        /*
        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
         */

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_START_DATE_DESC
                + VALID_DURATION_SUMMER + VALID_COUNTRY_SUMMER + VALID_BUDGET_SUMMER, Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITINERARY;
        String userInput = targetIndex.getOneBased() + COUNTRY_DESC_SUMMER + PEOPLE_DESC_SUMMER
                + START_DATE_DESC_SUMMER + DURATION_DESC_SUMMER + ITINERARY_DESC_DESC_SUMMER + BUDGET_DESC_SUMMER;
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withName(VALID_ITINERARY_DESC_SUMMER)
                .withCountry(VALID_COUNTRY_SUMMER).withStartDate(VALID_START_DATE_SUMMER)
                .withDuration(VALID_DURATION_SUMMER).withPeople(VALID_PEOPLE_SUMMER)
                .withBudget(VALID_BUDGET_SUMMER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITINERARY;
        String userInput = targetIndex.getOneBased() + COUNTRY_DESC_WINTER + START_DATE_DESC_SUMMER;
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withCountry(VALID_COUNTRY_WINTER)
                .withStartDate(VALID_START_DATE_SUMMER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ITINERARY;
        String userInput = targetIndex.getOneBased() + ITINERARY_DESC_DESC_SUMMER;
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder()
                .withName(VALID_ITINERARY_DESC_SUMMER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + COUNTRY_DESC_SUMMER;
        descriptor = new EditItineraryDescriptorBuilder().withCountry(VALID_COUNTRY_SUMMER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + START_DATE_DESC_SUMMER;
        descriptor = new EditItineraryDescriptorBuilder().withStartDate(VALID_START_DATE_SUMMER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + DURATION_DESC_SUMMER;
        descriptor = new EditItineraryDescriptorBuilder().withDuration(VALID_DURATION_SUMMER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + PEOPLE_DESC_SUMMER;
        descriptor = new EditItineraryDescriptorBuilder().withPeople(VALID_PEOPLE_SUMMER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITINERARY;
        String userInput = targetIndex.getOneBased() + COUNTRY_DESC_SUMMER + DURATION_DESC_SUMMER
                + START_DATE_DESC_SUMMER + PEOPLE_DESC_SUMMER + COUNTRY_DESC_SUMMER + DURATION_DESC_SUMMER
                + START_DATE_DESC_SUMMER + PEOPLE_DESC_SUMMER + COUNTRY_DESC_WINTER + DURATION_DESC_WINTER
                + START_DATE_DESC_WINTER + PEOPLE_DESC_WINTER;

        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder().withCountry(VALID_COUNTRY_WINTER)
                .withStartDate(VALID_START_DATE_WINTER).withDuration(VALID_DURATION_WINTER)
                .withPeople(VALID_PEOPLE_WINTER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITINERARY;
        String userInput = targetIndex.getOneBased() + INVALID_COUNTRY_DESC + COUNTRY_DESC_WINTER;
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder()
                .withCountry(VALID_COUNTRY_WINTER).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + START_DATE_DESC_WINTER + INVALID_COUNTRY_DESC + DURATION_DESC_WINTER
                + COUNTRY_DESC_WINTER;
        descriptor = new EditItineraryDescriptorBuilder().withCountry(VALID_COUNTRY_WINTER)
                .withStartDate(VALID_START_DATE_WINTER).withDuration(VALID_DURATION_WINTER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
