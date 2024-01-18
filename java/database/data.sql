BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PATIENT');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DOCTOR');

-- Insert sample data for users
INSERT INTO users (username, password_hash, role) VALUES
  ('john_doe', 'hashed_password_1', 'patient'),
  ('dr_smith', 'hashed_password_2', 'doctor'),
  ('dr_chow', 'hashed_password_3', 'doctor');

-- Insert sample data for doctors
INSERT INTO doctors (user_id, first_name, last_name, specialty) VALUES
  (2, 'Sarah', 'Smith', 'Cardiologist'),
  (3, 'Admin', 'User', 'General Practitioner');

-- Insert sample data for patients
INSERT INTO patients (user_id, first_name, last_name, birthdate, phone_number, email) VALUES
  (1, 'John', 'Doe', '1990-01-15', '123-456-7890', 'john.doe@example.com');

-- Insert sample data for offices
INSERT INTO office (doctor_id, name, address, phone_number, cost_per_hr, specialty, office_hr) VALUES
  (1, 'HeartCare Clinic', '123 Main St', '987-654-3210', 150, 'Cardiology', 8),
  (2, 'General Health Center', '456 Oak St', '111-222-3333', 120, 'General Medicine', 9);

-- Insert sample data for appointments
INSERT INTO appointments (doctor_id, patient_id, appointment_time, notes) VALUES
  (1, 1, '2024-01-15', 'Regular checkup'),
  (2, 1, '2024-02-01', 'General consultation');

-- Insert sample data for prescriptions
INSERT INTO prescriptions (patient_id, name, cost) VALUES
  (1, 'Aspirin', 10),
  (1, 'Antibiotic', 20);

-- Insert sample data for reviews
INSERT INTO reviews (office_id, response, rating, review_note) VALUES
  (1, 'Thank you for the great service!', 5, 'Highly recommended'),
  (2, 'Good experience overall', 4, 'Friendly staff');

COMMIT TRANSACTION;
