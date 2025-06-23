# HealthMeet - Spring Boot Application

HealthMeet is a web-based platform designed to streamline doctor-patient interactions, simplify appointment booking, and provide a centralized system for managing health records and schedules.

## 🚀 Features

- 👨‍⚕️ Doctor and Patient Registration & Login
- 📅 Appointment Booking and Scheduling
- 📁 Patient Health Records Management
- 🔒 Secure Authentication with Role-based Access
- 📊 Admin Dashboard for Monitoring and Management

## 🛠️ Tech Stack

- **Backend:** Spring Boot, Spring MVC, Spring Security, Spring Data JPA
- **Database:**  PostgreSQL 
- **Tools:** Maven or Gradle, Git, IntelliJ IDEA / VS Code

## 📂 Project Structure

HealthMeet/
├── src/
│ ├── main/
│ │ ├── java/ # Java source code
│ │ └── resources/ # Application properties, templates, static files
│ └── test/ # Unit and integration tests
├── pom.xml / build.gradle # Project dependencies
└── README.md


## 📡 API Endpoints
### 🔐 Authentication
- `POST /register` – Register a new user
- `POST /registerdoctor` – To register doctors of a Hospital by its admin

## 👤 Profile Management Endpoints
- `POST /profile/update/patient` - To update Patients extra details
- `POST /profile/update/admin` - To update Hospitals details
- `POST /profile/update/doctor` - To update Doctors details
- `PUT /update-password` - To change Passwords

## 🏥 Hospital Search Endpoints
- `GET /hospitals/by-location` - To Search hospitals based on location
- `GET /hospitals/by-speciality`- To Search hospitals based on speciality
- `GET /hospitals/by-location-and-speciality` - To Search hospitals based on location and sepciality

  ## 📅 Appointment Management Endpoints
  - `POST /book` - To book an appointment
  - `GET /getallappointments` - To get all the appointments made by a patient
  - `GET /todayappointments` - To get the appointments recieved by a doctor today
    
  ## 💊 Prescription Management Endpoints
  - `POST /prescriptions/write` - To write the prescription by doctor for the appointment booked by a patient
  - `GET /prescriptions/{appointmentId}` -To get the prescription given by the doctor to particular appointment
  
