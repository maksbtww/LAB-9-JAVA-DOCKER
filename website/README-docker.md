# Docker-инструкция для Spring Boot + MySQL

## 1. Сборка и запуск контейнеров

```bash
git clone <ВАШ_РЕПОЗИТОРИЙ>
cd website
docker-compose up --build
```

- Приложение будет доступно на http://localhost:8080
- MySQL будет доступен на порту 3306 (root/admin, база service)

## 2. Перенос на другой ПК

1. Запушьте проект на GitHub:
   ```bash
   git add .
   git commit -m "dockerize project"
   git push
   ```
2. На другом ПК:
   ```bash
   git clone <ВАШ_РЕПОЗИТОРИЙ>
   cd website
   docker-compose up --build
   ```

## 3. Возможные проблемы
- Если приложение стартует раньше MySQL, убедитесь, что используется healthcheck и depends_on (уже настроено).
- Проверьте, что порты 8080 и 3306 свободны.
- Для чистого запуска можно удалить volume:
  ```bash
  docker-compose down -v
  ``` 