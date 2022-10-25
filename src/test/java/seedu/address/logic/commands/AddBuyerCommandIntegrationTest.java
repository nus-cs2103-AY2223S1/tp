package seedu.address.logic.commands;

import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddBuyerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), getTypicalPropertyBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Buyer validBuyer = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getPersonModel(), model.getPropertyModel(), new UserPrefs());
        expectedModel.addPerson(validBuyer);

        assertCommandSuccess(new AddBuyerCommand(validBuyer), model,
                String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Buyer buyerInList = model.getPersonModel().getPersonList().get(0);
        assertCommandFailure(new AddBuyerCommand(buyerInList), model, AddBuyerCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
