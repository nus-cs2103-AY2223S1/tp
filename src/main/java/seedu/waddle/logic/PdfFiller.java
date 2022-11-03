package seedu.waddle.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.SystemUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import seedu.waddle.model.item.Day;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.UniqueItemList;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.commons.core.Text;

/**
 * Class to fill pdf acroform with itinerary details.
 */
public class PdfFiller {
    public static final int MAX_DISPLAY = 15;
    private final Itinerary itinerary;
    private final String pdfTemplate;
    private final List<PDDocument> pdfList;
    private final PDDocument finalPdf;


    /**
     * Constructor for a PdfFiller
     *
     * @param itinerary   Itinerary to export.
     * @param pdfTemplate Default template for export.
     * @throws IOException When fail to export itinerary information.
     */
    public PdfFiller(Itinerary itinerary, String pdfTemplate) throws IOException {
        this.itinerary = itinerary;
        this.pdfTemplate = pdfTemplate;
        this.pdfList = new ArrayList<>();
        this.finalPdf = new PDDocument();
    }

    private void fillField(PdfFieldInfo info, PDAcroForm form, List<PDField> fieldList) throws IOException {
        PDField field = form.getField(info.getName());
        field.setValue(info.getValue());
        fieldList.add(field);
    }

    private void fillForm(Day day, List<PdfFieldInfo> infoList) throws IOException {

        InputStream exportTemplate = getClass().getResourceAsStream(pdfTemplate);
        PDDocument pdf = PDDocument.load(exportTemplate);
        PDAcroForm form = pdf.getDocumentCatalog().getAcroForm();
        List<PDField> fieldList = new ArrayList<>();
        ArrayList<PdfFieldInfo> infoToFill = new ArrayList<>(infoList);
        form.setXFA(null);
        infoToFill.add(new PdfFieldInfo("itinerary_name", this.itinerary.getDescriptionString(Text.INDENT_NONE)));
        infoToFill.add(new PdfFieldInfo("day", "Day " + (day.getDayNumber() + 1)));

        for (PdfFieldInfo info : infoToFill) {
            fillField(info, form, fieldList);
        }

        form.flatten(fieldList, true);
        pdf.getDocumentCatalog().setAcroForm(form);
        this.pdfList.add(pdf);
    }

    /**
     * Export a day into PDF
     *
     * @param day The day containing items to export.
     * @throws IOException When export fails.
     */
    public void fillDay(Day day) throws IOException {
        UniqueItemList itemList = day.getItemList();
        int itemListSize = itemList.getSize();
        int numOfPages = (int) Math.ceil((double) itemListSize / MAX_DISPLAY);
        if (numOfPages == 0) {
            List<PdfFieldInfo> fieldList = new ArrayList<>();
            for (int i = 0; i < MAX_DISPLAY; i++) {
                PdfFieldInfo time = new PdfFieldInfo("time" + i, "");
                PdfFieldInfo activity = new PdfFieldInfo("item" + i, "");
                fieldList.add(time);
                fieldList.add(activity);
            }
            fillForm(day, fieldList);
        }
        for (int i = 0; i < numOfPages; i++) {
            List<PdfFieldInfo> fieldList = new ArrayList<>();
            for (int j = 0; j < MAX_DISPLAY; j++) {
                int targetIndex = i * MAX_DISPLAY + j;
                if (targetIndex < itemListSize) {
                    Item item = itemList.get(targetIndex);
                    PdfFieldInfo time = new PdfFieldInfo("time" + j,
                            item.getTimeString(Text.INDENT_NONE).replace("Time: ", ""));
                    PdfFieldInfo activity = new PdfFieldInfo("item" + j, item.getDescription().toString());
                    fieldList.add(time);
                    fieldList.add(activity);
                } else {
                    PdfFieldInfo time = new PdfFieldInfo("time" + j, "");
                    PdfFieldInfo activity = new PdfFieldInfo("item" + j, "");
                    fieldList.add(time);
                    fieldList.add(activity);
                }
            }
            fillForm(day, fieldList);
        }
    }

    /**
     * Export an itinerary into PDF.
     *
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

        // create a waddle directory and get the path
        String defaultPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        File waddleFolder;
        if (SystemUtils.IS_OS_MAC) {
            waddleFolder = new File(defaultPath + "/Documents/Waddle");

        } else {
            waddleFolder = new File(defaultPath + "/Waddle");
        }
        if (!waddleFolder.exists()) {
            waddleFolder.mkdirs();
        }

        finalPdf.save(waddleFolder + "/" + this.itinerary.getDescriptionString(Text.INDENT_NONE) + ".pdf");
        finalPdf.close();

        // only can close when all operations are done
        for (PDDocument pdf : this.pdfList) {
            pdf.close();
        }
    }
}
