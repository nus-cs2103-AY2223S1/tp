package seedu.waddle.logic.commands;

import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.ItineraryBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWaddle(), new UserPrefs());
    }

    @Test
    public void execute_newItinerary_success() {
        Itinerary validItinerary = new ItineraryBuilder().build();

        Model expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
        expectedModel.addItinerary(validItinerary);

        assertCommandSuccess(new AddCommand(validItinerary), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validItinerary), expectedModel);
    }

    @Test
    public void execute_duplicateItinerary_throwsCommandException() {
        Itinerary itineraryInList = model.getWaddle().getItineraryList().get(0);
        assertCommandFailure(new AddCommand(itineraryInList), model, AddCommand.MESSAGE_DUPLICATE_ITINERARY);
    }

}
