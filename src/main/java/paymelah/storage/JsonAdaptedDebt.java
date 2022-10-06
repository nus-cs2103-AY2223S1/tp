package paymelah.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import paymelah.commons.exceptions.IllegalValueException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * Jackson-friendly version of {@link Debt}.
 */
class JsonAdaptedDebt {

    private final String description;
    private final String money;

    /**
     * Constructs a {@code JsonAdaptedDebt} with the given debt details.
     */
    @JsonCreator
    public JsonAdaptedDebt(@JsonProperty("description") String description, @JsonProperty("money") String money) {
        this.description = description;
        this.money = money;
    }

    /**
     * Converts a given {@code Debt} into this class for Jackson use.
     */
    public JsonAdaptedDebt(Debt source) {
        description = source.getDescription().toString();
        money = source.getMoney().toString();
    }

    @JsonValue
    public String getDebtDescription() {
        return description;
    }

    @JsonValue
    public String getDebtMoney() {
        return money;
    }

    /**
     * Converts this Jackson-friendly adapted debt object into the model's {@code Debt} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted debt.
     */
    public Debt toModelType() throws IllegalValueException {
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        if (!Money.isValidMoney(money)) {
            throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
        }
        return new Debt(new Description(description), new Money(money));
    }

}
