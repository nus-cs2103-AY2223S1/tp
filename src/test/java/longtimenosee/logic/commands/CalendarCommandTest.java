package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.CommandTestUtil.showEventAtIndex;
import static longtimenosee.testutil.TypicalEvents.getTypicalAddressBook;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.event.Event;

public class CalendarCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new CalendarCommand(), model, CalendarCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEventAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new CalendarCommand(), model, CalendarCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listProperlyFiltered() {
        List<Event> list = model.calendarView();
        assertEquals(2, list.size());
    }

    @Test
    public void execute_filteredListUnaffected() {
        model.calendarView();
        expectedModel.calendarView();
        assertEquals(7, model.getFilteredEventList().size());
        assertEquals(7, expectedModel.getFilteredEventList().size());
    }

}
