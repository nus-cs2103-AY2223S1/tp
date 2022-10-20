package longtimenosee.logic.commands;

import static longtimenosee.model.person.Birthday.BIRTHDAY_COMPARATOR;
import static longtimenosee.model.person.Email.EMAIL_COMPARATOR;
import static longtimenosee.model.person.Income.INCOME_COMPARATOR;
import static longtimenosee.model.person.Name.NAME_COMPARATOR;
import static longtimenosee.model.person.Person.DEFAULT_COMPARATOR;
import static longtimenosee.model.person.Phone.PHONE_COMPARATOR;
import static longtimenosee.model.person.RiskAppetite.RISK_APPETITE_COMPARATOR;
import static longtimenosee.testutil.Assert.assertThrows;
import static longtimenosee.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.AddressBook;
import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.person.Person;




public class SortCommandTest {

    private AddressBook firstAddBook = getTypicalAddressBook();
    private AddressBook secondAddBook = getTypicalAddressBook();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void equals() {
        SortCommand firstSortCommand = new SortCommand("name");
        SortCommand secondSortCommand = new SortCommand("birthday");
        SortCommand thirdSortCommand = new SortCommand("name");
        SortCommand fourthSortCommand = new SortCommand("risk appetite");

        // same object -> returns true
        assertTrue(firstSortCommand.equals(firstSortCommand));


        // same sorting metric -> returns true
        assertTrue(firstSortCommand.equals(thirdSortCommand));


        // different object -> return false
        assertFalse(secondSortCommand.equals(fourthSortCommand));

        // null -> return false
        assertFalse(firstSortCommand.equals(null));


        // same sorting metric -> return true
        firstAddBook.sort(NAME_COMPARATOR);
        secondAddBook.sort(NAME_COMPARATOR);
        assertTrue(firstAddBook.equals(secondAddBook));


        // different sorting metric -> return false
        firstAddBook.sort(BIRTHDAY_COMPARATOR);
        secondAddBook.sort(DEFAULT_COMPARATOR);
        assertFalse(firstAddBook.equals(secondAddBook));

    }

    @Test
    public void testSortName_sorted() {
        firstAddBook.sort(NAME_COMPARATOR);
        boolean isSorted = true;
        List<Person> listToTest = firstAddBook.getPersonList();
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Person before = listToTest.get(i);
            Person after = listToTest.get(i + 1);
            if (before.getName().fullName.compareTo(after.getName().fullName) != -1) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }

    @Test
    public void testSortPhone_sorted() {
        firstAddBook.sort(PHONE_COMPARATOR);
        boolean isSorted = true;
        List<Person> listToTest = firstAddBook.getPersonList();
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Person before = listToTest.get(i);
            Person after = listToTest.get(i + 1);
            if (before.getPhone().value.compareTo(after.getPhone().value) > 0) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }

    @Test
    public void testSortBirthday_sorted() {
        firstAddBook.sort(BIRTHDAY_COMPARATOR);
        boolean isSorted = true;
        List<Person> listToTest = firstAddBook.getPersonList();
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Person before = listToTest.get(i);
            Person after = listToTest.get(i + 1);
            if (before.getBirthday().getBirthday().compareTo(after.getBirthday().getBirthday()) > 0) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }

    @Test
    public void testSortIncome_sorted() {
        firstAddBook.sort(INCOME_COMPARATOR);
        boolean isSorted = true;
        List<Person> listToTest = firstAddBook.getPersonList();
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Person before = listToTest.get(i);
            Person after = listToTest.get(i + 1);
            if (Double.compare(before.getIncome().getIncome(), after.getIncome().getIncome()) != -1) {
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }

    @Test
    public void testSortRiskAppetite_sorted() {
        firstAddBook.sort(RISK_APPETITE_COMPARATOR);
        boolean isSorted = true;
        List<Person> listToTest = firstAddBook.getPersonList();
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Person before = listToTest.get(i);
            Person after = listToTest.get(i + 1);
            if (before.getRiskAppetite().toString().equals("M")) {
                if (after.getRiskAppetite().toString().equals("L")) {
                    isSorted = false;
                    break;
                }
            }
            if (before.getRiskAppetite().toString().equals("H")) {
                if ((after.getRiskAppetite().toString().equals("M"))
                        || after.getRiskAppetite().toString().equals("L")) {
                    isSorted = false;
                    break;
                }
            }
        }
        assertTrue(isSorted);
    }

    @Test
    public void testEmailSort_notSorted() {
        firstAddBook.sort(EMAIL_COMPARATOR);
        boolean isSorted = true;
        // testing if sorted by name order
        List<Person> listToTest = firstAddBook.getPersonList();
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Person before = listToTest.get(i);
            Person after = listToTest.get(i + 1);
            if (before.getName().fullName.compareTo(after.getName().fullName) != -1) {
                isSorted = false;
                break;
            }
        }
        assertFalse(isSorted);
    }

    @Test
    public void testInvalidCommandInput() {
        assertThrows(CommandException.class, () -> new SortCommand("aaa").execute(model));
    }

}
