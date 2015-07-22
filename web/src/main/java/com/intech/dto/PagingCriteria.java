package com.intech.dto;

import java.util.Collections;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
public class PagingCriteria {
    private final Integer displayStart;
    private final Integer displaySize;
    private final List<SortField> sortFields;
    private final String echo;
    private final String search;

    public PagingCriteria(Integer displayStart, Integer displaySize, String echo, List<SortField> sortFields, String search)
    {
        this.displayStart = displayStart;
        this.displaySize = displaySize;
        this.echo = echo;
        this.sortFields = sortFields;
        this.search = search;
    }

    public Integer getDisplayStart() {
        return displayStart;
    }

    public Integer getDisplaySize() {
        return displaySize;
    }

    public List<SortField> getSortFields() {
        return Collections.unmodifiableList(sortFields);
    }

    public String getEcho() {
        return echo;
    }

    public String getSearch() {
        return search;
    }
}
