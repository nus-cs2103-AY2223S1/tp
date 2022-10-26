package seedu.waddle.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import seedu.waddle.model.item.Day;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Class to fill pdf acroform with itinerary details.
 */
public class PdfFiller {
    public static final int MAX_DISPLAY = 15;
    private Itinerary itinerary;
    private File pdfTemplate;
    private List<PDDocument> pdfList;
    private PDDocument finalPdf;

    /**
     * Constructor for a PdfFiller
     * @param itinerary Itinerary to export.
     * @param pdfTemplate Default template for export.
     * @throws IOException When fail to export itinerary information.
     */
    public PdfFiller(Itinerary itinerary, String pdfTemplate) throws IOException {
        this.itinerary = itinerary;
        File file = new File(pdfTemplate);
        this.pdfTemplate = file;
        this.pdfList = new ArrayList<>();
        this.finalPdf = new PDDocument();
    }

    private void fillField(PdfFieldInfo info, PDAcroForm form, List<PDField> fieldList) throws IOException {
        PDField field = form.getField(info.getName());
        field.setValue(info.getValue());
        fieldList.add(field);
    }

    private void fillForm(List<PdfFieldInfo> infoList, PDAcroForm form, List<PDField> fieldList) throws IOException {
        for (PdfFieldInfo info : infoList) {
            fillField(info, form, fieldList);
        }
    }

    /**
     * Export a day into PDF
     * @param day The day containing items to export.
     * @throws IOException When export fails.
     */
    public void fillDay(Day day) throws IOException {
        PDDocument pdf = PDDocument.load(pdfTemplate);
        PDAcroForm form = pdf.getDocumentCatalog().getAcroForm();
        List<PDField> fieldList = new ArrayList<>();
        form.setXFA(null);
        List<PdfFieldInfo> infoToFill = day.getPdfFieldInfoList();
        infoToFill.add(new PdfFieldInfo("itinerary_name", this.itinerary.getName().description));
        fillForm(infoToFill, form, fieldList);
        form.flatten(fieldList, true);
        pdf.getDocumentCatalog().setAcroForm(form);
        this.pdfList.add(pdf);
    }

    /**
     * Export an itinerary into PDF.
     * @throws IOException When export fails.
     */
    public void fillItinerary() throws IOException {
        for (Day day : this.itinerary.getDays()) {
            fillDay(day);
        }
        for (PDDocument pdf : this.pdfList) {
            PDPage page = pdf.getPage(0);
            this.finalPdf.addPage(page);
        }
        finalPdf.save("./data/" + this.itinerary.getName().description + ".pdf");
        finalPdf.close();
        // only can close when all operations are done
        for (PDDocument pdf : this.pdfList) {
            pdf.close();
        }
    }
}
