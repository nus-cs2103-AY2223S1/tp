package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandWordTest {
    private static final String MAIN_COMMAND_WORD_1 = "AAA";
    private static final String MAIN_COMMAND_WORD_2 = "AAa";
    private static final String MAIN_COMMAND_WORD_3 = "Aaa";
    private static final String ALTERNATIVE_COMMAND_WORD_1 = "BBB";
    private static final String ALTERNATIVE_COMMAND_WORD_2 = "BBb";
    private static final String ALTERNATIVE_COMMAND_WORD_3 = "Bbb";

    @Test
    public void test() {
        assertThrows(NullPointerException.class, () -> new CommandWord(null));

        //toString() returns mainCommandWord
        assertEquals(new CommandWord(MAIN_COMMAND_WORD_1).toString(), MAIN_COMMAND_WORD_1);
        assertNotEquals(new CommandWord(MAIN_COMMAND_WORD_2).toString(), MAIN_COMMAND_WORD_3);
        assertEquals(new CommandWord(MAIN_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_1).toString(), MAIN_COMMAND_WORD_1);
        assertEquals(new CommandWord(MAIN_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .toString(), MAIN_COMMAND_WORD_1);
        assertNotEquals(new CommandWord(MAIN_COMMAND_WORD_3, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .toString(), MAIN_COMMAND_WORD_2);

        // Matches input
        assertTrue(new CommandWord(MAIN_COMMAND_WORD_3, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .matches(MAIN_COMMAND_WORD_3));
        assertTrue(new CommandWord(MAIN_COMMAND_WORD_3, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .matches(ALTERNATIVE_COMMAND_WORD_1));
        assertTrue(new CommandWord(MAIN_COMMAND_WORD_3, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .matches(ALTERNATIVE_COMMAND_WORD_2));

        // Does not match input
        assertTrue(new CommandWord(MAIN_COMMAND_WORD_3, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .matches(MAIN_COMMAND_WORD_1));
        assertTrue(new CommandWord(MAIN_COMMAND_WORD_3, ALTERNATIVE_COMMAND_WORD_1, ALTERNATIVE_COMMAND_WORD_2)
                .matches(ALTERNATIVE_COMMAND_WORD_3));
    }
}
