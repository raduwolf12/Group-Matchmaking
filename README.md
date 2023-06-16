# Matchmaking tool
This application was designed and build for "Software Engineering and Architecture" course.

## <a name="team-members"></a>Team Members
* Taraburca Radu - <rtaraburca@yahoo.com> / <whx862@alumni.ku.dk>
* Mihai Razvan Costescu - <wrl572@alumni.ku.dk>
* Thomas Jackson Terry- <pdh248@alumni.ku.dk>
* Matthieu Massardier- <cxs929@alumni.ku.dk>
## Requirements:

- Node.js and Angular CLI installed.
- Java Development Kit (JDK) installed (jdk 17.0.7 or later).
- PostgreSQL database installed (10 or later).
- Docker (Optional)
  

### Install the required software:

Node.js and Angular CLI:

	Visit the Node.js website: https://nodejs.org
	Download and install the appropriate version of Node.js for your operating system.
	Note: For this project Node v18.16.0 was used and the npm version 9.6.7.
	Install the Angular CLI globally by running the following command:
		npm install -g @angular/cli

 Java Development Kit (JDK):

	Visit the Oracle website or OpenJDK website to download and install the JDK for your operating system.
	Set up the JAVA_HOME environment variable and add the JDK's "bin" directory to the system's PATH variable. 
	You can find instructions specific to your operating system online.
	Note: For this project it jdk 17.0.7 was used.

 PostgreSQL Database:

	Visit the PostgreSQL website: https://www.postgresql.org
	Download and install the appropriate version of PostgreSQL for your operating system.
	Note: For this project the databased used was "PostgreSQL 10"
	During the installation, take note of the hostname, port, database name, username, and password used for the PostgreSQL installation.
	Note: For this project the port is 5432, the username and password are postgres, and the database name is matchmaking
### How to run the application:

Before starting the application, it is necessary to create the database called "matchmaking". 
It is also necessary that the port on which postgres runs is 5432 and the password and user are postgres. 
Otherwise, the backend will not work and therefore the frontend will not work either.

Before starting the application, it is necessary to do the following command in cmd:

	npm install -g http-server

For the convenience of the user, an executable file has been created that will start your backend and frontend directly. 
The executable file is "start.cmd". Also in the application folder there are two bat executable files, called Frontend and Backend, which start the frontend and the backend, respectively.

If the user wants to manually start the backend and the frontend from cmd, then he must follow the following steps:
	To start the backend, the user must open a cmd in the directory with the application and put the following command:
	
	java -jar backend.jar
Note: You can use the "path to jdk\bin\java" -jar backend.jar as a command if you don't want to set JAVA_HOME 
To start the frontend, the user must open a cmd in the directory with the application and enter the following command: 
			
	http-server ./frontend -p 4200

## Compilation

1. Install the required software as mentioned in the Requirements section.

2. Frontend (Angular):
   - Open a command prompt or terminal.
   - Navigate to the "frontend" directory.
   - Run the following command to install the dependencies:

     npm install

   - Once the dependencies are installed, build the Angular application using the following command:

     ng build --configuration production

3. Backend (Java):
   - Open a command prompt or terminal.
   - Navigate to the "backend" directory.
   - Compile the Java source code and package it into a JAR file using the following commands:

     mvnw clean package -DskipTests

     Replace "your-application.jar" with the desired name for your JAR file.

4. Database (PostgreSQL):
   - Ensure that you have a PostgreSQL database installed and running.
   - Create a new database and note down the connection details (hostname, port, database name, username, password).

5. Configuration:
   - In the "backend" directory, locate the "application.properties" file.
   - Open the file in a text editor and update the PostgreSQL connection details:

     spring.datasource.url=jdbc:postgresql://hostname:port/database
     spring.datasource.username=username
     spring.datasource.password=password

6. Docker(Optional):
   - The alternative to run the application, there is also the docker version. 
   - To be able to run the application in docker, you need to install docker: https://www.docker.com/
   - To run the application, open a cmd in the folder where the "docker-compose.yml" file is located and use the command:
	
     docker compose up

!Note:
	To be able to use docker, some changes are also needed in the application.properties in the backend.
	These lines should be uncommented:
  
		#spring.datasource.url=jdbc:postgresql://localhost:5432/compose-postgres
		#spring.datasource.username=compose-postgres
		#spring.datasource.password=compose-postgres
		#spring.datasource.driver-class-name=org.postgresql.Driver
		#spring.jpa.properties.hibernate.dialect =org.hibernate.dialect.PostgreSQLDialect
	
  And also a new jar needs to be created (using the "mvnw clean package -DskipTests" command), which should be moved from the target folder in the backend to the backend 
	folder to be at the same level as the file called Dockerfile
	
  1. Build the resources :

	```bash
	# Build the frontend
	cd Frontend
	npm install
	node_modules/@angular/cli/bin/ng.js build
	# Build the backend
	cd ../Backend
	./mvnw clean package -DskipTests
	```

  2. Run the docker compose :
  

	```bash
	cd ..   # If you are still in the Backend folder
	docker compose up --build # add -d if you want to run it in background
	```


## Usage

1. Run the application:
   - Open a command prompt or terminal.
   - Navigate to the main folder (the one containing "frontend" and "backend" directories).
   - Run the following command to start the frontend and backend servers:

     start cmd /k "cd frontend && http-server ./dist -p 4200"
     start cmd /k "cd backend/target && java -jar demo-0.0.1-SNAPSHOT.jar"


2. Access the application:
   - Open a web browser.
   - Enter the following URL: http://localhost:4200
   - You should now see the Angular frontend of the application.

### Existing users
Within the application there is a component that automatically adds users. 
One of the users is the Professor who has the following credentials:
Email: abc@alumni.ku.dk
Password: TESTUSERPASS
One of the users is the Teacher who has the credentials
Email: qwe@alumni.ku.dk
Password: TESTUSERPASS
