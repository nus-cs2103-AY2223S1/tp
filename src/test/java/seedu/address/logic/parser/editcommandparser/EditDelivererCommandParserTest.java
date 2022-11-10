package seedu.address.logic.parser.editcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.editcommands.EditCommand;
import seedu.address.logic.commands.editcommands.EditDelivererCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditDelivererCommandParserTest {
    private EditDelivererCommandParser parser = new EditDelivererCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_spaces_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("     \t \n"));
    }

    @Test
    public void parse_noPrefixes_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("this has no prefixes"));
    }

    @Test
    public void parse_noFields_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" 1"));
    }

    @Test
    public void parse_name_success() throws ParseException {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName("doraemon").build();
        EditDelivererCommand expected = new EditDelivererCommand(INDEX_FIRST, descriptor);

        String input = " 1 n/doraemon";
        EditDelivererCommand result = parser.parse(input);
        assertEquals(result, expected);
    }

    @Test
    public void parse_phone_success() throws ParseException {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone("991").build();
        EditDelivererCommand expected = new EditDelivererCommand(INDEX_FIRST, descriptor);

        String input = " 1 ph/991";
        EditDelivererCommand result = parser.parse(input);
        assertEquals(result, expected);
    }

    @Test
    public void parse_email_success() throws ParseException {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withEmail("doraemon@comics.com").build();
        EditDelivererCommand expected = new EditDelivererCommand(INDEX_FIRST, descriptor);

        String input = " 1 e/doraemon@comics.com";
        EditDelivererCommand result = parser.parse(input);
        assertEquals(result, expected);
    }

    @Test
    public void parse_address_success() throws ParseException {
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAddress("Tokyo").build();
        EditDelivererCommand expected = new EditDelivererCommand(INDEX_FIRST, descriptor);

        String input = " 1 a/Tokyo";
        EditDelivererCommand result = parser.parse(input);
        assertEquals(result, expected);
    }
}
