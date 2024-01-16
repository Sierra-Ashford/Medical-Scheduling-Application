BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, office, patients, doctors, appointments, prescriptions, reviews CASCADE;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE doctors (
    doctor_id SERIAL,
    user_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    specialty varchar(50) NOT NULL,
    CONSTRAINT PK_doctors PRIMARY KEY (doctor_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE office (
    office_id SERIAL,
    doctor_id int,
    name varchar(50) NOT NULL UNIQUE,
    address varchar(200) NOT NULL,
    phone_number varchar(50) NOT NULL,
    cost_per_hr int NOT NULL,
    specialty varchar(50),
    office_hr int,
    CONSTRAINT PK_office_id PRIMARY KEY (office_id),
    CONSTRAINT FK_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
CREATE TABLE patients (
    patient_id SERIAL,
    user_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    birthdate date NOT NULL,
    phone_number varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    CONSTRAINT PK_patients PRIMARY KEY (patient_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE appointments (
    appointment_id SERIAL,
    doctor_id int,
    patient_id int,
    appointment_time date NOT NULL,
    notes varchar(50) NOT NULL,
    CONSTRAINT PK_appointment PRIMARY KEY (appointment_id),
    CONSTRAINT FK_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
    CONSTRAINT FK_patient_id FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);
CREATE TABLE prescriptions (
    prescription_id SERIAL,
    patient_id int,
    name varchar(50) NOT NULL,
    cost int NOT NULL,
    CONSTRAINT PK_prescriptions PRIMARY KEY (prescription_id),
    CONSTRAINT FK_patient_id FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);
CREATE TABLE reviews (
    review_id SERIAL,
    office_id int,
    response varchar(200),
    rating int,
    review_note varchar(50),
    CONSTRAINT PK_reviews PRIMARY KEY (review_id),
    CONSTRAINT FK_office_id FOREIGN KEY (office_id) REFERENCES office(office_id)
);

COMMIT TRANSACTION;
