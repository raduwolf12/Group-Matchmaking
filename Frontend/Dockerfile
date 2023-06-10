# Base image
FROM node:latest

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire application
COPY . .

# Build the Angular app
RUN npm run build

# Expose the necessary port
EXPOSE 4200

# Start the Angular application
CMD ["npm", "start"]
