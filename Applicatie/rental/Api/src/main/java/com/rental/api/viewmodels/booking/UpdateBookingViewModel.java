package com.rental.api.viewmodels.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UpdateBookingViewModel {
    //region Private attributes
    @JsonProperty("booking_id")
    private UUID id;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @JsonProperty("vehicle_ids")
    private List<String> vehicleIds = new ArrayList<>();

    //endregion

    //region Constructors
    public UpdateBookingViewModel() {

    }
    //endregion

    //region Getters and setters

    @JsonProperty("booking_id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("booking_id")
    public void setId(UUID id) {
        this.id = id;
    }

    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("vehicle_ids")
    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    @JsonProperty("vehicle_ids")
    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    //endregion
}
