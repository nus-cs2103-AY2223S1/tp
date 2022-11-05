package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class UrlTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Url(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Url(invalidName));
    }

    @Test
    public void isValidUrl() {
        // null name
        assertThrows(NullPointerException.class, () -> Url.isValidUrl(null));

        //valid url
        assertTrue(Url.isValidUrl("https://google.com"));
        assertTrue(Url.isValidUrl("https://www.google.com"));
        assertTrue(Url.isValidUrl("http://google.com"));
        //google docs
        assertTrue(Url.isValidUrl("https://docs.google.com/" +
                "document/d/1FExr2vMQSwLWZfnikJHDgmnmTRnBAVHyC5K-7XzOOjY/edit"));
        //git hub link
        assertTrue(Url.isValidUrl("https://github.com/AY2223S1-CS2103T-W13-4/tp"));
        //zoom link
        assertTrue(Url.isValidUrl("https://nus-sg.zoom.us/" +
                "j/86758308632?pwd=V2pxTUtHeEpiZC9ZeC83YSsvMWxGZz09"));
        //very long url sharepoint
        assertTrue(Url.isValidUrl(
                "https://nusu-my.sharepoint.com/personal/elcypss_nus_edu_sg"
                        + "/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Felcypss%5"
                        + "Fnus%5Fedu%5Fsg%2FDocuments%2FCS2101%20G05%20Tues%20%"
                        + "26%20Fri%202%2D4pm%202022&ct=1660284191656&or=OWA%2DN"
                        + "T&cid=60025597%2Dbc85%2De63b%2Db36a%2D07b809923650&ga=1"));

        //invalid url
        assertFalse(Url.isValidUrl("google.com")); //no https
        assertFalse(Url.isValidUrl("https://google")); //no .com
    }
}
