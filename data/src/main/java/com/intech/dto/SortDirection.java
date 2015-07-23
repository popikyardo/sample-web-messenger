package com.intech.dto;

/**
 * Created by popikyardo on 22.07.15.
 */
public enum SortDirection {
    ASC("asc"), DESC("desc");

    private String direction;

    private SortDirection(String direction)
    {
        this.direction = direction;
    }

    public String getDirection()
    {
        return this.direction;
    }

    public static SortDirection valueOfCaseInsensitive(String value)
    {
        String valueUpper = value.toUpperCase();
        SortDirection sd = SortDirection.valueOf(valueUpper);
        return sd;
    }
}
