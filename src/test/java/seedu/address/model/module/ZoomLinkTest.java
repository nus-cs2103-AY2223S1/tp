package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ZoomLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ZoomLink(null));
    }

    @Test
    public void constructor_invalidZoomLink_throwsIllegalArgumentException() {
        String invalidZoomLink = "";
        assertThrows(IllegalArgumentException.class, () -> new ZoomLink(invalidZoomLink));
    }

    @Test
    public void isValidZoomLink() {
        // null zoom link
        assertThrows(NullPointerException.class, () -> ZoomLink.isValidUrl(null));

        // invalid zoom link
        assertFalse(ZoomLink.isValidUrl("")); // empty string
        assertFalse(ZoomLink.isValidUrl(" ")); // spaces only
        assertFalse(ZoomLink.isValidUrl("htps://www.google.com/")); // Missing t fron https
        assertFalse(ZoomLink.isValidUrl("google.com")); // Missing https
        assertFalse(ZoomLink.isValidUrl("https:/www.google.com/")); // Missing / from //
        assertFalse(ZoomLink.isValidUrl("https://www.googlecom/")); // Missing . between google and com
        assertFalse(ZoomLink.isValidUrl("https//www.google.com/")); // Missing :

        // valid zoom link
        // CS2103T tutorial zoom link
        assertTrue(ZoomLink
                .isValidUrl("https://nus-sg.zoom.us/j/82167158590?pwd=Nkh1bDBIUi9lcXVWL09lRlBHcDltQT09"));
        // CS2101 zoom link
        assertTrue(ZoomLink
                .isValidUrl("https://nus-sg.zoom.us/j/82369972155?pwd=OU12QlBIYmJuK2Jaa1JibU9NVnFrdz09"));
        // CS2100 recitation zoom link
        assertTrue(ZoomLink
                .isValidUrl("https://nus-sg.zoom.us/j/86457148369?pwd=ci9UZ1p4T2lzS3RNV2s5QUU2TWNQdz09"));
        // CS2103T live lecture link
        assertTrue(ZoomLink
                .isValidUrl("https://nus-sg.zoom.us/j/92307270969?pwd=VVMvNWFPWFpyVHRIcXR0VkJlNkg0dz09"));
    }
}
