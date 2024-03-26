# Secret Santa service
RESTFul API Service for playing Secret Santa

## Описание настройки и запуска приложения
1) Скачайте репозиторий: git clone https://github.com/Leo13Gro/SecretSanta.git
2) Перейдите в папку проекта и введите: ./gradlew bootRun

### Запуск приложени в Docker
1. Из папки проекта: ./gradlew bootJar
2. docker build -t myorg/myapp .
3. docker run -p 8080:8080 myorg/myapp
