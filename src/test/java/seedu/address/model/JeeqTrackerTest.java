package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.ALICE;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.CompanyBuilder;

public class JeeqTrackerTest {

    private final JeeqTracker jeeqTracker = new JeeqTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), jeeqTracker.getCompanyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jeeqTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyJeeqTracker_replacesData() {
        JeeqTracker newData = getTypicalJeeqTracker();
        jeeqTracker.resetData(newData);
        assertEquals(newData, jeeqTracker);
    }

    @Test
    public void resetData_withDuplicateCompanies_throwsDuplicateCompanyException() {
        // Two companies with the same identity fields
        Client editedAlice = new CompanyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Client> newCompanies = Arrays.asList(ALICE, editedAlice);
        JeeqTrackerStub newData = new JeeqTrackerStub(newCompanies);

        assertThrows(DuplicateClientException.class, () -> jeeqTracker.resetData(newData));
    }

    @Test
    public void hasCompany_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jeeqTracker.hasCompany(null));
    }

    @Test
    public void hasCompany_companyNotInJeeqTracker_returnsFalse() {
        assertFalse(jeeqTracker.hasCompany(ALICE));
    }

    @Test
    public void hasCompany_companyInJeeqTracker_returnsTrue() {
        jeeqTracker.addCompany(ALICE);
        assertTrue(jeeqTracker.hasCompany(ALICE));
    }

    @Test
    public void hasCompany_companyWithSameIdentityFieldsInJeeqTracker_returnsTrue() {
        jeeqTracker.addCompany(ALICE);
        Client editedAlice = new CompanyBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(jeeqTracker.hasCompany(editedAlice));
    }

    @Test
    public void getCompanyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jeeqTracker.getCompanyList().remove(0));
    }

    /**
     * A stub ReadOnlyJeeqTracker whose companies list can violate interface constraints.
     */
    private static class JeeqTrackerStub implements ReadOnlyJeeqTracker {
        private final ObservableList<Client> companies = FXCollections.observableArrayList();

        JeeqTrackerStub(Collection<Client> companies) {
            this.companies.setAll(companies);
        }

        @Override
        public ObservableList<Client> getCompanyList() {
            return companies;
        }
    }

}
