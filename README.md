# Space Simulation

## Description

Web application that simulates life in space.

API (Spring Boot) + frontend (HTML, CSS, JS).

## Installation

You can clone this repository and use it locally:
```sh
$ git clone https://github.com/NAntigravity/ShopAPI.git
```
---
**Using Maven plugin**

First you should do clean installation:
```sh
$ mvn clean install
```
You can start application using Spring Boot custom command:
```sh
$ mvn spring-boot:run
```
---
**Using Docker**

To start app on port 8080 use this command:
```sh
$ docker run -d -p 8080:8080 nantigravity/space-simulation
```