package longtimenosee.model.policy;

import static longtimenosee.logic.commands.CommandTestUtil.VALID_END_DATE_FLEXI;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_END_DATE_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PREMIUM_FLEXI;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PREMIUM_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_START_DATE_FLEXI;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_START_DATE_PRUSHIELD;
import static longtimenosee.testutil.TypicalAssignedPolicies.FLEXI;
import static longtimenosee.testutil.TypicalAssignedPolicies.PRUSHIELD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.AssignedPolicyBuilder;

public class AssignedPolicyTest {

    @Test
    public void isSameAssignedPolicy() {
        // same object -> returns true
        assertTrue(FLEXI.isSamePolicy(FLEXI.getPolicy()));

        // null -> returns false
        assertFalse(FLEXI.isSamePolicy(null));

        // same policy, all other attributes different -> returns true
        AssignedPolicy editedFlexi = new AssignedPolicyBuilder(FLEXI).withPremium(VALID_PREMIUM_PRUSHIELD)
                .withStartDate(VALID_START_DATE_PRUSHIELD)
                .withEndDate(VALID_END_DATE_PRUSHIELD).build();
        assertTrue(FLEXI.isSamePolicy(editedFlexi.getPolicy()));

        // different policy, all other attributes same -> returns false
        editedFlexi = new AssignedPolicyBuilder(FLEXI).withPolicy(PRUSHIELD.getPolicy()).build();
        assertFalse(FLEXI.isSamePolicy(editedFlexi.getPolicy()));

    }

    @Test
    public void equals() {
        // same values -> returns true
        AssignedPolicy flexiCopy = new AssignedPolicyBuilder(FLEXI).build();
        assertTrue(FLEXI.equals(flexiCopy));

        // same object -> returns true
        assertTrue(FLEXI.equals(FLEXI));

        // null -> returns false
        assertFalse(FLEXI.equals(null));

        // different type -> returns false
        assertFalse(FLEXI.equals(5));

        // different AssignedPolicy -> returns false
        assertFalse(FLEXI.equals(PRUSHIELD));

        // different policy -> returns false
        AssignedPolicy editedPrulife = new AssignedPolicyBuilder(PRUSHIELD).withPolicy(FLEXI.getPolicy()).build();
        assertFalse(PRUSHIELD.equals(editedPrulife));

        // different premium -> returns true
        editedPrulife = new AssignedPolicyBuilder(PRUSHIELD).withPremium(VALID_PREMIUM_FLEXI).build();
        assertTrue(PRUSHIELD.equals(editedPrulife));

        // different start date -> returns true
        editedPrulife = new AssignedPolicyBuilder(PRUSHIELD).withStartDate(VALID_START_DATE_FLEXI).build();
        assertTrue(PRUSHIELD.equals(editedPrulife));

        // different end date -> returns true
        editedPrulife = new AssignedPolicyBuilder(PRUSHIELD).withEndDate(VALID_END_DATE_FLEXI).build();
        assertTrue(PRUSHIELD.equals(editedPrulife));
    }
}
