# Datenpersistenz & Volumen Verwaltung für Bundestagstracker

## 🗄️ Docker Volumes verstehen

### Aktuelles Setup
```yaml
volumes:
  - postgres_data:/var/lib/postgresql/data
```

Dies erstellt ein **Named Volume** `postgres_data`, das PostgreSQL-Daten speichert.

## 📋 Datenpersistenz prüfen

### 1. Volumen anschauen
```bash
# Alle Volumen auflisten
docker volume ls

# Details eines Volumens
docker volume inspect postgres_data
```

### 2. Container Status
```bash
# Laufende Container
docker-compose ps

# Volumen im Container
docker-compose exec db mount | grep postgres
```

## 💾 Daten sichern & wiederherstellen

### Backup der Datenbank
```bash
# Während Container läuft
docker-compose exec db pg_dump -U danielfandin -d bundestagstracker > backup_$(date +%Y%m%d_%H%M%S).sql

# Von außen (wenn .env gesetzt ist)
docker-compose exec -e PGPASSWORD=1234 db pg_dump -U danielfandin -d bundestagstracker > backup.sql
```

### Datenbank wiederherstellen
```bash
# Backup einspielen
docker-compose exec -T db psql -U danielfandin -d bundestagstracker < backup.sql
```

## 🔍 Probleme beheben

### Problem: Daten verschwinden nach Neustart
**Ursache:** Volumen nicht richtig gemountet
```bash
# Lösung: Container und Volumen löschen und neu starten
docker-compose down -v
./mvnw clean package
docker-compose up -d
```

### Problem: "Permission denied" für /var/lib/postgresql/data
**Ursache:** Berechtigungsproblem beim Volume
```bash
# PostgreSQL-Container manuell prüfen
docker-compose exec db ls -la /var/lib/postgresql/data
```

### Problem: Viel Speicherplatz benötigt
```bash
# Volumen-Größe prüfen
docker volume inspect postgres_data | grep Mountpoint
du -sh /var/lib/docker/volumes/postgres_data/_data
```

## 🚀 Produktion: Alternative Volumes

### Option 1: Bind Mount (einfacher zu backupen)
```yaml
volumes:
  - ./data/postgres:/var/lib/postgresql/data
```

### Option 2: Externe Datenbank (empfohlen)
Nutze ein verwaltetes PostgreSQL-Service:
- AWS RDS
- DigitalOcean Managed Databases
- Heroku PostgreSQL

## ✅ Checkliste für Datenpersistenz

- [ ] `docker volume ls` zeigt `postgres_data`
- [ ] `docker-compose ps` zeigt `db` mit "Up" Status
- [ ] `docker-compose logs db` zeigt keine Fehler
- [ ] Daten bleiben nach `docker-compose down` erhalten
- [ ] Regelmäßige Backups erstellt

---

**Hilfe?** Frag nach!

