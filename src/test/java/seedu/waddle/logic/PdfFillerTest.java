package seedu.waddle.logic;

import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PdfFillerTest {

    private String pdfTemplate = "/template/waddle_template.pdf";
    private PdfFiller pdfFiller;

    @BeforeEach
    public void setUp() {
        try {
            pdfFiller = new PdfFiller(WINTER, pdfTemplate);
        } catch (IOException e) {
            System.out.println("Failed to create PdfFiller");
        }
    }
    @Test
    public void constructor_success() {
        try {
            PdfFiller pdfFiller = new PdfFiller(WINTER, pdfTemplate);
        } catch (IOException e) {
            assert false : "Fail to load pdfTemplate";
        }
        assert true : "Load pdfTemplate successfully";
    }

    @Test
    public void fillItinerary_success() {
        try {
            pdfFiller.fillItinerary();
        } catch (IOException e) {
            assert false : "Fail to fill itinerary";
        }
        assert true : "Filled itinerary successfully";
    }
}
