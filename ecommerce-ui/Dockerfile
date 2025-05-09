# Stage 1: Compile and Build angular codebase
FROM node:20 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve app with nginx server
FROM nginx:alpine
COPY --from=build /app/dist/ecommerce-ui /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
