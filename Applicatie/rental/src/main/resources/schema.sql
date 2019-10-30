Drop table if Exists Users;
Drop table if Exists Vehicles;
Drop table if Exists Bookings;
Drop table if Exists BookingsVehicles;

CREATE table Users (
id UUID primary key,
name varchar NOT NULL,
address varchar not null,
city varchar not null
);

CREATE table Vehicles (
id UUID primary key,
licence_plate varchar not null,
vehicle_name varchar not null,
price_per_day decimal(5,2) not null,
mileage int not null
);

create table Bookings (
id UUID primary key,
booking_number varchar not null,
start_date Date not null,
end_date Date not null,
user_id UUID not null,
is_returned bit not null,
total_price decimal(10,2) not null,
FOREIGN KEY(user_id) references Users(id)
);

CREATE table BookingsVehicles (
id UUID primary key,
bookings_id UUID not null,
vehicle_id UUID not null,
FOREIGN KEY(bookings_id) references Bookings(id),
FOREIGN KEY(vehicle_id) references Vehicles(id)
);