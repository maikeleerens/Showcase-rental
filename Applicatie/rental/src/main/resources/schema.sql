Drop table if Exists Users;
Drop table if Exists Vehicles;
Drop table if Exists Bookings;
Drop table if Exists BookingsVehicles;

CREATE table Users (
Id UUID primary key,
Name varchar NOT NULL,
Address varchar not null,
City varchar not null
);

create table Vehicles (
id UUID primary key,
licence_plate varchar not null,
vehicle_name varchar not null,
price_per_day decimal(5,2) not null,
mileage int not null
);

create table Bookings (
Id UUID primary key,
BookingNumber varchar not null,
StartDate Date not null,
EndDate Date not null,
UserId varchar not null,
IsReturned bit not null,
TotalPrice decimal(10,2) not null,
FOREIGN KEY(UserId) references Users(Id)
);

create table BookingsVehicles (
Id UUID primary key,
BookingsId varchar not null,
VehicleId varchar not null,
FOREIGN KEY(BookingsId) references Bookings(Id),
FOREIGN KEY(VehicleId) references Vehicles(Id)
);