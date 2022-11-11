package longtimenosee.model.policy;



import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMMISSION_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMPANY_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COMPANY_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COVERAGE_MANUEXTRA;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_COVERAGE_PRULIFE;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_TITLE_MANUEXTRA;
import static longtimenosee.testutil.Assert.assertThrows;
import static longtimenosee.testutil.TypicalPolicies.MANUEXTRA;
import static longtimenosee.testutil.TypicalPolicies.PRULIFE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PolicyBuilder;

public class PolicyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Policy policy = new PolicyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> policy.getCoverages().remove(0));
    }

    @Test
    public void isSamePolicy() {
        // same object -> returns true
        assertTrue(PRULIFE.isSamePolicy(PRULIFE));

        // null -> returns false
        assertFalse(PRULIFE.isSamePolicy(null));

        // same name and company, all other attributes different -> returns true
        Policy editedPrulife = new PolicyBuilder(PRULIFE).withCoverages(VALID_COVERAGE_PRULIFE)
                .withCompany(VALID_COMPANY_PRULIFE)
                .withCommission(VALID_COMMISSION_MANUEXTRA).build();
        assertTrue(PRULIFE.isSamePolicy(editedPrulife));

        // different name, all other attributes same -> returns false
        editedPrulife = new PolicyBuilder(PRULIFE).withTitle(VALID_TITLE_MANUEXTRA).build();
        assertFalse(PRULIFE.isSamePolicy(editedPrulife));

        // different company, all other attributes same -> returns false
        editedPrulife = new PolicyBuilder(PRULIFE).withCompany(VALID_COMPANY_MANUEXTRA).build();
        assertFalse(PRULIFE.isSamePolicy(editedPrulife));

        // name differs in case, all other attributes same -> returns false
        Policy editedManuextra = new PolicyBuilder(MANUEXTRA).withTitle(VALID_TITLE_MANUEXTRA.toLowerCase()).build();
        assertFalse(MANUEXTRA.isSamePolicy(editedManuextra));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_TITLE_MANUEXTRA + " ";
        editedManuextra = new PolicyBuilder(MANUEXTRA).withTitle(nameWithTrailingSpaces).build();
        assertFalse(MANUEXTRA.isSamePolicy(editedManuextra));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Policy prulifeCopy = new PolicyBuilder(PRULIFE).build();
        assertTrue(PRULIFE.equals(prulifeCopy));

        // same object -> returns true
        assertTrue(PRULIFE.equals(PRULIFE));

        // null -> returns false
        assertFalse(PRULIFE.equals(null));

        // different type -> returns false
        assertFalse(PRULIFE.equals(5));

        // different Policy -> returns false
        assertFalse(PRULIFE.equals(MANUEXTRA));

        // different title -> returns false
        Policy editedPrulife = new PolicyBuilder(PRULIFE).withTitle(VALID_TITLE_MANUEXTRA).build();
        assertFalse(PRULIFE.equals(editedPrulife));

        // different coverage -> returns false
        editedPrulife = new PolicyBuilder(PRULIFE).withCoverages(VALID_COVERAGE_MANUEXTRA).build();
        assertFalse(PRULIFE.equals(editedPrulife));

        // different company -> returns false
        editedPrulife = new PolicyBuilder(PRULIFE).withCompany(VALID_COMPANY_MANUEXTRA).build();
        assertFalse(PRULIFE.equals(editedPrulife));

        // different commission -> returns false
        editedPrulife = new PolicyBuilder(PRULIFE).withCommission(VALID_COMMISSION_MANUEXTRA).build();
        assertFalse(PRULIFE.equals(editedPrulife));
    }
}
