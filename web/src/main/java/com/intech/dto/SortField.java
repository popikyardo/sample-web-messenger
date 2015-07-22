package com.intech.dto;

/**
 * Created by popikyardo on 22.07.15.
 */
public class SortField {
    private final String field;
    private final SortDirection direction;

    public SortField(String field, SortDirection direction)
    {
        this.field = field;
        this.direction = direction;
    }

    public SortField(String field, String direction)
    {
        this.field = field;
        this.direction = SortDirection.valueOfCaseInsensitive(direction);
    }

    public String getField() {
        return field;
    }

    public SortDirection getDirection() {
        return direction;
    }

}