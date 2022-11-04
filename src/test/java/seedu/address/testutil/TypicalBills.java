package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BILL_DATE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BILL_DATE_8;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_3;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_4;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_5;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_6;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentsHealthContact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.HealthContact;
import seedu.address.model.bill.Bill;

/**
 * A utility class containing a list of {@code Bill} objects to be used in tests.
 */
public class TypicalBills {

    public static final Bill BILL_1 = new BillBuilder().withAppointment(APPOINTMENT_1).withBillDate("2019-01-23")
            .withAmount("1000.23").withPaymentStatus("paid").build();
    public static final Bill BILL_2 = new BillBuilder().withAppointment(APPOINTMENT_2).withBillDate("2020-12-22")
            .withAmount("1001.23").withPaymentStatus("unpaid").build();
    public static final Bill BILL_3 = new BillBuilder().withAppointment(APPOINTMENT_3).withBillDate("2021-11-24")
            .withAmount("1010.23").withPaymentStatus("paid").build();
    public static final Bill BILL_4 = new BillBuilder().withAppointment(APPOINTMENT_4).withBillDate("2022-10-21")
            .withAmount("1011.23").withPaymentStatus("unpaid").build();
    public static final Bill BILL_5 = new BillBuilder().withAppointment(APPOINTMENT_5).withBillDate("2023-09-25")
            .withAmount("1100.23").withPaymentStatus("paid").build();
    public static final Bill BILL_6 = new BillBuilder().withAppointment(APPOINTMENT_6).withBillDate("2023-08-20")
            .withAmount("1101.23").withPaymentStatus("unpaid").build();

    public static final Bill BILL_7 = new BillBuilder().withAppointment(APPOINTMENT_1).withBillDate(VALID_BILL_DATE_7)
            .withAmount(VALID_AMOUNT_7).withPaymentStatus("paid").build();
    public static final Bill BILL_8 = new BillBuilder().withAppointment(APPOINTMENT_2).withBillDate(VALID_BILL_DATE_8)
            .withAmount(VALID_AMOUNT_8).withPaymentStatus("paid").build();

    private TypicalBills() {} // prevents instantiation

    /**
     * Returns an {@code HealthContact} with all the typical patients.
     */
    public static HealthContact getTypicalBillsHealthContact() {
        HealthContact ab = getTypicalAppointmentsHealthContact();

        for (Bill bill : getTypicalBills()) {
            ab.addBill(bill);
        }
        return ab;
    }

    public static List<Bill> getTypicalBills() {
        return new ArrayList<>(Arrays.asList(BILL_1, BILL_2, BILL_3,
                BILL_4, BILL_5, BILL_6));
    }
}
