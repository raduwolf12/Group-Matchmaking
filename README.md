# Matchmaking tool

## Usage

1. Build the ressources :

```bash
# Build the frontend
cd Frontend
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
