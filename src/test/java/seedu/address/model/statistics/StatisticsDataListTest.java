package seedu.address.model.statistics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatisticsDataListTest {

    public static final String TEST_NAME = "Test";
    public static final String FAIL_TEST_NAME = "Test2";
    public static final Double TEST_VALUE = 1.0;
    public static final Double FAIL_TEST_VALUE = 0.0;

    @Test
    public void isEmpty() {
        StatisticDataList modelDataList = new StatisticDataList();

        //StatisticDataList is empty
        assertTrue(modelDataList.isEmpty());

        modelDataList.addStatistic(new StatisticData(TEST_NAME, TEST_VALUE));

        //StatisticDataList is not empty
        assertFalse(modelDataList.isEmpty());
    }

    @Test
    public void contains() {
        StatisticDataList modelDataList = new StatisticDataList();

        //StatisticDataList does not contain the specified StatisticData
        assertFalse(modelDataList.contains(new StatisticData(TEST_NAME, TEST_VALUE)));

        modelDataList.addStatistic(new StatisticData(TEST_NAME, 1.0));

        ////StatisticDataList contains the specified StatisticData
        assertTrue(modelDataList.contains(new StatisticData(TEST_NAME, TEST_VALUE)));
    }

    @Test
    public void equals() {
        StatisticDataList modelDataList = new StatisticDataList();
        StatisticDataListStub otherDataList = new StatisticDataListStub(FXCollections.observableArrayList());

        //Other StatisticDataList is null
        assertFalse(modelDataList.equals(null));

        //Both StatisticDataLists are empty
        assertTrue(modelDataList.equals(otherDataList));

        //Both StatisticDataLists have same elements
        modelDataList.addStatistic(new StatisticData(TEST_NAME, TEST_VALUE));
        ObservableList<StatisticData> otherData = FXCollections.observableArrayList();
        otherData.add(new StatisticData(TEST_NAME, TEST_VALUE));
        otherDataList = new StatisticDataListStub(otherData);
        assertTrue(modelDataList.equals(otherDataList));

        //StatisticDataLists have StatisticData with same name but different value
        otherData.setAll(new StatisticData(TEST_NAME, TEST_VALUE + TEST_VALUE));
        otherDataList = new StatisticDataListStub(otherData);
        assertFalse(modelDataList.equals(otherDataList));

        //StatisticDataLists have StatisticData with same value but different name
        otherData.setAll(new StatisticData(FAIL_TEST_NAME, TEST_VALUE));
        otherDataList = new StatisticDataListStub(otherData);
        assertFalse(modelDataList.equals(otherDataList));

        //other StatisticDataList has 1 same StatisticData but also an extra StatisticData
        otherData.setAll(new StatisticData(TEST_NAME, TEST_VALUE), new StatisticData(FAIL_TEST_NAME, TEST_VALUE));
        otherDataList = new StatisticDataListStub(otherData);
        assertFalse(modelDataList.equals(otherDataList));
    }

    @Test
    public void addStatistic() {
        ObservableList<StatisticData> expectedData = FXCollections.observableArrayList();
        expectedData.add(new StatisticData(TEST_NAME, TEST_VALUE));
        StatisticDataListStub expectedDataList = new StatisticDataListStub(expectedData);
        StatisticDataList modelDataList = new StatisticDataList();
        modelDataList.addStatistic(new StatisticData(TEST_NAME, TEST_VALUE));

        //Ensure addStatistic works as intended
        assertTrue(modelDataList.equals(expectedDataList));
    }

    @Test
    public void addToStatistic() {
        ObservableList<StatisticData> expectedData = FXCollections.observableArrayList();
        expectedData.add(new StatisticData(TEST_NAME, TEST_VALUE));
        StatisticDataListStub expectedDataList = new StatisticDataListStub(expectedData);
        StatisticDataList modelDataList = new StatisticDataList();
        modelDataList.addStatistic(new StatisticData(TEST_NAME, FAIL_TEST_VALUE));

        //values are different
        assertFalse(modelDataList.equals(expectedDataList));

        //after increment with addToStatistics(), values are same
        modelDataList.addToStatistic(TEST_NAME);
        assertTrue(modelDataList.equals(expectedDataList));
    }

    private class StatisticDataListStub extends StatisticDataList {

        StatisticDataListStub(ObservableList<StatisticData> dataList) {
            super();
            super.chartDataList.setAll(dataList);
        }
    }
}
