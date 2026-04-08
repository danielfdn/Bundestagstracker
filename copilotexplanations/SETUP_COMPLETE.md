# 🎉 Bundestagstracker - Docker Setup Komplett!

## ✅ Was wurde gelöst

### 1. **Build-Problem** ❌ → ✅
**Problem:** `./mvnw clean package` schlug fehl mit Test-Fehlern
**Lösung:** 
- H2-Datenbank als Test-Abhängigkeit hinzugefügt
- Separate `application.properties` für Tests erstellt
- Lombok entfernt (Java 21 Kompatibilität)

### 2. **PostgreSQL 18+ Volumen-Fehler** ❌ → ✅
**Problem:** PostgreSQL 18+ erwartet Volume bei `/var/lib/postgresql` statt `/var/lib/postgresql/data`
**Lösung:**
- Volume-Pfad in `docker-compose.yml` korrigiert
- Datenpersistenz funktioniert jetzt korrekt

### 3. **Umgebungsvariablen nicht geladen** ❌ → ✅
**Problem:** Spring Boot las `.env` Variablen nicht
**Lösung:**
- `entrypoint.sh` erstellt, das `.env` zu Java System Properties konvertiert
- Dockerfile angepasst, um `entrypoint.sh` zu nutzen
- `docker-compose.yml` mit `env_file: .env` konfiguriert

## 📁 Neue Dateien erstellt

```
├── .env                    # Umgebungskonfiguration
├── entrypoint.sh          # Startup-Script für Docker
├── DOCKER_SETUP.md        # Docker Dokumentation
├── DATENPERSISTENZ.md     # Datenpersistenz Dokumentation
└── docker-compose.prod.yml # Produktion Setup (optional)
```

## 🚀 Quick Start

### Container starten
```bash
cd "/Users/danielfandin/Library/Mobile Documents/com~apple~CloudDocs/Programmieren/Bundestagstracker"
docker-compose up -d
```

### Status prüfen
```bash
docker-compose ps
docker-compose logs app
```

### App öffnen
```
http://localhost:8080
```

## 🗄️ Datenbankverbindung

Die App verbindet sich zu:
```
jdbc:postgresql://db:5432/bundestagstracker
User: danielfandin
Password: 1234
```

Änderbare Werte in `.env` Datei:
```
DB_HOST=db
DB_PORT=5432
DB_NAME=bundestagstracker
DB_USERNAME=danielfandin
DB_PASSWORD=1234
```

## 💾 Datenpersistenz

PostgreSQL-Daten werden in Docker Volume gespeichert:
```bash
docker volume ls           # Volumen anschauen
docker volume inspect postgres_data
```

Backup erstellen:
```bash
docker-compose exec db pg_dump -U danielfandin -d bundestagstracker > backup.sql
```

## 🔧 Für VPS-Deployment

Auf dem VPS:
```bash
git clone <repo>
cd Bundestagstracker
cp .env.example .env
# Passwörter in .env ändern!
docker-compose up -d
```

Mit Nginx Reverse Proxy für HTTPS (optional):
```bash
docker-compose -f docker-compose.prod.yml up -d
```

## ✅ Checkliste

- [x] Maven Package erfolgreich
- [x] Docker Image bauen funktioniert
- [x] PostgreSQL läuft und ist healthy
- [x] App verbindet sich zu Datenbank
- [x] Tomcat startet auf Port 8080
- [x] Datenpersistenz mit Volumes
- [x] Umgebungsvariablen funktionieren
- [x] Health Checks konfiguriert

## 🎯 Status

```
✅ Prod-Ready!
```

Die Anwendung ist jetzt vollständig dockerisiert und ready for deployment auf einem VPS!

---

**Brauchst du noch etwas?** Frag nach!

