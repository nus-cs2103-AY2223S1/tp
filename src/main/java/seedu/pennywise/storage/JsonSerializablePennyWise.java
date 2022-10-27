package seedu.pennywise.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.pennywise.commons.exceptions.IllegalValueException;
import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.entry.Entry;

/**
 * An Immutable PennyWise that is serializable to JSON format.
 */
@JsonRootName(value = "pennywise")
class JsonSerializablePennyWise {

    // todo modify message duplicates for  both income/expenditure.
    public static final String MESSAGE_DUPLICATE_ENTRY = "Entries list contains duplicate entry(s).";

    private final List<JsonAdaptedExpenditure> expenditures = new ArrayList<>();
    private final List<JsonAdaptedIncome> incomes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePennyWise} with the given entries.
     */
    @JsonCreator
    public JsonSerializablePennyWise(@JsonProperty("expenditures") List<JsonAdaptedExpenditure> expenditures,
                                     @JsonProperty("incomes") List<JsonAdaptedIncome> incomes) {
        this.expenditures.addAll(expenditures);
        this.incomes.addAll(incomes);
    }

    /**
     * Converts a given {@code ReadOnlyPennyWise} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePennyWise}.
     */
    public JsonSerializablePennyWise(ReadOnlyPennyWise source) {
        expenditures.addAll(
                source.getExpenditureList().stream().map(JsonAdaptedExpenditure::new).collect(Collectors.toList()));
        incomes.addAll(source.getIncomeList().stream().map(JsonAdaptedIncome::new).collect(Collectors.toList()));
    }

    /**
     * Converts this pennywise book into the model's {@code PennyWise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PennyWise toModelType() throws IllegalValueException {
        PennyWise pennyWise = new PennyWise();
        for (JsonAdaptedExpenditure jsonAdaptedExpenditure : expenditures) {
            Entry e = jsonAdaptedExpenditure.toModelType();
            if (pennyWise.hasExpenditure(e)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENTRY);
            }
            pennyWise.addExpenditure(e);
        }
        for (JsonAdaptedIncome jsonAdaptedIncome : incomes) {
            Entry e = jsonAdaptedIncome.toModelType();
            if (pennyWise.hasIncome(e)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENTRY);
            }
            pennyWise.addIncome(e);
        }
        return pennyWise;
    }

}
