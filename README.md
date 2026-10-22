# 🧹 ItemClear

A powerful Minecraft Spigot/Paper plugin that automatically removes items from the ground to optimize server performance.

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21-brightgreen.svg)](https://www.spigotmc.org/)
[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Features

- ⏰ **Automatic Item Clearing** – Removes items at configurable time intervals  
- ⚠️ **Warning System** – Warns players before the item clear  
- 🎨 **Fully Customizable** – All messages and timings are configurable  
- 🔧 **Admin Command** – Manual item clearing via command  
- 🌍 **Multi-World Support** – Works across all worlds simultaneously  
- 🎯 **Performance Optimized** – Minimal server load  
- 🔐 **Permission System** – Secure permission management  

## 🚀 Installation

1. Download the latest version of ItemClear  
2. Place the `.jar` file in your `plugins` folder  
3. Restart the server or load the plugin using a plugin manager  
4. Configure the plugin to your liking in `config.yml`  

## ⚙️ Configuration

The `config.yml` is automatically created on first startup:



```yaml
# Clear interval in seconds
clear-interval: 600

# Warning timer before the clear happens
warning-time: 60

# Message displayed when items are cleared
clear-message: '&6[ItemClear] &cAll items have been removed from the ground!'

# Warning displayed before items are cleared
warning-message: '&6[ItemClear] &eAttention! All items will be removed from the ground in one minute!'

# Command messages
command-success-message: '&6[ItemClear] &aItem clear executed immediately!'
command-broadcast-message: '&6[ItemClear] &eAn admin manually triggered an item clear!'
command-no-permission-message: '&6[ItemClear] &cYou do not have permission for this command!'
command-player-only-message: '&6[ItemClear] &cThis command can only be executed by players!'
```

### 🎨 Color Codes

The plugin supports Minecraft color codes using the `&` symbol:

| Code | Color | Code | Color |
|------|--------|------|--------|
| `&0` | Black | `&8` | Dark Gray |
| `&1` | Dark Blue | `&9` | Blue |
| `&2` | Dark Green | `&a` | Green |
| `&3` | Dark Aqua | `&b` | Aqua |
| `&4` | Dark Red | `&c` | Red |
| `&5` | Dark Purple | `&d` | Pink |
| `&6` | Gold | `&e` | Yellow |
| `&7` | Gray | `&f` | White |

## 🎮 Commands

| Command | Description | Permission | Aliases |
|---------|-------------|------------|---------|
| `/ic` | Executes Item Clear immediately | `itemclear.admin` or OP | - |

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|----------|
| `itemclear.admin` | Allows the use of the `/ic` command | OP |

## 🛠️ Development

### Requirements

- Java 21 or higher
- Maven 3.6+
- Paper API 1.21

### Build


```bash
mvn clean package
```

The finished `.jar` file can be found in the `target` folder.

### Project Structure
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

## 📊 How It Works

1. **Automatic Mode**: The plugin starts a timer based on the `clear-interval`  
2. **Warning**: A warning is sent `warning-time` seconds before the clear  
3. **Clearing**: All items are removed from all worlds  
4. **Repeat**: The process repeats automatically  

## 🤝 Support

If you have questions or issues:  
- Create an issue on GitHub  
- Contact the developer: **zKinqJustinTW**

## 📝 Changelog

### Version 2.0 (dpembo)
- ✨ **Fork Release**

  Introduces the dpembo-maintained branch of ItemClear with updated compatibility and safety improvements
- 🌐 **Fully Translated to English**
  
  All messages, configuration entries, and documentation are now English‑native for international servers, with in game meessages supporting plaeholders for correct display of counts and timers
  
- 🖼️ **Item Frame Protection**

  Hard‑blocked item removal from item frames to ensure decorative builds and displays remain untouched under all conditions
  
- 🧹 **Smart Floor‑Only Clearing**

  Added a world scan check so the clear task only runs when actual dropped items exist, preventing unnecessary cycles
  
- ☕ **Updated to Java 25**

  Migrated the project to Java 25, matching Paper 26.2+ requirements and ensuring future compatibility
  
- 📄 **Added License File**

  Project now includes a proper license for distribution and contribution clarity
  
- 🚀 **Release Automation Added**

  GitHub Release Action implemented for automatic packaging and publishing of new versions


### Version 1.0 (zKinqJustinTW)
- ✨ Initial release  
- ⏰ Automatic item clearing  
- ⚠️ Warning system  
- 🔧 Admin command  
- 🎨 Fully configurable

## 👨‍💻 Author
**dpembo** (v2+)
**zKinqJustinTW** (v1)


## 📄 License

This project is licensed under the MIT License.

---

<p align="center">Made with ❤️ by zKinqJustinTW (v1)</p>
<P align="center">Updated ✨ by dpembo (v2)</p>
