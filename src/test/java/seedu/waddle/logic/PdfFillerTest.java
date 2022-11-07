package seedu.waddle.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.TypicalItineraries.AUTUMN;
import static seedu.waddle.testutil.TypicalItineraries.SUMMER;
import static seedu.waddle.testutil.TypicalItineraries.WINTER;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import seedu.waddle.commons.core.Text;
import seedu.waddle.model.item.Day;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.storage.JsonUserPrefsStorage;
import seedu.waddle.storage.JsonWaddleStorage;
import seedu.waddle.storage.StorageManager;

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
