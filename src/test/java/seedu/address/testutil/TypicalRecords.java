package seedu.address.testutil;

import seedu.address.model.record.Record;

/**
 * A utility class containing a list of {@code Record} objects to be used in tests.
 */
public class TypicalRecords {
    public static final Record RECORD1 = new RecordBuilder().withRecordDate("10-10-2011 1400")
            .withRecordData("Covid-19")
            .withMedications("Paracetamol 500mg").build();
    public static final Record RECORD2 = new RecordBuilder().withRecordDate("10-10-2010 1400")
            .withRecordData("Cold").build();
    public static final Record RECORD3 = new RecordBuilder().withRecordDate("10-11-2011 1410")
            .withRecordData("Rashes (Allergic Reaction)")
            .withMedications("Cetrizine 10 tabs", " Ketotifen 1 botl")
            .build();
}
