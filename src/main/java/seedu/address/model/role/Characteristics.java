package seedu.address.model.role;

public class Characteristics {
    String[] characteristicsArr;
    
    public Characteristics(String[] characteristics) {
        this.characteristicsArr = characteristics;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < characteristicsArr.length; i++) {
            result += characteristicsArr[i];
        }
        return result;
    }
}
