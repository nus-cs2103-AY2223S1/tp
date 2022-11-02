package seedu.address.logic.commands.commission;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddCommissionCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.commission.Commission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalCommissions.CAT_BUILDER;
import static seedu.address.testutil.TypicalCommissions.DOG_BUILDER;
import static seedu.address.testutil.TypicalCommissions.ELEPHANT_BUILDER;

import static seedu.address.testutil.Assert.assertThrows;

public class AddCommissionCommandTest {
    @Test
    public void constructor_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommissionCommand(null));
    }

    @Test
    public void execute_modelNoSelectedCustomer_throwsCommandException() {
        AddCommissionCommand addCommissionCommand = new AddCommissionCommand(CAT_BUILDER);
        assertThrows(CommandException.class, Messages.MESSAGE_NO_ACTIVE_CUSTOMER, () ->
                addCommissionCommand.execute(new ModelStubWithoutCustomer()));
    }

    @Test
    public void execute_customerHasDuplicateCommission_throwsCommandException() {
        CustomerStubWithCommission selectedCustomer = new CustomerStubWithCommission();
        selectedCustomer.addCommission(DOG_BUILDER.build(selectedCustomer));
        ModelStubWithCustomer modelStub = new ModelStubWithCustomer(selectedCustomer);

        AddCommissionCommand addCommissionCommand = new AddCommissionCommand(DOG_BUILDER);
        assertThrows(CommandException.class, String.format(AddCommissionCommand.MESSAGE_DUPLICATE_COMMISSION,
                selectedCustomer.getName()), () -> addCommissionCommand.execute(modelStub));
    }

    @Test
    public void execute_commissionAcceptedByModel_addSuccessful() throws Exception {
        CustomerStubWithCommission selectedCustomer = new CustomerStubWithCommission();
        ModelStubWithCustomer modelStub = new ModelStubWithCustomer(selectedCustomer);

        Commission expectedCommission = ELEPHANT_BUILDER.build(selectedCustomer);

        CommandResult commandResult = new AddCommissionCommand(ELEPHANT_BUILDER).execute(modelStub);
        assertEquals(String.format(AddCommissionCommand.MESSAGE_SUCCESS, expectedCommission),
                commandResult.getFeedbackToUser());
        assertEquals(expectedCommission, selectedCustomer.getCommissionList().get(0));
    }

    @Test
    public void equals() {
        AddCommissionCommand firstAddCommissionCommand = new AddCommissionCommand(ELEPHANT_BUILDER);
        AddCommissionCommand secondAddCommissionCommand = new AddCommissionCommand(DOG_BUILDER);

        assertEquals(firstAddCommissionCommand, firstAddCommissionCommand);

        AddCommissionCommand firstAddCommissionCommandCopy = new AddCommissionCommand(ELEPHANT_BUILDER);
        assertEquals(firstAddCommissionCommand, firstAddCommissionCommandCopy);

        assertNotEquals(null, firstAddCommissionCommand);

        assertNotEquals(firstAddCommissionCommand, secondAddCommissionCommand);
    }
}
