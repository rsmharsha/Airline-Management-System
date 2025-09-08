# Flight Reservation Mini Project

A compact Java-based flight reservation system that demonstrates core functionality of a booking application: user authentication, flight search, passenger management, booking, boarding pass generation and cancellation.

## What it does

- Allows users to log in and access the application.
- Manages customer records (add and view customer details).
- Displays available flight information and journey details.
- Creates and stores flight bookings, and generates boarding passes.
- Supports booking cancellation and related operations.
- Uses a database connection layer to persist and retrieve data.

## Files and their roles

- **`Login.java`** — Handles user authentication, login form, and access validation.
- **`Home.java`** — Provides the main application dashboard with navigation to other features.
- **`AddCustomer.java`** — Manages passenger/customer details including adding new records.
- **`FlightInfo.java`** — Displays available flights, their schedules, and related metadata.
- **`JourneyDetails.java`** — Collects trip-specific details like source, destination, and date.
- **`BookFlight.java`** — Implements the booking process and creates a reservation entry.
- **`BoardingPass.java`** — Generates and displays a digital boarding pass after booking.
- **`Cancel.java`** — Enables cancellation of existing bookings.
- **`DBConnection.java`** — Provides database connection utilities and manages persistence.

## Project purpose

This project is intended as an educational, demonstrative application to showcase typical operations found in small-scale reservation systems: user sessions, CRUD for customers and bookings, persistence via a DB connection, and simple business flows like booking and cancellation.

---
