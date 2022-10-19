package seedu.address.model.quote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quote(null, "quotee"));
        assertThrows(NullPointerException.class, () -> new Quote("message", null));
        assertThrows(NullPointerException.class, () -> new Quote(null, null));
    }

    @Test
    public void equals() {
        String quote = "Do the difficult things while they are easy and do the great things while they are small.";
        String quotee = "Lao Tzu";
        Quote firstQuote = new Quote(quote, quotee);

        // same object -> returns true
        assertTrue(firstQuote.equals(firstQuote));

        // same values -> returns true
        assertTrue(firstQuote.equals(new Quote(quote, quotee)));

        // different types -> returns false
        assertFalse(firstQuote.equals(1));

        // null -> returns false
        assertFalse(firstQuote.equals(null));

        // different quotee -> returns false
        assertFalse(firstQuote.equals(new Quote(quote, "Lao Fu Tzu")));

        // different quote -> returns false
        assertFalse(firstQuote.equals(new Quote("I love you", quotee)));

        // different values -> returns false
        assertFalse(firstQuote.equals(new Quote("I love you", "Lao Fu Tzu")));

        // different quote -> returns false
        assertFalse(firstQuote.equals(new Quote("I love you", quotee)));
    }

}
