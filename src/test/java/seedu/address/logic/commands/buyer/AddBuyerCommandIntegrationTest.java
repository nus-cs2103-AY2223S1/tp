package seedu.address.logic.commands.buyer;

import seedu.address.model.Model;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddBuyerCommandIntegrationTest {

    private Model model;
    //    @BeforeEach
    //    public void setUp() {
    //        model = new ModelManager(getTypicalPersonsBook(), getTypicalPropertyBook(), new UserPrefs());
    //        model.setPersonListPanel(new BuyerListPanel(FXCollections.observableArrayList()));
    //    }

    //    @Test
    //    public void execute_newPerson_success() {
    //        Buyer validBuyer = new BuyerBuilder().build();
    //
    //        Model expectedModel = new ModelManager(model.getPersonModel(), model.getPropertyModel(), new UserPrefs());
    //
    //        expectedModel.addPerson(validBuyer);
    //
    //        assertCommandSuccess(new AddBuyerCommand(validBuyer), model,
    //                String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), expectedModel);
    //    }

    //    @Test
    //    public void execute_duplicatePerson_throwsCommandException() {
    //        Buyer buyerInList = model.getPersonModel().getPersonList().get(0);
    //        assertCommandFailure(new AddBuyerCommand(buyerInList), model, AddBuyerCommand.MESSAGE_DUPLICATE_PERSON);
    //    }
}
