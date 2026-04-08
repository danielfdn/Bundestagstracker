# 🚀 QUICK START - Lokale Entwicklung

## IDE Lokal Starten (z.B. IntelliJ)

### Schnelle Methode:

**Terminal 1: PostgreSQL Container**
```bash
docker run --name bundestagstracker-postgres \
  -e POSTGRES_DB=bundestagstracker \
  -e POSTGRES_USER=danielfandin \
  -e POSTGRES_PASSWORD=1234 \
  -p 5433:5432 \
  -v postgres_dev_data:/var/lib/postgresql \
  postgres:16-alpine
```

**Terminal 2: In der IDE**
1. Rechts-Klick auf `BundestagstrackerApplication.java`
2. "Run" → "Run 'BundestagstrackerApplication'"
3. Oder: `Shift+F10` (Keyboard Shortcut)

**Oder mit Maven:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

---

## Wie funktioniert es?

**Zwei Application Properties Dateien:**

1. **`application.properties`** → Production (Docker)
   - Nutzt Environment-Variablen
   - Caching aktiviert
   - Für VPS deployment

2. **`application-local.properties`** → Lokale Entwicklung
   - Hardcodierte Werte (localhost:5433)
   - Caching deaktiviert (schnellerer Reload)
   - Debug-Infos eingeblendet

**Das Profil wird aktiviert durch:**
- IDE Run Config: `--spring.profiles.active=local`
- Maven: `-Dspring-boot.run.arguments="--spring.profiles.active=local"`

---

## App öffnen

```
http://localhost:8080
```

---

## Wenn es nicht funktioniert:

```bash
# PostgreSQL läuft?
docker ps | grep postgres

# Falls nicht:
docker start bundestagstracker-postgres

# Logs anschauen:
docker logs bundestagstracker-postgres
```

---

**Viel Spaß beim Entwickeln! 🎉**

