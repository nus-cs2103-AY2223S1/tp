package seedu.address.logic.commands.iteration;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCommissions.DOG_PRODUCER;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.Iteration;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.IterationBuilder;

/**
 * Contains integration tests (interaction with the Model and Commissions) for {@code DeleteIterationCommand}.
 */
public class DeleteIterationCommandIntegrationTest {
    private static final Supplier<Customer> ALAN_PRODUCER = () -> new CustomerBuilder().withName("Alan").build();
    private Model model;

    @BeforeEach
    public void setUp() {
        model = getSetUpModelManager();
    }

    @Test
    public void execute_selectedCommissionDeleteExistingIteration_success() {
        Iteration existingIteration = new IterationBuilder().build();

        Model expectedModel = getSetUpModelManager();
        expectedModel.getSelectedCommission().getValue().removeIteration(existingIteration);

        assertCommandSuccess(new DeleteIterationCommand(INDEX_FIRST), model,
                String.format(DeleteIterationCommand.MESSAGE_DELETE_ITERATION_SUCCESS,
                        existingIteration), expectedModel);
    }

    @Test
    public void execute_selectedCommissionDeleteNonExistentIteration_throwsCommandException() {
        assertCommandFailure(new DeleteIterationCommand(INDEX_SECOND), model, null,
                Messages.MESSAGE_INVALID_ITERATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noSelectedCommission_throwsCommandException() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.selectCommission(null);
        assertCommandFailure(new DeleteIterationCommand(INDEX_FIRST),
                model, null,
                Messages.MESSAGE_NO_ACTIVE_COMMISSION);
    }

    private static Model getSetUpModelManager() {
        Customer alan = ALAN_PRODUCER.get();
        Commission alanDogCommission = DOG_PRODUCER.apply(alan);
        alan.addCommission(alanDogCommission);
        alanDogCommission.addIteration(new IterationBuilder().build());
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.selectCustomer(alan);
        model.selectCommission(alanDogCommission);
        return model;
    }
}
