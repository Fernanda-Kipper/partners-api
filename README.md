# Partners API

This project is an API built using **Java, Java Spring, Flyway Migrations, PostgresSQL as the database, and Spring Security for authentication control.** 

The API simulates the functionality of a food delivery platform like [iFood](https://www.ifood.com.br/) and [Zé Delivery](https://www.ze.delivery/). Users can registrate themselves into the app, and logged-in users can search for nearby restaurant partners, while ADMIN users can register new partners in the API.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/project-name.git
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

```markdown
GET /partners - Retrieve a list of all partners.

GET /partners/{partnerId} - Retrieve a specific partner by ID.

POST /partners - Register a new partner (ADMIN access required).

GET /partners/search - Search for partners near a specified location.

POST /auth/login - Login into the App

POST /auth/register - Register a new user into the App
```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
ROLE_USER - Standard user role for logged-in users.
ROLE_ADMIN - Admin role for managing partners (registering new partners).
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes PostgresSQL as the database. The necessary database migrations are managed using Flyway.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit message conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.




