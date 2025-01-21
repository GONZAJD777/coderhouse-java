package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateTimeRestAPI {

    @JsonProperty("currentDateTime")
    private Date currentDateTime;

    @JsonProperty("utcOffset")
    private String utcOffset;

    @JsonProperty("isDayLightSavingsTime")
    private boolean isDayLightSavingsTime;

    @JsonProperty("dayOfTheWeek")
    private String dayOfTheWeek;

    @JsonProperty("timeZoneName")
    private String timeZoneName;

    @JsonProperty("currentFileTime")
    private long currentFileTime;

    @JsonProperty("ordinalDate")
    private String ordinalDate;

    @JsonProperty("serviceResponse")
    private Object serviceResponse; // Puedes ajustar el tipo según tus necesidades

    public Date getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(Date pCurrentDateTime) {
        currentDateTime = pCurrentDateTime;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String ptcOffset) {
        utcOffset = ptcOffset;
    }

    public boolean isDayLightSavingsTime() {
        return isDayLightSavingsTime;
    }

    public void setDayLightSavingsTime(boolean psDayLightSavingsTime) {
        isDayLightSavingsTime = psDayLightSavingsTime;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String payOfTheWeek) {
        dayOfTheWeek = payOfTheWeek;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String pimeZoneName) {
        timeZoneName = pimeZoneName;
    }

    public long getCurrentFileTime() {
        return currentFileTime;
    }

    public void setCurrentFileTime(long purrentFileTime) {
        currentFileTime = purrentFileTime;
    }

    public String getOrdinalDate() {
        return ordinalDate;
    }

    public void setOrdinalDate(String prdinalDate) {
        ordinalDate = prdinalDate;
    }

    public Object getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(Object perviceResponse) {
        serviceResponse = perviceResponse;
    }

    @Override
    public String toString() {
        return "DateTimeRestAPI{" +
                "currentDateTime=" + currentDateTime +
                ", utcOffset='" + utcOffset + '\'' +
                ", isDayLightSavingsTime=" + isDayLightSavingsTime +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                ", timeZoneName='" + timeZoneName + '\'' +
                ", currentFileTime=" + currentFileTime +
                ", ordinalDate='" + ordinalDate + '\'' +
                ", serviceResponse=" + serviceResponse +
                '}';
    }
}

