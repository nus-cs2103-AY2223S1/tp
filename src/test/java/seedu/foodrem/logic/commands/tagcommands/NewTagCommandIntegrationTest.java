package seedu.foodrem.logic.commands.tagcommands;

import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodrem.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodrem.testutil.TypicalFoodRem.getTypicalFoodRem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.viewmodels.TagsWithMessage;

/**
 * Contains integration tests (interaction with the Model) for {@code AddTagCommand}.
 */
public class NewTagCommandIntegrationTest {
    private static final String EXPECTED_ERROR_DUPLICATE = "This tag already exists in FoodRem";
    private static final String EXPECTED_SUCCESS_MESSAGE = "New tag added:";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodRem(), new UserPrefs());
    }

    @Test
    public void execute_newTag_success() {
        Tag validTag = new TagBuilder().withTagName("test").build();

        Model expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        expectedModel.addTag(validTag);

        assertCommandSuccess(new NewTagCommand(validTag), model,
                new TagsWithMessage(EXPECTED_SUCCESS_MESSAGE, validTag), expectedModel);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag tagInList = model.getFoodRem().getTagList().get(0);
        assertCommandFailure(new NewTagCommand(tagInList), model, EXPECTED_ERROR_DUPLICATE);
    }
}
