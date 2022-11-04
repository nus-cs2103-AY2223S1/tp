package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

class HistoryListTest {

    @Test
    void getList_correctInput() {

        //Initialize the historyList
        HistoryList history = new HistoryList();
        history.addToHistory("add 1");

        //Creating a queue with correct inputs
        Queue<String> queue = new LinkedList<>();
        queue.add("add 1");
        assertTrue(history.getList().equals(queue));
    }

    @Test
    void getList_incorrectInput() {

        //Initialize the historyList
        HistoryList history = new HistoryList();
        history.addToHistory("add 1");

        //Creating a queue with wrong inputs
        Queue<String> queue = new LinkedList<>();
        queue.add("add 2");
        assertFalse(history.getList().equals(queue));
    }

    @Test
    void isMax_correct() {

        //Initialize the historyList & populate it with 5 previous commands
        HistoryList history = new HistoryList();
        history.addToHistory("add 1");
        history.addToHistory("list");
        history.addToHistory("help");
        history.addToHistory("delete 1");
        history.addToHistory("include 1 s/telegram");
        assertTrue(history.isMax());
    }

    @Test
    void isEmpty_correct() {

        //Initialize the historyList with nothing
        HistoryList history = new HistoryList();
        assertTrue(history.isEmpty());
    }

    @Test
    void isEmpty_wrong() {

        //Initialize the historyList with a command
        HistoryList history = new HistoryList();
        history.addToHistory("test");
        assertFalse(history.isEmpty());
    }

    @Test
    void addToHistory_success_oneInput() {

        //Initialize the historyList
        HistoryList history1 = new HistoryList();

        //Add a command to historyList
        history1.addToHistory("add 1");
        assertFalse(history1.printList().equals("1: add 1\n"));
    }

    @Test
    void addToHistory_failure_multipleInputs() {

        //Initialize the historyList
        HistoryList history1 = new HistoryList();

        //Add a command to historyList
        history1.addToHistory("add 1");
        history1.addToHistory("delete 1");
        history1.addToHistory("sort e/");
        history1.addToHistory("help");
        assertFalse(history1.printList().equals(""));
    }

    @Test
    void clearList_success() {
        HistoryList history = new HistoryList();
        history.clearList();
        Queue<String> queue = new LinkedList<>();
        assertTrue(history.getList().equals(queue));
    }

    @Test
    void clearList_successAfterInput() {
        HistoryList history = new HistoryList();
        history.addToHistory("add 1");
        history.clearList();
        Queue<String> queue = new LinkedList<>();
        assertTrue(history.getList().equals(queue));
    }

    @Test
    void equals() {
        HistoryList historyList1 = new HistoryList();
        HistoryList historyList2 = new HistoryList();

        // same object -> returns true
        assertTrue(historyList1.equals(historyList1));

        // different types -> returns false
        assertFalse(historyList1.equals(1));

        // null -> returns false
        assertFalse(historyList1.equals(null));

        // different historyLists -> returns false
        assertFalse(historyList1.equals(historyList2));
    }
}
