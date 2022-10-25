package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.NextSessionUtil.MESSAGE_NO_NEXT_SESSION_FOUND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.JOLENE;
import static seedu.address.testutil.TypicalPersons.KALEY;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.Session;
import seedu.address.testutil.AddressBookBuilder;

public class NextSessionUtilTest {

    public static final String MESSAGE_NEXT_SESSION_FEEDBACK_TEST_SUCCESS = "Next Session: " + JOLENE.getName()
            + " " + JOLENE.getSessionList().sessionList.get(0);

    @Test
    public void nextSessionFeedback_noSession() {
        // No Person in Pupilist.
        AddressBook addressBook = new AddressBook();
        NextSessionUtil nextSessionUtil = new NextSessionUtil(addressBook.getPersonList());
        assertEquals(nextSessionUtil.nextSessionFeedback(), MESSAGE_NO_NEXT_SESSION_FOUND);

        // Add TypicalPersons without Sessions.
        addressBook.addPerson(CARL);
        addressBook.addPerson(DANIEL);
        addressBook.addPerson(ELLE);

        assertEquals(nextSessionUtil.nextSessionFeedback(), MESSAGE_NO_NEXT_SESSION_FOUND);
    }

    @Test
    public void nextSessionFeedback_success() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON)
                .withPerson(JOLENE).withPerson(KALEY).build();
        NextSessionUtilStub nextSessionUtilStub = new NextSessionUtilStub(addressBook.getPersonList());
        addressBook.getPersonList();
        assertEquals(MESSAGE_NEXT_SESSION_FEEDBACK_TEST_SUCCESS, nextSessionUtilStub.nextSessionFeedback());
    }

    private class NextSessionUtilStub extends NextSessionUtil {
        /**
         * Constructs an {@code NextSessionUtil}.
         *
         * @param personList
         */
        public NextSessionUtilStub(ObservableList<Person> personList) {
            super(personList);
        }

        @Override
        protected Session getTimeNowAsSession() {
            return new Session("Sun 00:00");
        }
    }

}
