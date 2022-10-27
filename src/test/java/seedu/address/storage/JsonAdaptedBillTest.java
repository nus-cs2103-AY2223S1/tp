package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BILL_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAYMENT_STATUS;
import static seedu.address.storage.JsonAdaptedBill.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBills.BILL_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.BillDate;
import seedu.address.model.bill.PaymentStatus;
import seedu.address.model.patient.Name;

public class JsonAdaptedBillTest {
    public static final String VALID_NAME = "Benson";
    private static final String INVALID_NAME = "R@chel";
    @Test
    public void toModelType_validBill_returnsAppointment() throws Exception {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1);
        assertEquals(BILL_1, bill.toModelType());
    }

    @Test
    public void toModelType_validBillDetails_returnsAppointment() throws Exception {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1.getAppointment().getName().toString(),
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), BILL_1.getBillDate().toString(),
                BILL_1.getAmount().toString(), BILL_1.getPaymentStatus().toString());
        assertEquals(BILL_1, bill.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBill bill =
                new JsonAdaptedBill(INVALID_NAME, BILL_1.getAppointment().getSlot().toString(),
                        BILL_1.getAppointment().getMedicalTest().toString(),
                        BILL_1.getAppointment().getDoctor().toString(), BILL_1.getBillDate().toString(),
                        BILL_1.getAmount().toString(), BILL_1.getPaymentStatus().toString());
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

    @Test
    public void toModelType_nullBillDate_throwsIllegalValueException() {
        JsonAdaptedBill bill = new JsonAdaptedBill(null,
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), null,
                BILL_1.getAmount().toString(), BILL_1.getPaymentStatus().toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

    @Test
    public void toModelType_invalidBillDate_throwsIllegalValueException() {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1.getAppointment().getName().toString(),
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), INVALID_BILL_DATE_DESC,
                BILL_1.getAmount().toString(), BILL_1.getPaymentStatus().toString());
        String expectedMessage = BillDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1.getAppointment().getName().toString(),
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), BILL_1.getBillDate().toString(),
                null, BILL_1.getPaymentStatus().toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1.getAppointment().getName().toString(),
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), BILL_1.getBillDate().toString(),
                INVALID_AMOUNT_DESC, BILL_1.getPaymentStatus().toString());
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

    @Test
    public void toModelType_nullPaymentStatus_throwsIllegalValueException() {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1.getAppointment().getName().toString(),
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), BILL_1.getBillDate().toString(),
                BILL_1.getAmount().toString(), null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PaymentStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

    @Test
    public void toModelType_invalidPaymentStatus_throwsIllegalValueException() {
        JsonAdaptedBill bill = new JsonAdaptedBill(BILL_1.getAppointment().getName().toString(),
                BILL_1.getAppointment().getSlot().toString(),
                BILL_1.getAppointment().getMedicalTest().toString(),
                BILL_1.getAppointment().getDoctor().toString(), BILL_1.getBillDate().toString(),
                BILL_1.getAmount().toString(), INVALID_PAYMENT_STATUS);
        String expectedMessage = PaymentStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, bill::toModelType);
    }

}
