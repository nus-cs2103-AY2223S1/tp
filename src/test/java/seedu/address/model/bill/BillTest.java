package seedu.address.model.bill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalBills.BILL_1;
import static seedu.address.testutil.TypicalBills.BILL_2;
import static seedu.address.testutil.TypicalBills.BILL_3;
import static seedu.address.testutil.TypicalBills.BILL_4;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.BillBuilder;

public class BillTest {

    @Test
    public void isSameBill() {
        // same object -> returns true
        assertTrue(BILL_1.isSameBill(BILL_1));

        // null -> returns false
        assertFalse(BILL_1.isSameBill(null));

        // same appointment, all other attributes different -> returns true
        Bill editedBill1 = new BillBuilder(BILL_1).withAmount(BILL_2.getAmount().toString())
                .withBillDate(BILL_2.getBillDate().toString())
                .withPaymentStatus(BILL_2.getPaymentStatus().toString())
                .build();
        assertTrue(BILL_1.isSameBill(editedBill1));

        // all attributes same -> returns true
        editedBill1 = new BillBuilder()
                .withAppointment(BILL_1.getAppointment())
                .withAmount(BILL_1.getAmount().toString())
                .withBillDate(BILL_1.getBillDate().toString())
                .withPaymentStatus(BILL_1.getPaymentStatus().toString())
                .build();
        assertTrue(BILL_1.isSameBill(editedBill1));

        // all attributes same -> returns true
        Appointment tempAppointment = BILL_1.getAppointment();
        editedBill1 = new BillBuilder()
                .withAppointment(tempAppointment.getName().toString(),
                        tempAppointment.getMedicalTest().toString(),
                        tempAppointment.getSlot().toString(),
                        tempAppointment.getDoctor().toString())
                .withAmount(BILL_1.getAmount().toString())
                .withBillDate(BILL_1.getBillDate().toString())
                .withPaymentStatus(BILL_1.getPaymentStatus().toString())
                .build();
        assertTrue(BILL_1.isSameBill(editedBill1));

        // name has trailing spaces, all other attributes same -> returns false
        tempAppointment = BILL_1.getAppointment();
        String testWithTrailingSpaces = tempAppointment.getName().toString() + " ";
        editedBill1 = new BillBuilder(BILL_1)
                .withAppointment(testWithTrailingSpaces,
                        tempAppointment.getMedicalTest().toString(),
                        tempAppointment.getSlot().toString(),
                        tempAppointment.getDoctor().toString())
                .build();
        assertFalse(BILL_1.isSameBill(editedBill1));

        // medical test has trailing spaces, all other attributes same -> returns false
        tempAppointment = BILL_1.getAppointment();
        testWithTrailingSpaces = tempAppointment.getMedicalTest().toString() + " ";
        editedBill1 = new BillBuilder(BILL_1)
                .withAppointment(tempAppointment.getName().toString(),
                        testWithTrailingSpaces,
                        tempAppointment.getSlot().toString(),
                        tempAppointment.getDoctor().toString())
                .build();
        assertFalse(BILL_1.isSameBill(editedBill1));

        // doctor has trailing spaces, all other attributes same -> returns false
        tempAppointment = BILL_1.getAppointment();
        testWithTrailingSpaces = tempAppointment.getDoctor().toString() + " ";
        editedBill1 = new BillBuilder(BILL_1)
                .withAppointment(tempAppointment.getName().toString(),
                        tempAppointment.getMedicalTest().toString(),
                        tempAppointment.getSlot().toString(),
                        testWithTrailingSpaces)
                .build();
        assertFalse(BILL_1.isSameBill(editedBill1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Bill billOneCopy = new BillBuilder(BILL_1).build();
        assertTrue(BILL_1.isSameBill(billOneCopy));

        // same object -> returns true
        assertTrue(BILL_2.equals(BILL_2));

        // null -> returns false
        assertFalse(BILL_3.equals(null));

        // different type -> returns false
        assertFalse(BILL_4.equals(5));

        // different bills -> returns false
        assertFalse(BILL_1.equals(BILL_2));

        // different appointment -> returns false
        Bill editedBill1 = new BillBuilder(BILL_1).withAppointment(APPOINTMENT_2).build();
        assertFalse(BILL_1.equals(editedBill1));

        // different bill date -> returns false
        editedBill1 = new BillBuilder(BILL_1).withBillDate(BILL_2.getBillDate().toString()).build();
        assertFalse(BILL_1.equals(editedBill1));

        // different amount -> returns false
        editedBill1 = new BillBuilder(BILL_1).withAmount(BILL_2.getAmount().toString()).build();
        assertFalse(BILL_1.equals(editedBill1));

        // different payment status -> returns false
        editedBill1 = new BillBuilder(BILL_1).withPaymentStatus(BILL_2.getPaymentStatus().toString()).build();
        assertFalse(BILL_1.equals(editedBill1));
    }
}
