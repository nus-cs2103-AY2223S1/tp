package seedu.address.model.person.nextofkin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class RelationshipTest {

    @Test
    public void createLevel_validLevel_success() {
        assertTrue(Relationship.createRelationship("FATHER") == Relationship.FATHER);
        assertTrue(Relationship.createRelationship("MOTHER") == Relationship.MOTHER);
        assertTrue(Relationship.createRelationship("BROTHER") == Relationship.BROTHER);
        assertTrue(Relationship.createRelationship("SISTER") == Relationship.SISTER);
        assertTrue(Relationship.createRelationship("GUARDIAN") == Relationship.GUARDIAN);
    }
}
