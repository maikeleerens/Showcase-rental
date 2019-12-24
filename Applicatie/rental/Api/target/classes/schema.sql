Drop table if Exists Companies;
Drop table if Exists Users;
Drop table if Exists Vehicles;
Drop table if Exists Bookings;
Drop table if Exists Bookings_Vehicles;
Drop table if Exists User_Notifications;
Drop table if Exists Company_Notifications;
Drop table if Exists Roles;
Drop table if Exists Users_Roles;

CREATE table Companies (
id UUID primary key,
name varchar not null,
address varchar not null
);

CREATE table Users (
id UUID primary key,
email varchar NOT NULL UNIQUE,
password varchar NOT NULL,
name varchar NOT NULL,
address varchar not null,
city varchar not null
);

CREATE table Roles (
id UUID primary key,
name varchar NOT NULL UNIQUE
);

CREATE table Vehicles (
id UUID primary key,
licence_plate varchar not null unique,
vehicle_name varchar not null,
price_per_day decimal(5,2) not null,
mileage int not null
);

CREATE table Bookings (
id UUID primary key,
booking_number varchar not null unique,
start_date Date not null,
end_date Date not null,
user_id UUID not null,
company_id UUID not null,
returned bit not null,
total_price decimal(10,2) not null,
FOREIGN KEY(user_id) references Users(id),
FOREIGN KEY(company_id) references Companies(id)
);

CREATE table Bookings_Vehicles (
id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
booking_id UUID,
vehicles_id UUID,
FOREIGN KEY(booking_id) REFERENCES Bookings(id),
FOREIGN KEY(vehicles_id) REFERENCES Vehicles(id)
);

CREATE table Users_Roles (
id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
user_id UUID,
roles_id UUID,
FOREIGN KEY(user_id) REFERENCES Users(id),
FOREIGN KEY(roles_id) REFERENCES Roles(id)
);

CREATE table User_Notifications (
id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
user_id UUID,
notification varchar,
FOREIGN KEY(user_id) references Users(id)
);

CREATE table Company_Notifications (
id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
company_id UUID,
notification varchar,
FOREIGN KEY(company_id) references Companies(id)
);

