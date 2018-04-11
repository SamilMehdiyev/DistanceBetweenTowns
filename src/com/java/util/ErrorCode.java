package com.java.util;

/**
 * Created by Shamil on 11-Apr-18.
 */
public enum ErrorCode {

    UNIQUENESS_FAILED(1000, "Failed", "Exist duplication in rail-road graphic");

    private int code;
    private String label;
    private String description;

    ErrorCode(int code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Error" +
                "{code=" + code +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' + '}';
    }

}
