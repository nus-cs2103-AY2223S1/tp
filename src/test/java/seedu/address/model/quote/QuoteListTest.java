package seedu.address.model.quote;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class QuoteListTest {

    @Test
    public void getRandomQuote_doesNotChange() {
        assertEquals(QuoteList.getRandomQuote(), QuoteList.getRandomQuote());
    }
}
