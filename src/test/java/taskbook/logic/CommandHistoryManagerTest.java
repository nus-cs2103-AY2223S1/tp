package taskbook.logic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CommandHistoryManagerTest {

    private static final String EMPTY = "";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String SIX = "6";
    private static final String SEVEN = "7";

    @Test
    public void getPreviousCommand_emptyHistory_returnsNull() {
        CommandHistoryManager history = new CommandHistoryManager();
        assertNull(history.getPreviousCommand());
    }

    @Test
    public void getNextCommand_emptyHistory_returnsNull() {
        CommandHistoryManager history = new CommandHistoryManager();
        assertNull(history.getNextCommand());
    }

    @Test
    public void getPreviousCommand_validSteps_correct() {
        CommandHistoryManager history = new CommandHistoryManager();
        history.addCommand(ONE);
        history.addCommand(TWO);
        history.addCommand(THREE);
        assertEquals(THREE, history.getPreviousCommand());
        assertEquals(TWO, history.getPreviousCommand());
        assertEquals(ONE, history.getPreviousCommand());
    }

    @Test
    public void getPreviousCommand_pastLast_returnsLast() {
        CommandHistoryManager history = new CommandHistoryManager();
        history.addCommand(ONE);
        history.addCommand(TWO);
        assertEquals(TWO, history.getPreviousCommand());
        assertEquals(ONE, history.getPreviousCommand());
        assertEquals(ONE, history.getPreviousCommand());
        assertEquals(ONE, history.getPreviousCommand());
    }

    @Test
    public void getNextCommand_validSteps_correct() {
        CommandHistoryManager history = new CommandHistoryManager();
        history.addCommand(ONE);
        history.addCommand(TWO);
        history.addCommand(THREE);
        history.getPreviousCommand();
        history.getPreviousCommand();
        history.getPreviousCommand();
        assertEquals(TWO, history.getNextCommand());
        assertEquals(THREE, history.getNextCommand());
    }

    @Test
    public void getNextCommand_pastFirst_returnsEmpty() {
        CommandHistoryManager history = new CommandHistoryManager();
        history.addCommand(ONE);
        history.addCommand(TWO);
        history.getPreviousCommand();
        history.getPreviousCommand();
        history.getNextCommand();
        assertEquals(EMPTY, history.getNextCommand());
        assertEquals(EMPTY, history.getNextCommand());
    }

    @Test
    public void addCommand_valid_success() {
        CommandHistoryManager history = new CommandHistoryManager();
        assertDoesNotThrow(() -> history.addCommand(ONE));
    }

    @Test
    public void addCommand_addPastEvenCapacity_prunesHistory() {
        CommandHistoryManager history = new CommandHistoryManager(2);
        history.addCommand(ONE);
        history.addCommand(TWO);
        history.addCommand(THREE);
        history.addCommand(FOUR);
        history.addCommand(FIVE);
        assertEquals(FIVE, history.getPreviousCommand());
        assertEquals(FOUR, history.getPreviousCommand());
        assertEquals(FOUR, history.getPreviousCommand());
    }

    @Test
    public void addCommand_addPastOddCapacity_prunesHistory() {
        CommandHistoryManager history = new CommandHistoryManager(3);
        history.addCommand(ONE);
        history.addCommand(TWO);
        history.addCommand(THREE);
        history.addCommand(FOUR);
        history.addCommand(FIVE);
        history.addCommand(SIX);
        history.addCommand(SEVEN);
        assertEquals(SEVEN, history.getPreviousCommand());
        assertEquals(SIX, history.getPreviousCommand());
        assertEquals(FIVE, history.getPreviousCommand());
        assertEquals(FIVE, history.getPreviousCommand());
    }
}
