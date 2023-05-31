# Partners API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

This project is an API built using **Java, Java Spring, Flyway Migrations, PostgresSQL as the database, and Spring Security for authentication control.** 

The API simulates the functionality of a food delivery platform like [iFood](https://www.ifood.com.br/) and [Zé Delivery](https://www.ze.delivery/). Users can register themselves into the app, and logged-in users can search for nearby restaurant partners, while ADMIN users can register new partners in the API.

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

3. Install [PostgresSQL](https://www.postgresql.org/)
4. Install  [Postgis Extension](https://postgis.net/documentation/getting_started/)

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
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.

Also, this project is using the PostgresSQL extension [Postgis](https://postgis.net/) to store coordinates in [GeoJson](https://geojson.org/) pattern.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.




