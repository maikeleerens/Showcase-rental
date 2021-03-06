INSERT INTO COMPANIES VALUES ( 'a05b2e72-a95e-4044-95b4-a7a3bbe5a95c', 'CarRental', 'Teststraat 12' );

INSERT INTO ROLES VALUES ('a0c03879-5af3-488a-88df-54f29a6d0b8d', 'ADMIN');
INSERT INTO ROLES VALUES ('3e93b6b7-bb8e-41b5-872c-849e68770b02', 'EMPLOYEE');
INSERT INTO ROLES VALUES ('f9262185-2dba-416b-ba2e-93a790a2d5c4', 'CUSTOMER');

INSERT INTO USERS VALUES ('51069215-566f-41b5-a281-0d5c18e1fcc8', 'maikel.eerens@gmail.com', '$2a$10$lk5fTyCawQcVTxEO3l8k4OI3qAGhuQf8L/lAo1FaEBMDlacgNCBx.', 'Maikel Eerens', 'Koning Leopoldlaan 54B', 'Lommel');
INSERT INTO USERS VALUES ('8ba2f9eb-104f-4bb3-ad10-62f7024b6163', 'JanKlaas@test.com', '$2a$10$lk5fTyCawQcVTxEO3l8k4OI3qAGhuQf8L/lAo1FaEBMDlacgNCBx.', 'Jan Klaas', 'JanKlaasstraat 69', 'ZekerNietBoxtel');
INSERT INTO USERS VALUES ('55162f80-ad51-405a-a081-eddcd1738dad', 'Henk@testcustomer.com', '$2a$10$lk5fTyCawQcVTxEO3l8k4OI3qAGhuQf8L/lAo1FaEBMDlacgNCBx.', 'Henk', 'Teststraat 69', 'TestGat');

INSERT INTO USERS_ROLES VALUES ('a808ad00-e73a-480c-bd82-8f3fbdc0d61b', '51069215-566f-41b5-a281-0d5c18e1fcc8', 'a0c03879-5af3-488a-88df-54f29a6d0b8d');
INSERT INTO USERS_ROLES VALUES ('d9a14748-6d8b-4d85-a7e4-51476861941d', '8ba2f9eb-104f-4bb3-ad10-62f7024b6163', '3e93b6b7-bb8e-41b5-872c-849e68770b02');
INSERT INTO USERS_ROLES VALUES ('635e3193-4581-4e53-8198-7bc3f2bb1f10', '55162f80-ad51-405a-a081-eddcd1738dad', 'f9262185-2dba-416b-ba2e-93a790a2d5c4');

INSERT INTO VEHICLES VALUES ( 'f0d66c7c-8ed3-44ff-aad6-0a747d0bf2e1', '36-RN-GF', 'Fiat Stilo 2.4', 25.00, 170000 );
INSERT INTO VEHICLES VALUES ( '820960f9-e0fa-4d9a-ac32-affcd9dc3733', '05-GG-RH', 'Ford Fiesta 1.2', 18.00, 65000 );
INSERT INTO VEHICLES VALUES ( 'ac08f29b-dffc-4409-912e-554bde55c734', '02-LOL-2', 'Mercedes A45', 30.00, 12000 );

INSERT INTO BOOKINGS VALUES ('2674572c-d30e-4431-99a0-826d9e1d278a', '12345', '2019-10-25', '2019-10-30', '51069215-566f-41b5-a281-0d5c18e1fcc8', 'a05b2e72-a95e-4044-95b4-a7a3bbe5a95c', true, 150.00);
INSERT INTO BOOKINGS VALUES ('f6f0c9f0-3c49-44ac-b08b-95c93611064b', '93957', '2019-11-05', '2019-11-10', '8ba2f9eb-104f-4bb3-ad10-62f7024b6163', 'a05b2e72-a95e-4044-95b4-a7a3bbe5a95c', false, 90.00);
INSERT INTO BOOKINGS VALUES ('ab9c2113-6408-4621-b90b-4dbe561e46d2', '34325', '2019-11-09', '2019-11-10', '55162f80-ad51-405a-a081-eddcd1738dad', 'a05b2e72-a95e-4044-95b4-a7a3bbe5a95c', false, 43.00);
INSERT INTO BOOKINGS VALUES ('1bd3a284-163e-4e7c-a9d9-3cec2bab3ab1', '46377', '2019-11-11', '2019-11-20', '55162f80-ad51-405a-a081-eddcd1738dad', 'a05b2e72-a95e-4044-95b4-a7a3bbe5a95c', false, 162.00);

INSERT INTO BOOKINGS_VEHICLES VALUES ('4a439eec-058e-4a1e-972e-1c972e242338', '2674572c-d30e-4431-99a0-826d9e1d278a', 'ac08f29b-dffc-4409-912e-554bde55c734');
INSERT INTO BOOKINGS_VEHICLES VALUES ('96882f86-7497-4b2f-86cd-766e3c93eb2f', 'f6f0c9f0-3c49-44ac-b08b-95c93611064b', '820960f9-e0fa-4d9a-ac32-affcd9dc3733');
INSERT INTO BOOKINGS_VEHICLES VALUES ('359d1a72-92b4-4508-b843-e077ab7e5e5f', 'ab9c2113-6408-4621-b90b-4dbe561e46d2', '820960f9-e0fa-4d9a-ac32-affcd9dc3733');
INSERT INTO BOOKINGS_VEHICLES VALUES ('4c3e6080-71cd-4ff6-9f5d-3315b269cf48', 'ab9c2113-6408-4621-b90b-4dbe561e46d2', 'f0d66c7c-8ed3-44ff-aad6-0a747d0bf2e1');
INSERT INTO BOOKINGS_VEHICLES VALUES ('0b34d970-29a9-4a17-9e97-fd4e971143df', '1bd3a284-163e-4e7c-a9d9-3cec2bab3ab1', '820960f9-e0fa-4d9a-ac32-affcd9dc3733');