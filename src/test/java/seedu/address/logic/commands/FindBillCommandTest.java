package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BILL_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBills.BILL_1;
import static seedu.address.testutil.TypicalBills.BILL_2;
//import static seedu.address.testutil.TypicalBills.BILL_3;
//import static seedu.address.testutil.TypicalBills.BILL_4;
import static seedu.address.testutil.TypicalBills.BILL_5;
import static seedu.address.testutil.TypicalBills.BILL_6;
import static seedu.address.testutil.TypicalBills.getTypicalBillsHealthContact;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.BillDate;
import seedu.address.model.bill.PaymentStatus;
import seedu.address.model.patient.Name;

public class FindBillCommandTest {
    private Model model = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());
    @Test
    public void equals() {
        Optional<Predicate<Name>> firstNamePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("MEIER".toLowerCase()));
        Optional<Predicate<PaymentStatus>> firstPaymentStatusPredicate = Optional.of(paymentStatus -> paymentStatus.toString().toLowerCase()
                .contains("PAID".toLowerCase()));
        Optional<Predicate<BillDate>> firstBillDatePredicate = Optional.of(billDate -> billDate.toString()
                .contains("2022-"));
        Optional<Predicate<Amount>> firstAmountPredicate = Optional.of(amount -> amount.toString().toLowerCase()
                .contains("1000".toLowerCase()));

        Optional<Predicate<Name>> secondNamePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("pauLINE".toLowerCase()));
        Optional<Predicate<PaymentStatus>> secondPaymentStatusPredicate = Optional.of(paymentStatus -> paymentStatus.toString().toLowerCase()
                .contains("UNpaiD".toLowerCase()));
        Optional<Predicate<BillDate>> secondBillDatePredicate = Optional.of(billDate -> billDate.toString()
                .contains("-08".toLowerCase()));
        Optional<Predicate<Amount>> secondAmountPredicate = Optional.of(amount -> amount.toString().toLowerCase()
                .contains(".23".toLowerCase()));

        FindBillCommand firstCommand = new FindBillCommand(firstNamePredicate, firstPaymentStatusPredicate,
                firstBillDatePredicate, firstAmountPredicate);
        FindBillCommand secondCommand = new FindBillCommand(secondNamePredicate, secondPaymentStatusPredicate,
                secondBillDatePredicate, secondAmountPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        /*
        // same values -> returns true
        FindBillCommand firstCommandCopy = new FindBillCommand(firstNamePredicate, firstPaymentStatusPredicate,
                firstBillDatePredicate, firstAmountPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));*/

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }


    @Test
    public void execute_findByName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 0);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("bernice".toLowerCase()));
        FindBillCommand command = new FindBillCommand(namePredicate, Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredBillList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBillList());
    }

    @Test
    public void execute_findByName_found() {
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 1);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("pauline".toLowerCase()));
        FindBillCommand command = new FindBillCommand(namePredicate, Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredBillList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_1), model.getFilteredBillList());
    }

    /*
    @Test
    public void execute_findByPaymentStatus_found() {
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 3);
        Optional<Predicate<PaymentStatus>> paymentStatusPredicate = Optional.of(paymentStatus -> paymentStatus.toString().toLowerCase()
                .contains("PAID".toLowerCase()));
        FindBillCommand command = new FindBillCommand(Optional.empty(), paymentStatusPredicate, Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredBillList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        //ObservableList<Bill> test =  model.getFilteredBillList();
        assertEquals(Arrays.asList(BILL_1, BILL_3, BILL_5), model.getFilteredBillList());
    }*/

    @Test
    public void execute_findByBillDate_found() {
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 2);
        Optional<Predicate<BillDate>> billDatePredicate = Optional.of(billDate -> billDate.toString()
                .contains("2023-"));
        FindBillCommand command = new FindBillCommand(Optional.empty(), Optional.empty(), billDatePredicate,
                Optional.empty());
        expectedModel.updateFilteredBillList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_5, BILL_6),
                model.getFilteredBillList());
    }

    @Test
    public void execute_findByAmount_found() {
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 1);
        Optional<Predicate<Amount>> amountPredicate = Optional.of(amount -> amount.toString().toLowerCase()
                .contains("1101".toLowerCase()));
        FindBillCommand command = new FindBillCommand(Optional.empty(),
                Optional.empty(), Optional.empty(), amountPredicate);
        expectedModel.updateFilteredBillList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_6),
                model.getFilteredBillList());
    }

    @Test
    public void execute_multipleFields_found() {
        String expectedMessage = String.format(MESSAGE_BILL_LISTED_OVERVIEW, 1);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("MEIER".toLowerCase()));
        Optional<Predicate<PaymentStatus>> testPredicate = Optional.of(paymentStatus -> paymentStatus.toString().toLowerCase()
                .contains("PAID".toLowerCase()));
        Optional<Predicate<BillDate>> slotPredicate = Optional.of(billDate -> billDate.toString()
                .contains("2020-"));
        Optional<Predicate<Amount>> doctorPredicate = Optional.of(amount -> amount.toString().toLowerCase()
                .contains(".23"));
        FindBillCommand command = new FindBillCommand(namePredicate, testPredicate, slotPredicate,
                doctorPredicate);
        expectedModel.updateFilteredBillList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BILL_2), model.getFilteredBillList());
    }
}
