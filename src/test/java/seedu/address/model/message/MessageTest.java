package seedu.address.model.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_RECOMMEND_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class MessageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Message(null));
    }

    @Test
    public void constructor_invalidMessage_throwsIllegalArgumentException() {
        String invalidMessage = "";
        assertThrows(IllegalArgumentException.class, () -> new Message(invalidMessage));
    }

    @Test
    public void isValidMessage() {
        // null message
        assertThrows(NullPointerException.class, () -> Message.isValidMessage(null));

        // invalid messages
        assertFalse(Message.isValidMessage("")); // empty string
        assertFalse(Message.isValidMessage(" ")); // spaces only

        // valid messages
        assertTrue(Message.isValidMessage("Happy Birthday, {name}!"));
        assertTrue(Message.isValidMessage("-")); // one character
        assertTrue(Message.isValidMessage("Dear {name}, would you be interested in getting yourself insured?"));
        // long message
    }

    @Test
    public void generate_validPersonMessageWithPlaceholder_success() {
        Person typicalPerson = new PersonBuilder().build();
        assertEquals("Dear Alice Pauline, Happy Birthday!",
                VALID_MESSAGE_HAPPY_BIRTHDAY.generate(typicalPerson));
    }

    @Test
    public void generate_validPersonMessageWithoutPlaceholder_success() {
        Person typicalPerson = new PersonBuilder().build();
        assertEquals("Would you be interested in trying out our new product?",
                VALID_MESSAGE_RECOMMEND_PRODUCT.generate(typicalPerson));
    }

    @Test
    public void toString_messageWithPlaceholder_success() {
        assertEquals("Dear {name}, Happy Birthday!", VALID_MESSAGE_HAPPY_BIRTHDAY.toString());
    }

    @Test
    public void toString_messageWithoutPlaceholder_success() {
        assertEquals("Would you be interested in trying out our new product?",
                VALID_MESSAGE_RECOMMEND_PRODUCT.toString());
    }
}
