package paymelah.storage;

import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalDebts.CHICKEN_RICE;

import org.junit.jupiter.api.Test;

import paymelah.commons.exceptions.IllegalValueException;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

public class JsonAdaptedDebtTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_MONEY = "two dollars";

    private static final String VALID_DESCRIPTION = CHICKEN_RICE.getDescription().toString();
    private static final String VALID_MONEY = CHICKEN_RICE.getMoney().toString();

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(INVALID_DESCRIPTION, VALID_MONEY);
        assertThrows(IllegalValueException.class, Description.MESSAGE_CONSTRAINTS, debt::toModelType);
    }

    @Test
    public void toModelType_invalidMoney_throwsIllegalValueException() {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(VALID_DESCRIPTION, INVALID_MONEY);
        assertThrows(IllegalValueException.class, Money.MESSAGE_CONSTRAINTS, debt::toModelType);
    }
}
