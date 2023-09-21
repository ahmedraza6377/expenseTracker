# Expense Tracker API

The Expense Tracker API is a Java Spring Boot application built on the MVC architecture, designed to help users efficiently manage their expenses. This API uses MySQL as the database and Hibernate JPA for data persistence. It is hosted on an AWS EC2 instance for scalability and accessibility.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Clone the Repository](#clone-the-repository)
  - [Database Configuration](#database-configuration)
  - [AWS EC2 Setup](#aws-ec2-setup)
  - [Build and Run](#build-and-run)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

### User Authentication

- **User Registration**: Allow users to create accounts to track their expenses.
- **User Login and Logout**: Provide secure authentication mechanisms for users.

### Expense Management

- **Create Expenses**: Enable users to add new expenses with details like date, amount, and description.
- **Update Expenses**: Allow users to edit and modify existing expense records.
- **Delete Expenses**: Provide the option to delete expenses.

### Expense Categories

- **Expense Categories**: Categorize expenses for better organization and tracking.
- **Create, Update, Delete Categories**: Allow users to manage their expense categories.


### Multi-User Support

- **Multiple User Accounts**: Support multiple users with individual accounts and separate expense records.
- **Data Isolation**: Ensure data privacy and isolation for each user.

### Data Validation and Error Handling

- **Data Validation**: Implement robust validation to maintain data integrity.
- **Error Handling**: Gracefully handle errors and provide informative error messages.

### Security

- **Security Measures**: Secure API endpoints with authentication and authorization.
- **Data Encryption**: Protect sensitive user data with encryption.

### Deployment on AWS EC2

- **Scalability**: Host the API on an AWS EC2 instance for scalability.
- **Security Groups**: Configure security groups and network settings for secure access.

### Documentation

- **API Documentation**: Provide comprehensive API documentation, including usage examples.
- **Setup Guide**: Explain how to set up the API, configure the database, and deploy it.


### Scalability and Performance

- **Scalability**: Design the API for scalability to handle a growing number of users and expenses.
- **Performance Optimization**: Optimize database queries for efficient performance.


## Prerequisites

Before you begin, make sure you have the following:

- Java Development Kit (JDK) 17 or later installed.
- MySQL database server.
- Git for cloning the repository.
- An AWS EC2 instance set up and accessible.

## Getting Started

### Clone the Repository

Clone the Expense Tracker API repository to your local machine:

```bash
git clone https://github.com/yourusername/expense-tracker-api.git
cd expense-tracker-api
