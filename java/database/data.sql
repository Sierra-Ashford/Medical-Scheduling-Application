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
(3, 'Alexander ', 'Bennett', 'Cardiology', '/Images/image_480.png'),
(4, 'Harper ', 'Lewis', 'Pediatrics', '/Images/image_720.png'),
(5, 'Noah  ', 'Foster', 'Neurology', '/Images/image_750.png'),
(6, 'Sebastian  ', 'Patel', 'Physical Therapy', '/Images/image_710.png'),
(7, 'Aria  ', 'Williams', 'Nutrition', '/Images/image_730.png'),
(8, 'Liam  ', 'Mitchell', 'General Practitioner', '/Images/image_740.png'),
(9, 'Grace  ', 'Taylor', 'OBGYN', '/Images/image_700.png');

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
(1, 1, '2024-02-05T10:00:00', '2024-02-05T10:30:00', 'Cardiology Checkup'),
(1, 4, '2024-02-05T14:30:00', '2024-02-05T15:30:00', 'Annual Exam'),
(1, 3, '2024-02-05T15:30:00', '2024-02-05T16:00:00', 'Cardiology Checkup'),
(1, 3, '2024-02-06T14:30:00', '2024-02-06T15:30:00', 'Cardiology Checkup'),
(4, 1, '2024-02-02T14:30:00', '2024-02-02T15:30:00', 'Physical Therapy Session');
-- Sample data for prescriptions table
INSERT INTO prescriptions (patient_id, doctor_id, name, cost) VALUES
(1, 1, 'Prednisone', 120),
(2, 1, 'Warfarin', 205),
(3, 1, 'Amoxicillin', 57),
(1, 1, 'Atorvastatin', 310),
(4, 2, 'Warfarin', 205),
(2, 6, 'Amoxicillin', 57),
(4, 3, 'Azithromycin', 89);

-- Sample data for reviews table
INSERT INTO reviews (doctor_id, response, rating, review_note) VALUES
(1, 'Great service', 4, 'First time seeing Dr. Bennett, but already a fan! Super thorough and answered all my questions.'),
(1, 'Highly recommended', 5, 'Thank you for your excellent service!'),
(1, 'Awesome', 4, 'Dr. Bennett and his expertise in cardiology is unmatched. His detailed approach to my complex condition gave me confidence in my treatment plan.'),
(2, 'Would visit again', 4, 'Great with kids, very caring.'),
(2, 'Wonderful', 5, 'My kids are actually excited to visit the doctor.  Dr. Lewis is caring, attentive, and incredibly skilled.'),
(3, 'On the fence', 2, 'Dr. Foster seemed competent and professional but I felt a bit rushed during the appointment and would have liked more time to discuss my concerns.'),
(3, 'Very thorough ', 3, 'He was quick in diagnosing my migraines. His treatment plan has reduced my headaches significantly.'),
(4, 'Attentive', 3, 'Dr, Patel is very thorough and I trust him completely with my care.'),
(5, 'Great service', 5, 'Dr. Williams is incredibly knowledgeable and always takes the time to explain things in an understandable way.'),
(6, 'Not impressed', 1, 'Im not impressed with Dr. Mitchell. He sometimes seems a bit dismissive of my concerns.'),
(7, 'Highly recommend', 5, 'Thorough and takes the time to answer all of my questions and concerns.');

-- Sample data for availability table
INSERT INTO availability (doctor_id, day_id, start_time, end_time) VALUES
(1, 1, '10:00:00', '11:00:00'),
(1, 1, '12:00:00', '17:00:00'),
(1, 2, '07:30:00', '12:00:00'),
(1, 2, '13:00:00', '18:00:00'),
(1, 3, '09:00:00', '17:00:00'),
(1, 4, '09:00:00', '19:00:00'),
(1, 5, '12:00:00', '16:00:00'),
(2, 6, '09:00:00', '16:00:00');


COMMIT TRANSACTION;