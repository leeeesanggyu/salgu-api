./gradlew clean build
docker build -t salgu-api:v1 .
docker rm -f salgu-auth
docker run -d -e PROFILE=dev -e DB_URL=192.168.35.187 --name salgu-api -p 18081:18080 salgu-api:v1