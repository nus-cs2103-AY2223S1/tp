package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    private final CommandHistory history = new CommandHistory();

    @Test
    public void execute_maxSizeTen_success() {
        String str = "list patients";
        for (int i = 0; i < 20; i++) {
            history.add(str);
        }
        assertEquals(10, history.getHistory().size());
    }

    @Test
    public void execute_reverseString_success() {
        List<String> strings = Arrays.asList("list patients", "list appts", "mark 1");
        for (String str : strings) {
            history.add(str);
        }
        Collections.reverse(strings);
        String expectedMessage = String.join("\n", strings);
        assertEquals(expectedMessage, history.getHistoryString());
    }
}
