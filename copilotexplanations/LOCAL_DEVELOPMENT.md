# 🔧 Lokale Entwicklung

## IDE / Lokal Starten

### Option 1: Mit lokaler PostgreSQL (Docker Container)

```bash
# Terminal 1: PostgreSQL Container starten
docker run --name bundestagstracker-postgres \
  -e POSTGRES_DB=bundestagstracker \
  -e POSTGRES_USER=danielfandin \
  -e POSTGRES_PASSWORD=1234 \
  -p 5433:5432 \
  -v postgres_dev_data:/var/lib/postgresql \
  postgres:16-alpine

# Terminal 2: IDE/App starten
# IDE Konfiguration:
# - Rechts-Klick auf Projekt → Run/Debug Configurations
# - VM Options: (leer lassen)
# - Profiles: local
# - Run BundestagstrackerApplication
```

### Option 2: In der IDE starten (mit "local" Profil)

**IntelliJ IDEA:**
1. Klick auf "Run" → "Edit Configurations"
2. Bei "BundestagstrackerApplication" "VM options" setzten: `-Dspring.profiles.active=local`
3. Oder im Menü: `Run` → `Run with Profile` → `local`

**Oder Environment-Variablen in IDE setzen:**
```
DB_HOST=localhost
DB_PORT=5433
DB_NAME=bundestagstracker
DB_USERNAME=danielfandin
DB_PASSWORD=1234
```

### Option 3: Maven Command Line

```bash
# Mit "local" Profil starten
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

---

## Wie funktioniert das Profil-System?

**application.properties** = Production (Docker)
```ini
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
# Erwartet Environment-Variablen!
```

**application-local.properties** = Lokale Entwicklung
```ini
spring.datasource.url=jdbc:postgresql://localhost:5433/bundestagstracker
spring.datasource.username=danielfandin
spring.datasource.password=1234
spring.jpa.show-sql=true          # Debug Info
spring.thymeleaf.cache=false      # Kein Caching
```

---

## Schneller Start (alles auf einmal)

```bash
# Terminal 1: PostgreSQL Container
docker run --name bundestagstracker-postgres \
  -e POSTGRES_DB=bundestagstracker \
  -e POSTGRES_USER=danielfandin \
  -e POSTGRES_PASSWORD=1234 \
  -p 5433:5432 \
  -v postgres_dev_data:/var/lib/postgresql \
  postgres:16-alpine

# Terminal 2: App mit local Profil
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

Öffne dann: **http://localhost:8080**

---

## Tipps für lokale Entwicklung

✅ **Lokal:** `application-local.properties` nutzen
- Debug SQL Queries: `spring.jpa.show-sql=true`
- Kein Caching: `spring.thymeleaf.cache=false`
- Schneller Reload

✅ **Production (Docker):** `application.properties` nutzen
- Environment-Variablen laden
- Caching aktiviert
- Performance optimiert

✅ **IDE Tipps:**
- Spring Boot Dashboard für schnellen Überblick
- DevTools für automatischen Reload
- Breakpoints setzen und debuggen

---

## Probleme beheben

### Problem: "Connection refused"
```bash
# PostgreSQL nicht erreichbar?
docker ps | grep postgres
# Sollte laufen. Falls nicht:
docker start bundestagstracker-postgres
```

### Problem: "Relation does not exist"
```bash
# Datenbank-Tabellen nicht vorhanden?
# Lösung: `spring.jpa.hibernate.ddl-auto=create` temporär setzen
```

### Problem: "Password authentication failed"
```bash
# Passwort falsch in application-local.properties?
# Prüfe: DB_USERNAME und DB_PASSWORD stimmen überein
```

---

## Clean Up (nach Entwicklung)

```bash
# Container stoppen
docker stop bundestagstracker-postgres

# Container und Daten löschen
docker rm -v bundestagstracker-postgres
```

---

**Happy Coding! 🚀**

