Drop table if Exists Users;
Drop table if Exists Vehicles;
Drop table if Exists Bookings;
Drop table if Exists BookingsVehicles;

CREATE table Users (
Id varchar primary key,
Name varchar NOT NULL,
Address varchar not null,
City varchar not null
);

create table Vehicles (
Id varchar primary key,
LicencePlate varchar not null,
VehicleName varchar not null,
PricePerDay decimal(5,2) not null,
Mileage int not null
);

create table Bookings (
Id varchar primary key,
BookingNumber varchar not null,
StartDate Date not null,
EndDate Date not null,
UserId varchar not null,
IsReturned bit not null,
TotalPrice decimal(10,2) not null,
FOREIGN KEY(UserId) references Users(Id)
);

create table BookingsVehicles (
Id varchar primary key,
BookingsId varchar not null,
VehicleId varchar not null,
FOREIGN KEY(BookingsId) references Bookings(Id),
FOREIGN KEY(VehicleId) references Vehicles(Id)
);