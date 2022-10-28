package seedu.condonery.logic.commands;

import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.AddPropertyCommand;
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
        model = new ModelManager(getTypicalPropertyDirectory(), getTypicalClientDirectory(), new UserPrefs());
    }

    @Test
    public void execute_newProperty_success() {
        Property validProperty = new PropertyBuilder().build();

        Model expectedModel = new ModelManager(
                model.getPropertyDirectory(), model.getClientDirectory(), new UserPrefs());
        expectedModel.addProperty(validProperty);

        CommandTestUtil.assertCommandSuccess(new AddPropertyCommand(validProperty), model,
            String.format(AddPropertyCommand.MESSAGE_SUCCESS + ". No rejected client names.",
                validProperty), expectedModel);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property propertyInList = model.getPropertyDirectory().getPropertyList().get(0);
        CommandTestUtil.assertCommandFailure(
                new AddPropertyCommand(propertyInList), model, AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

}
