BEGIN TRANSACTION;

INSERT INTO daysOfTheWeek (day_id, day_description)
VALUES
    (0, 'SUNDAY'),
    (1, 'MONDAY'),
    (2, 'TUESDAY'),
    (3, 'WEDNESDAY'),
    (4, 'THURSDAY'),
    (5, 'FRIDAY'),
    (6, 'SATURDAY');

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PATIENT');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

-- Sample data for users table
INSERT INTO users (username, password_hash, role) VALUES
('abennett', '$2a$10$A6Wik6Gm/SqTOBeYcz//N.7D2XnP.Y7BhEZrGvCth8N1N.3agn8fC', 'ROLE_DOCTOR'),
('hlewis', '$2a$10$Jb6dCIUn5AF9ltOWApgEs.vuoSVxU5CsCJ.G3NxxBv1KxJUKJttxW', 'ROLE_DOCTOR'),
('nfoster', '$2a$10$TLVPLC4RocgxorYWlv/fPeZjn8MNR7ymP85ydIx0GqPt6TPQE7xne', 'ROLE_DOCTOR'),
('spatel', '$2a$10$ORFp831GFU7QUwyj.lGKDOEhiDOfW/59dpEE.C9zTNrmN7qp0ktTS', 'ROLE_DOCTOR'),
('awilliams', '$2a$10$tCEkXCJbcO1jGxIiuT9okuF8qwWZoB2Og8ZTGgKmkSuDpYjnXvEne', 'ROLE_DOCTOR'),
('lmitchell', '$2a$10$9Q8pzZXcW9xAxW3NeT9KuueUTc4MeEMQKMA4sVuHsj.QnKjwDzlCS', 'ROLE_DOCTOR'),
('gtaylor', '$2a$10$mZdHpqLk81osheu8ldtmdeMLnu6jJuG7NWBVgbWVw060s6AhgMn52', 'ROLE_DOCTOR'),
('ajohnson', '$2a$10$gDagmG1d6GHi8n4xLEdMY.n81z.tnR.izFjPbA.31eseY0bcH1NrW', 'ROLE_PATIENT'),
('bwilliams', '$2a$10$TC6YYi4qlgRkragckBhFd.jR/jT.K9fnLtbUVjKgTkQAkMrmIYRNq', 'ROLE_PATIENT'),
('iwang', '$2a$10$Nk4c0QNq6YLFdJ49R7d4VOMJ87BFr60F1ITJ6NShZASQIRWpLLg/q', 'ROLE_PATIENT'),
('oramirez', '$2a$10$dP8S4PGjq7OEDgK3a5IeNex9N25K0.jv98.jCWT5xBuQayXvcEHSm', 'ROLE_PATIENT');

-- Sample data for doctors table
INSERT INTO doctors (user_id, first_name, last_name, specialty, headshot) VALUES
(3, 'Alexander ', 'Bennett', 'Cardiology', 'https://files.slack.com/files-tmb/T0GNFLF6D-F06ESKZFE3W-aaeadfd9f0/image_480_480.png'),
(4, 'Harper ', 'Lewis', 'Pediatrics', 'https://files.slack.com/files-tmb/T0GNFLF6D-F06E9KXK97H-e95ccf5319/image_720_720.png'),
(5, 'Noah  ', 'Foster', 'Neurology', 'https://files.slack.com/files-tmb/T0GNFLF6D-F06E4CNT8J2-5ae3afc1f3/image_720.png'),
(6, 'Sebastian  ', 'Patel', 'Physical Therapy', 'https://files.slack.com/files-tmb/T0GNFLF6D-F06DWFAECVD-9dce1ce3ee/image_720.png'),
(7, 'Aria  ', 'Williams', 'Nutrition', 'https://files.slack.com/files-tmb/T0GNFLF6D-F06E4CK351U-b2ec5d86ac/image_720.png'),
(8, 'Liam  ', 'Mitchell', 'General Practitioner', 'https://files.slack.com/files-pri/T0GNFLF6D-F06DWF6U7AT/image.png'),
(9, 'Grace  ', 'Taylor', 'OBGYN', 'https://files.slack.com/files-pri/T0GNFLF6D-F06E4CF8LUE/image.png');


-- Sample data for office table
INSERT INTO office (name, address, phone_number, office_hr) VALUES
('TE Wellness Center', '123 Main St, Cityville', '555-1234', '8:00-8:00');


-- Sample data for patients table
INSERT INTO patients (user_id, first_name, last_name, birthdate, phone_number, email) VALUES
(10, 'Alice', 'Johnson', '1990-05-15', '555-1111', 'alice@email.com'),
(11, 'Bob', 'Williams', '1985-08-20', '555-2222', 'bob@email.com'),
(12, 'Isabella', 'Wang', '1995-11-02', '555-3333', 'isabella@email.com'),
(13, 'Oliver', 'Ramirez', '2010-01-01', '555-4444', 'oliver@email.com');

-- Sample data for appointments table
INSERT INTO appointments (doctor_id, patient_id, appointment_start_time, appointment_end_time, notes) VALUES
(1, 2, '2024-02-01T10:00:00', '2024-02-01T11:00:00', 'Cardiology Checkup'),
(2, 4, '2024-02-02T14:30:00', '2024-02-02T15:30:00', 'Pediatrics Consultation'),
(6, 3, '2024-02-02T14:30:00', '2024-02-02T15:30:00', 'Annual Exam'),
(4, 1, '2024-02-02T14:30:00', '2024-02-02T15:30:00', 'Physical Therapy Session');
-- Sample data for prescriptions table
INSERT INTO prescriptions (patient_id, doctor_id, name, cost) VALUES
(1, 2, 'Prednisone', 120),
(2, 1, 'Warfarin', 205),
(3, 6, 'Amoxicillin', 57),
(4, 3, 'Azithromycin', 89);

-- Sample data for reviews table
INSERT INTO reviews (doctor_id, response, rating, review_note) VALUES
(1, 'Thank you for your excellent service!', 5, 'Highly recommended'),
(2, 'Great with kids, very caring.', 4, 'Would visit again');

-- Sample data for availability table
INSERT INTO availability (doctor_id, day_id, start_time, end_time) VALUES
(1, 1, '08:00:00', '17:00:00'),
(2, 3, '09:00:00', '16:00:00');


COMMIT TRANSACTION;