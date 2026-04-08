# Bundestagstracker - Docker Setup & Datenkonfiguration

## 🐳 Docker Container starten

### Schritt 1: Projekt bauen
```bash
./mvnw clean package
```

### Schritt 2: Container starten
```bash
docker-compose up -d
```

### Schritt 3: Status prüfen
```bash
docker-compose ps
docker-compose logs -f app
```

## ⚙️ Datenkonfiguration

### `.env` Datei verstehen
Die `.env` Datei enthält alle Geheimnisse:
```
POSTGRES_DB=bundestagstracker          # Datenbankname
POSTGRES_USER=danielfandin            # PostgreSQL Benutzer
POSTGRES_PASSWORD=1234                # PostgreSQL Passwort
DB_NAME=bundestagstracker             # App Datenbankname
DB_USERNAME=danielfandin              # App DB Benutzer
DB_PASSWORD=1234                      # App DB Passwort
```

### Umgebungsvariablen anpassen
1. Ändere die Werte in `.env` nach Bedarf
2. Container neu starten: `docker-compose down && docker-compose up -d`
3. **WICHTIG**: `.env` NIEMALS committen! (steht in `.gitignore`)

## 💾 Datenpersistenz

### PostgreSQL Daten speichern
```yaml
volumes:
  - postgres_data:/var/lib/postgresql/data
```
- Daten werden im Docker Volume `postgres_data` gespeichert
- Bleiben erhalten, auch wenn Container stoppt
- Anschauen: `docker volume inspect postgres_data`

### Backup erstellen
```bash
docker-compose exec db pg_dump -U danielfandin bundestagstracker > backup.sql
```

### Datenbank wiederherstellen
```bash
docker-compose exec -T db psql -U danielfandin bundestagstracker < backup.sql
```

## 🔍 Was wurde korrigiert

| Problem | Vorher | Nachher |
|---------|--------|---------|
| Port-Mapping | `5433:5342` ❌ | `5433:5432` ✅ |
| Volumes | Keine | `postgres_data:/var/lib/postgresql/data` |
| Health-Check | Unvollständig | Mit interval, timeout, retries |
| Passwörter | Hardcodiert | In `.env` Datei |
| Datenpersistenz | Verloren bei Restart | Persistent im Volume |

## 🚀 Auf VPS deployen

```bash
# 1. .env mit Produktions-Werten erstellen
cp .env.example .env
nano .env  # Sichere Passwörter setzen!

# 2. Mit Produktion-Config starten
docker-compose -f docker-compose.prod.yml up -d

# 3. Logs überwachen
docker-compose -f docker-compose.prod.yml logs -f app
```

## 🐛 Häufige Probleme

### Container startet nicht
```bash
docker-compose logs app
# Prüfe DB_HOST, DB_PORT, Passwörter in .env
```

### Datenbank verbindung fehlgeschlagen
```bash
# Starte DB neu
docker-compose down
docker-compose up -d db
docker-compose up -d app
```

### Port bereits in Benutzung
```bash
# Port 8080 oder 5433 bereits verwendet?
docker-compose down
# oder ändere Port in docker-compose.yml
```

---

**Weitere Hilfe?** Frag nach!

