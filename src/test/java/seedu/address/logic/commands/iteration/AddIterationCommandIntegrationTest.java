package seedu.address.logic.commands.iteration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCommissions.CAT_PRODUCER;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.Iteration;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.IterationBuilder;


/**
 * Contains integration tests (interaction with the Model and Commissions) for {@code AddIterationCommand}.
 */
public class AddIterationCommandIntegrationTest {

    private static final Supplier<Customer> EMILY_PRODUCER = () -> new CustomerBuilder().withName("Emily").build();
    private static final String CAT_COMMISSION_TITLE = VALID_TITLE_CAT;
    private Model model;

    @BeforeEach
    public void setUp() {
        model = getSetUpModelManager();
    }

    @Test
    public void execute_selectedCommissionAddNewIteration_success() throws CommandException {
        StorageWithImageStub storage = new StorageWithImageStub();
        Iteration validIteration = new IterationBuilder()
                .withImagePath(storage.getCurrentPath().toString()).build();

        Model expectedModel = getSetUpModelManager();
        BufferedImage tempImage = new BufferedImage(1, 1, 1);
        expectedModel.getSelectedCommission().getValue().addIteration(validIteration);
        AddIterationCommand addIterationCommand = new AddIterationCommand(validIteration);
        storage.saveImage(tempImage, validIteration.getImagePath());

        String actualResponse = addIterationCommand.execute(model, storage).getFeedbackToUser();
        Iteration expectedIteration = new IterationBuilder(validIteration)
                .withImagePath(storage.getCurrentPath().toString()).build();
        String expectedResponse = String.format(AddIterationCommand.MESSAGE_ADD_ITERATION_SUCCESS,
                expectedIteration, CAT_COMMISSION_TITLE);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_selectedCommissionAddDuplicateIteration_throwsCommandException() {
        Iteration duplicateIteration = new IterationBuilder().build();
        model.getSelectedCommission().getValue().addIteration(duplicateIteration);
        assertCommandFailure(new AddIterationCommand(duplicateIteration), model, null,
                String.format(AddIterationCommand.MESSAGE_DUPLICATE_ITERATION, CAT_COMMISSION_TITLE));
    }

    @Test
    public void execute_noSelectedCommission_throwsCommandException() {
        Iteration validIteration = new IterationBuilder().build();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model = expectedModel;
        model.selectCommission(null);
        assertThrows(CommandException.class, Messages.MESSAGE_NO_ACTIVE_COMMISSION, () ->
                new AddIterationCommand(validIteration).execute(model, new StorageWithImageStub()));
        assertEquals(model, expectedModel);
    }

    private static Model getSetUpModelManager() {
        Customer emily = EMILY_PRODUCER.get();
        Commission emilyCatCommission = CAT_PRODUCER.apply(emily);
        emily.addCommission(emilyCatCommission);

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.selectCustomer(emily);
        model.selectCommission(emilyCatCommission);

        return model;
    }
}
