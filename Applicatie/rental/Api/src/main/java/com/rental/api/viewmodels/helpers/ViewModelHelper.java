package com.rental.api.viewmodels.helpers;

import com.rental.api.viewmodels.booking.BookingViewModel;
import com.rental.api.viewmodels.booking.CreateBookingViewModel;
import com.rental.api.viewmodels.booking.UpdateBookingViewModel;
import com.rental.api.viewmodels.company.CompanyViewModel;
import com.rental.api.viewmodels.company.CreateCompanyViewModel;
import com.rental.api.viewmodels.user.CreateUserViewModel;
import com.rental.api.viewmodels.user.UserViewModel;
import com.rental.api.viewmodels.vehicle.CreateVehicleViewModel;
import com.rental.api.viewmodels.vehicle.VehicleViewModel;
import com.rental.domain.interfaces.entities.Booking;
import com.rental.domain.interfaces.entities.Company;
import com.rental.domain.interfaces.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ViewModelHelper {
    public static List<CompanyViewModel> toCompanyViewModels(List<? extends Company> companies) {
        List<CompanyViewModel> returnCompanyList = new ArrayList<>();

        for (var company:
                companies) {
            returnCompanyList.add(new CompanyViewModel(company));
        }
        return returnCompanyList;
    }

    public static List<VehicleViewModel> toVehicleViewModels(List<? extends Vehicle> vehicles) {
        List<VehicleViewModel> returnVehicleList = new ArrayList<>();

        for (var vehicle:
             vehicles) {
            returnVehicleList.add(new VehicleViewModel(vehicle));
        }
        return returnVehicleList;
    }

    public static List<BookingViewModel> toBookingViewModels(List<? extends Booking> bookings) {
        List<BookingViewModel> returnBookingList = new ArrayList<>();

        for (var booking:
             bookings) {
            returnBookingList.add(new BookingViewModel(booking));
        }
        return returnBookingList;
    }

    public static VehicleViewModel toVehicleViewModel(CreateVehicleViewModel model) {
        return new VehicleViewModel(model.getLicencePlate(), model.getVehicleName(), model.getPricePerDay(), model.getMileage());
    }

    public static CompanyViewModel toCompanyViewModel(CreateCompanyViewModel model) {
        return new CompanyViewModel(model.getName(), model.getAddress());
    }

//    public static UserViewModel toUserViewModel(CreateUserViewModel model) {
//        return new UserViewModel(model.getName(), model.getAddress(), model.getCity());
//    }

    public static BookingViewModel toBookingViewModel(CreateBookingViewModel model) {
        List<VehicleViewModel> vehicleList = new ArrayList<>();
        for (var vehicle:
                model.getVehicleIds()) {
            vehicleList.add(new VehicleViewModel(UUID.fromString(vehicle)));
        }
        return new BookingViewModel(model.getBookingNumber(), model.getStartDate(),
                model.getEndDate(), vehicleList, new UserViewModel(UUID.fromString(model.getUserId())),
                new CompanyViewModel(UUID.fromString(model.getCompanyId())));
    }

    public static BookingViewModel toBookingViewModel(UpdateBookingViewModel model) {
        List<VehicleViewModel> vehicleList = new ArrayList<>();
        for (var vehicle:
                model.getVehicleIds()) {
            vehicleList.add(new VehicleViewModel(UUID.fromString(vehicle)));
        }
        return new BookingViewModel(model.getId(), model.getEndDate(), vehicleList);
    }
}
