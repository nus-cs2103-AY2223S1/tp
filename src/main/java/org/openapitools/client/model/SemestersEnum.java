package org.openapitools.client.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SemestersEnum {
    S1(new BigDecimal("1"), "Semester 1"),

    S2(new BigDecimal("2"), "Semester 2"),

    ST1(new BigDecimal("3"), "Special Term 1"),

    ST2(new BigDecimal("4"), "Special Term 2");

    private final BigDecimal value;
    private final String valueStr;

    SemestersEnum(BigDecimal value, String valueStr) {
        this.value = value;
        this.valueStr = valueStr;
    }

    @JsonCreator
    public static SemestersEnum fromValue(BigDecimal value) {
        for (SemestersEnum b : SemestersEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static SemestersEnum fromValue(String semesterId) {
        for (SemestersEnum b : SemestersEnum.values()) {
            if (b.valueStr.equals(semesterId) || b.name().equals(semesterId)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected semester id string '" + semesterId + "'");
    }

    @JsonValue
    public BigDecimal getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.valueStr;
    }
}
