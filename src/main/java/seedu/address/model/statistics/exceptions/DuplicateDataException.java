package seedu.address.model.statistics.exceptions;

/**
 * Signals that the operation will result in duplicate StatisticData
 * (StatisticData are considered duplicates if they have the same name).
 */
public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException() {
        super("Operation would result in duplicate data");
    }
}
