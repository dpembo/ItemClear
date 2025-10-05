# 🧹 ItemClear

Ein leistungsstarkes Minecraft Spigot/Paper Plugin, das automatisch Items vom Boden entfernt, um die Server-Performance zu optimieren.

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21-brightgreen.svg)](https://www.spigotmc.org/)
[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Features

- ⏰ **Automatisches Item-Clearing** - Entfernt Items in konfigurierbaren Zeitintervallen
- ⚠️ **Vorwarnsystem** - Warnt Spieler vor dem Item-Clear
- 🎨 **Vollständig anpassbar** - Alle Nachrichten und Zeiten konfigurierbar
- 🔧 **Admin-Command** - Manuelles Item-Clearing per Befehl
- 🌍 **Multi-World Support** - Funktioniert in allen Welten gleichzeitig
- 🎯 **Performance-optimiert** - Minimale Server-Belastung
- 🔐 **Permission-System** - Sichere Rechteverwaltung

## 🚀 Installation

1. Lade die neueste Version von ItemClear herunter
2. Platziere die `.jar` Datei in deinem `plugins` Ordner
3. Starte den Server neu oder lade das Plugin mit einem Plugin-Manager
4. Konfiguriere das Plugin nach deinen Wünschen in der `config.yml`

## ⚙️ Konfiguration

Die `config.yml` wird automatisch beim ersten Start erstellt:

```yaml
# Clear interval in sekunden
clear-interval: 600

# Warnungs timer bevor der clear passiert
warning-time: 60

# Nachricht die angezeigt wird wenn die Items gecleart wurden
clear-message: '&6[ItemClear] &cAlle Items wurden vom Boden entfernt!'

# Warung die Angezeigt wird wenn die Items gecleart werden
warning-message: '&6[ItemClear] &eAchtung! In einer Minute werden alle Items vom Boden entfernt!'

# Command Nachrichten
command-success-message: '&6[ItemClear] &aItem Clear wurde sofort ausgeführt!'
command-broadcast-message: '&6[ItemClear] &eEin Admin hat den Item Clear manuell ausgelöst!'
command-no-permission-message: '&6[ItemClear] &cDu hast keine Berechtigung für diesen Command!'
command-player-only-message: '&6[ItemClear] &cDieser Command kann nur von Spielern ausgeführt werden!'
```

### 🎨 Farbcodes

Das Plugin unterstützt Minecraft-Farbcodes mit dem `&` Symbol:

| Code | Farbe | Code | Farbe |
|------|-------|------|-------|
| `&0` | Schwarz | `&8` | Dunkelgrau |
| `&1` | Dunkelblau | `&9` | Blau |
| `&2` | Dunkelgrün | `&a` | Grün |
| `&3` | Dunkel Aqua | `&b` | Aqua |
| `&4` | Dunkelrot | `&c` | Rot |
| `&5` | Dunkel Lila | `&d` | Rosa |
| `&6` | Gold | `&e` | Gelb |
| `&7` | Grau | `&f` | Weiß |

## 🎮 Commands

| Command | Beschreibung | Permission | Aliases |
|---------|--------------|------------|---------|
| `/ic` | Führt Item Clear sofort aus | `itemclear.admin` oder OP | - |

## 🔐 Permissions

| Permission | Beschreibung | Standard |
|------------|--------------|----------|
| `itemclear.admin` | Erlaubt die Nutzung des `/ic` Commands | OP |

## 🛠️ Entwicklung

### Voraussetzungen

- Java 21 oder höher
- Maven 3.6+
- Paper API 1.21

### Build

```bash
mvn clean package
```

Die fertige `.jar` Datei findest du im `target` Ordner.

### Projektstruktur

```
ItemClear/
├── src/
│   └── main/
│       ├── java/
│       │   └── de/zkinqjustin/itemClear/
│       │       ├── ItemClear.java
│       │       └── commands/
│       │           └── ItemClearCommand.java
│       └── resources/
│           ├── plugin.yml
│           └── config.yml
├── pom.xml
└── README.md
```

## 📊 Funktionsweise

1. **Automatischer Modus**: Das Plugin startet einen Timer basierend auf dem `clear-interval`
2. **Warnung**: `warning-time` Sekunden vor dem Clear wird eine Warnung gesendet
3. **Clearing**: Alle Items werden aus allen Welten entfernt
4. **Wiederholung**: Der Prozess wiederholt sich automatisch

## 🤝 Support

Bei Fragen oder Problemen:
- Erstelle ein Issue auf GitHub
- Kontaktiere den Entwickler: **zKinqJustinTW**

## 📝 Changelog

### Version 1.0
- ✨ Initiales Release
- ⏰ Automatisches Item-Clearing
- ⚠️ Vorwarnsystem
- 🔧 Admin-Command
- 🎨 Vollständig konfigurierbar

## 👨‍💻 Autor

**zKinqJustinTW**

## 📄 Lizenz

Dieses Projekt steht unter der MIT Lizenz.

---

<p align="center">Made with ❤️ by zKinqJustinTW</p>
