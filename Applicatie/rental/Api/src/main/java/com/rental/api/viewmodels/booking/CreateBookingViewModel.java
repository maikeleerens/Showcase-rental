package com.rental.api.viewmodels.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateBookingViewModel {

    //region Private attributes
    @JsonProperty("booking_number")
    private String bookingNumber;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @JsonProperty("vehicle_ids")
    private List<String> vehicleIds = new ArrayList<>();

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("company_id")
    private String companyId;
    //endregion

    //region Constructors
    public CreateBookingViewModel() {

    }
    //endregion

    //region Getters and setters
    @JsonProperty("booking_number")
    public String getBookingNumber() {
        return bookingNumber;
    }

    @JsonProperty("booking_number")
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("company_id")
    public String getCompanyId() {
        return companyId;
    }

    @JsonProperty("company_id")
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    //endregion
}
