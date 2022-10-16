package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PetCertificateTest {

    @Test
    public void constructor_null_emptyString() {
        PetCertificate pc = new PetCertificate(null);
        PetCertificate expected = new PetCertificate("");
        assertEquals(pc, expected);
    }

    @Test
    public void getCertificate() {
        PetCertificate pc = new PetCertificate("AVA");
        assertEquals(pc.getCertificate(), "AVA");
    }

    @Test
    public void isSamePetCertificate() {
        PetCertificate pc = new PetCertificate("AVA");
        assertEquals(pc, pc);
    }

    @Test
    public void equals() {
        PetCertificate pc1 = new PetCertificate("AVA");
        PetCertificate pc2 = new PetCertificate("AVA");
        assertEquals(pc1, pc2);
    }

    @Test
    public void notEquals() {
        PetCertificate pc1 = new PetCertificate("AVA");
        PetCertificate pc2 = new PetCertificate("FDA");
        assertNotEquals(pc1, pc2);
    }

    @Test
    public void toStringTest() {
        PetCertificate pc = new PetCertificate("AVA");
        String pcExpected = " [ AVA ] ";
        assertEquals(pc.toString(), pcExpected);
    }

    @Test
    public void hashCodeTest() {
        PetCertificate pc = new PetCertificate("AVA");
        int pcExpected = "AVA".hashCode();
        assertEquals(pc.hashCode(), pcExpected);
    }
}
