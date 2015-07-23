package com.intech.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by popikyardo on 22.07.15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTablesResponse<T> implements java.io.Serializable {
    @JsonProperty("iTotalRecords")
    private int iTotalRecords = 0;
    @JsonProperty("iTotalDisplayRecords")
    private int iTotalDisplayRecords = 0;
    private String sEcho;
    private List<T> aaData = new ArrayList<>();

    public DataTablesResponse(String sEcho) {
        this.sEcho = sEcho;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
        this.iTotalDisplayRecords = aaData.size();
    }
}
