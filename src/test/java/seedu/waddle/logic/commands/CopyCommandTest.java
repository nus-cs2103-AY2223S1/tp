package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.Itinerary;

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

    @Test
    public void execute_correctStage_firstItinerary() {
        // copy does not work on linux
        if (SystemUtils.IS_OS_LINUX) {
            return;
        }

        // select first itinerary
        Itinerary selectedItinerary = model.getFilteredItineraryList().get(0);
        StageManager.getInstance().setWishStage(selectedItinerary);
        String expectedCommandResult = String.format(CopyCommand.MESSAGE_SUCCESS,
              selectedItinerary.getDescription());

        assertCommandSuccess(new CopyCommand(), model, expectedCommandResult, expectedModel);

        String actualClipboardData = getClipboardData();
        String expectedClipboardData = "Spring Trip" + System.lineSeparator()
                + "    Country: Australia" + System.lineSeparator()
                + "    Duration: 14 Days" + System.lineSeparator()
                + "    Dates: 2022-01-01 - 2022-01-14" + System.lineSeparator()
                + "    Waddlers: 1" + System.lineSeparator()
                + "    Budget: $300.00" + System.lineSeparator()
                + System.lineSeparator() + "Day 1" + System.lineSeparator()
                + System.lineSeparator() + "Day 2" + System.lineSeparator()
                + System.lineSeparator() + "Day 3" + System.lineSeparator()
                + System.lineSeparator() + "Day 4" + System.lineSeparator()
                + System.lineSeparator() + "Day 5" + System.lineSeparator()
                + System.lineSeparator() + "Day 6" + System.lineSeparator()
                + System.lineSeparator() + "Day 7" + System.lineSeparator()
                + System.lineSeparator() + "Day 8" + System.lineSeparator()
                + System.lineSeparator() + "Day 9" + System.lineSeparator()
                + System.lineSeparator() + "Day 10" + System.lineSeparator()
                + System.lineSeparator() + "Day 11" + System.lineSeparator()
                + System.lineSeparator() + "Day 12" + System.lineSeparator()
                + System.lineSeparator() + "Day 13" + System.lineSeparator()
                + System.lineSeparator() + "Day 14" + System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedClipboardData, actualClipboardData);
    }

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
