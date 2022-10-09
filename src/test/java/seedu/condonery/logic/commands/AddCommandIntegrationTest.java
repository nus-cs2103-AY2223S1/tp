package seedu.condonery.logic.commands;

import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.Property;
import seedu.condonery.testutil.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());
    }

    @Test
    public void execute_newProperty_success() {
        Property validProperty = new PropertyBuilder().build();

        Model expectedModel = new ModelManager(model.getPropertyDirectory(), new UserPrefs());
        expectedModel.addProperty(validProperty);

        CommandTestUtil.assertCommandSuccess(new AddCommand(validProperty), model,
            String.format(AddCommand.MESSAGE_SUCCESS, validProperty), expectedModel);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property personInList = model.getPropertyDirectory().getPropertyList().get(0);
        CommandTestUtil.assertCommandFailure(
                new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

}
