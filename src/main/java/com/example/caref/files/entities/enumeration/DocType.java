package com.example.caref.files.entities.enumeration;

public enum DocType {

    PROFILE_IMAGE(0),
    CAR_IMAGE(1),
    GARAGE_IMAGE(2),
    OTHER(3);

    private int value;

    DocType(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public static DocType forValue(int value) {
        for (DocType docType : values()) {
            if (docType.getValue() == value) {
                return docType;
            }
        }
        return null;
    }

    public static DocType forString(String value) {
        for (DocType docType : values()) {
            if (docType.toString().equals(value)) {
                return docType;
            }
        }
        return null;
    }
}

