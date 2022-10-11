package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandFailure;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Eatery validEatery = new EateryBuilder().build();

        Model expectedModel = new ModelManager(model.getFoodGuide(), new UserPrefs());
        expectedModel.addEatery(validEatery);

        assertCommandSuccess(new AddCommand(validEatery), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEatery), expectedModel);
    }

    @Test
    public void execute_duplicateEatery_throwsCommandException() {
        Eatery eateryInList = model.getFoodGuide().getEateryList().get(0);
        assertCommandFailure(new AddCommand(eateryInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
