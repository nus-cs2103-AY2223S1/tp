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

    public static final String MESSAGE_DUPLICATE_EXPENDITURE = "Expenditure list contains duplicate entry(s).";
    public static final String MESSAGE_DUPLICATE_INCOME = "Income list contains duplicate entry(s).";

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
     * Converts this PennyWise into the model's {@code PennyWise} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PennyWise toModelType() throws IllegalValueException {
        PennyWise pennyWise = new PennyWise();
        for (JsonAdaptedExpenditure jsonAdaptedExpenditure : expenditures) {
            Entry expenditure = jsonAdaptedExpenditure.toModelType();
            if (pennyWise.hasExpenditure(expenditure)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EXPENDITURE);
            }
            pennyWise.addExpenditure(expenditure);
        }
        for (JsonAdaptedIncome jsonAdaptedIncome : incomes) {
            Entry income = jsonAdaptedIncome.toModelType();
            if (pennyWise.hasIncome(income)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INCOME);
            }
            pennyWise.addIncome(income);
        }
        return pennyWise;
    }

}
