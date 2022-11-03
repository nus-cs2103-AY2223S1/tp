package seedu.waddle.logic.commands;

import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class CopyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWaddle(), new UserPrefs());
        expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
    }

    //    @Test
    //    public void execute_correctStage_firstItinerary() {
    //        // select first itinerary
    //        Itinerary selectedItinerary = model.getFilteredItineraryList().get(0);
    //        StageManager.getInstance().setWishStage(selectedItinerary);
    //        String expectedCommandResult = String.format(CopyCommand.MESSAGE_SUCCESS, selectedItinerary.getDescription());
    //
    //        assertCommandSuccess(new CopyCommand(), model, expectedCommandResult, expectedModel);
    //
    //        String actualClipboardData = getClipboardData();
    //        String expectedClipboardData = "Spring Trip\n    Country: Australia\n    Duration: 14 Days\n"
    //                + "    Dates: 2022-01-01 - 2022-01-14\n    Waddlers: 1\n    Budget: $300.00\n\nDay 1\n\nDay 2\n"
    //                + "\nDay 3\n\nDay 4\n\nDay 5\n\nDay 6\n\nDay 7\n\nDay 8\n\nDay 9\n\nDay 10\n\nDay 11\n\nDay 12\n"
    //                + "\nDay 13\n\nDay 14\n\n";
    //        assertEquals(expectedClipboardData, actualClipboardData);
    //    }

    private String getClipboardData() {
        String data = "";
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            assert false : e.getMessage();
        }
        return data;
    }
}
