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

/**
 * Contains integration tests (interaction with the Model) for {@code AddTagCommand}.
 */
public class AddTagCommandIntegrationTest {
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

        assertCommandSuccess(new AddTagCommand(validTag), model,
                String.format(AddTagCommand.MESSAGE_SUCCESS, validTag), expectedModel);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag tagInList = model.getFoodRem().getTagList().get(0);
        assertCommandFailure(new AddTagCommand(tagInList), model, AddTagCommand.MESSAGE_DUPLICATE_TAG);
    }
}
