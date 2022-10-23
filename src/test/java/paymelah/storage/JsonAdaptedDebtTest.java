package paymelah.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalDebts.CHICKEN_RICE;

import org.junit.jupiter.api.Test;

import paymelah.commons.exceptions.IllegalValueException;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

public class JsonAdaptedDebtTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_MONEY = "two dollars";
    private static final String INVALID_DATE = "1999-02-32";
    private static final String INVALID_TIME = "25:61";

    private static final String VALID_DESCRIPTION = CHICKEN_RICE.getDescription().toString();
    private static final String VALID_MONEY = CHICKEN_RICE.getMoney().toString();
    private static final String VALID_DATE = CHICKEN_RICE.getDate().toString();
    private static final String VALID_TIME = CHICKEN_RICE.getTime().toString();

    @Test
    public void toModelType_validDetails_returnsDebt() throws IllegalValueException {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(VALID_DESCRIPTION, VALID_MONEY, VALID_DATE, VALID_TIME);
        assertEquals(CHICKEN_RICE, debt.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(INVALID_DESCRIPTION, VALID_MONEY, VALID_DATE, VALID_TIME);
        assertThrows(IllegalValueException.class, Description.MESSAGE_CONSTRAINTS, debt::toModelType);
    }

    @Test
    public void toModelType_invalidMoney_throwsIllegalValueException() {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(VALID_DESCRIPTION, INVALID_MONEY, VALID_DATE, VALID_TIME);
        assertThrows(IllegalValueException.class, Money.MESSAGE_CONSTRAINTS, debt::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(VALID_DESCRIPTION, VALID_MONEY, INVALID_DATE, VALID_TIME);
        assertThrows(IllegalValueException.class, DebtDate.MESSAGE_CONSTRAINTS, debt::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedDebt debt = new JsonAdaptedDebt(VALID_DESCRIPTION, VALID_MONEY, VALID_DATE, INVALID_TIME);
        assertThrows(IllegalValueException.class, DebtTime.MESSAGE_CONSTRAINTS, debt::toModelType);
    }
}
