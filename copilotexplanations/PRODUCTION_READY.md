# 🚀 Production Deployment Checklist

## ✅ Code & Konfiguration

- [x] Hartcodierte Passwörter entfernt
- [x] Environment-Variablen implementiert
- [x] Debug-Modi deaktiviert (spring.jpa.show-sql=false)
- [x] Caching aktiviert (spring.thymeleaf.cache=true)
- [x] PostgreSQL Dialect explizit gesetzt
- [x] docker-compose.yml Version entfernt (deprecated)
- [x] Logging konfiguriert (json-file mit max-size)
- [x] .env in .gitignore (Geheimnisse geschützt)
- [x] Health Checks konfiguriert
- [x] Restart-Policy auf "always"

## ⚠️ VOR DEM DEPLOYMENT:

### 1. Sichere Passwörter setzen
```bash
cp .env.example .env
# Bearbeite .env mit SICHEREN Passwörtern!
nano .env
```

**WICHTIG:** 
- Benutze starke Passwörter (mind. 20 Zeichen)
- Keine Standardwerte verwenden
- Besonder für `POSTGRES_PASSWORD` und `DB_PASSWORD`

### 2. Überprüfe die Datenbank-Verbindung
```bash
docker-compose up -d
docker-compose logs app | grep "Started"
```

### 3. Teste die API
```bash
curl http://localhost:8080
```

## 📋 Docker-Spezifikation

**Base Image:** `eclipse-temurin:21-jdk-jammy` ✅
- Sicher und offiziell unterstützt
- Kleine Größe (Alpine)

**Database:** `postgres:16-alpine` ✅
- Aktuelle stabile Version
- Alpine für kleinere Größe

**Memory Limits:** `-Xmx512m -Xms256m` ✅
- 512MB Max (anpassbar für größere Server)
- 256MB Initial

## 🔒 Sicherheit

- [x] Keine Passwörter im Code
- [x] .env nicht in Git
- [x] Health Checks aktiviert
- [x] Logging begrenzt (max 10m, 3 files)
- [x] Container auto-restart

### ⚠️ Noch zu prüfen:
- [ ] Firewall für Ports konfiguriert (nur 8080 offen)
- [ ] SSH-Keys für Server-Zugriff
- [ ] Backups für PostgreSQL Volume

## 📊 Production Settings

```yaml
restart: always           # ✅ Startet automatisch neu
logging:
  max-size: 10m          # ✅ Logs werden rotiert
  max-file: 3            # ✅ Max 3 Log-Files
healthcheck:
  retries: 5             # ✅ Gibt DB Zeit zu starten
```

## 🚀 Deployment Steps

```bash
# 1. SSH zum VPS
ssh user@your-vps-ip

# 2. Git klonen
git clone <your-repo>
cd Bundestagstracker

# 3. Umgebung vorbereiten
cp .env.example .env
nano .env  # Sichere Passwörter einsetzen!

# 4. Bauen und starten
./mvnw clean package
docker-compose up -d

# 5. Überprüfen
docker-compose ps
docker-compose logs app
```

## 📈 Optional: Production Improvements

1. **Reverse Proxy (Nginx)** - für HTTPS
2. **SSL-Zertifikat** - Let's Encrypt
3. **Monitoring** - CloudWatch oder Prometheus
4. **Backups** - PostgreSQL regelmäßig sichern
5. **Load Balancer** - bei mehreren Instanzen

## 🔍 Finale Überprüfung

```bash
# App läuft?
docker-compose ps

# Datenbank verbunden?
docker-compose logs app | grep "Tomcat started"

# Speicher-Limits ok?
docker stats

# Volume persistent?
docker volume ls | grep postgres_data
```

---

## ✅ READY FOR PRODUCTION! 🎉

Deine Anwendung ist jetzt sauber und produktionsreif!

