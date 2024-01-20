BEGIN TRANSACTION;

INSERT INTO daysOfTheWeek (day_id, day_description)
VALUES
    (1, 'SUNDAY'),
    (2, 'MONDAY'),
    (3, 'TUESDAY'),
    (4, 'WEDNESDAY'),
    (5, 'THURSDAY'),
    (6, 'FRIDAY'),
    (7, 'SATURDAY');

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PATIENT');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

-- Sample data for users table
INSERT INTO users (username, password_hash, role) VALUES
('user1', 'hashed_password_1', 'ROLE_PATIENT'),
('user2', 'hashed_password_2', 'ROLE_PATIENT'),
('user3', 'hashed_password_3', 'ROLE_DOCTOR');

-- Sample data for doctors table
INSERT INTO doctors (user_id, first_name, last_name, specialty, headshot) VALUES
(3, 'John', 'Doe', 'Cardiology', 'https://imgur.com/0PpiqE8'),
(3, 'Jane', 'Smith', 'Pediatrics', 'https://imgur.com/0PpiqE8');

-- Sample data for office table
INSERT INTO office (name, address, phone_number, cost_per_hr, office_hr) VALUES
('Cardiology Clinic', '123 Main St, Cityville', '555-1234', 150, 8),
('Pediatrics Center', '456 Oak St, Townsville', '555-5678', 120, 9);

-- Sample data for patients table
INSERT INTO patients (user_id, first_name, last_name, birthdate, phone_number, email) VALUES
(1, 'Alice', 'Johnson', '1990-05-15', '555-1111', 'alice@email.com'),
(2, 'Bob', 'Williams', '1985-08-20', '555-2222', 'bob@email.com');

-- Sample data for appointments table
INSERT INTO appointments (doctor_id, patient_id, appointment_start_time, appointment_end_time, notes) VALUES
(1, 1, '2024-02-01T10:00:00', '2024-02-01T11:00:00', 'Cardiology Checkup'),
(2, 2, '2024-02-02T14:30:00', '2024-02-02T15:30:00', 'Pediatrics Consultation');

-- Sample data for prescriptions table
INSERT INTO prescriptions (patient_id, doctor_id, name, cost) VALUES
(1, 2, 'Medicine A', 30),
(2, 1, 'Medicine B', 25);

-- Sample data for reviews table
INSERT INTO reviews (doctor_id, response, rating, review_note) VALUES
(1, 'Thank you for your excellent service!', 5, 'Highly recommended'),
(2, 'Great with kids, very caring.', 4, 'Would visit again');

-- Sample data for availability table
INSERT INTO availability (doctor_id, day_id, start_time, end_time) VALUES
(1, 1, '08:00:00', '17:00:00'),
(2, 3, '09:00:00', '16:00:00');


COMMIT TRANSACTION;
