package tracko.model.order;

import javafx.util.Pair;

public class ItemQuantityPair extends Pair<String, Integer> {

    public ItemQuantityPair(String item, Integer quantity) {
        super(item, quantity);
    }

    public String getItem() {
        return this.getKey();
    }

    public Integer getQuantity() {
        return this.getValue();
    }
}
