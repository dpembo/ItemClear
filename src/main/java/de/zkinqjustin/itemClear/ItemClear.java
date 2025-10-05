package de.zkinqjustin.itemClear;

import de.zkinqjustin.itemClear.commands.ItemClearCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;

public class ItemClear extends JavaPlugin {
    private int clearInterval;
    private String clearMessage;
    private String warningMessage;
    private int warningTime;
    private String commandSuccessMessage;
    private String commandBroadcastMessage;
    private String commandNoPermissionMessage;
    private String commandPlayerOnlyMessage;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        loadConfig();

        getCommand("ic").setExecutor(new ItemClearCommand(this));

        scheduleClearTask();

        getLogger().info("ItemClear wurde eingeschaltet!");
        getLogger().info("made by zKinqJustinTW");
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();

        config.addDefault("clear-interval", 600); 
        config.addDefault("warning-time", 60); 
        config.addDefault("clear-message", "&cAlle Items wurden vom Boden entfernt!");
        config.addDefault("warning-message", "&eAchtung! In einer Minute werden alle Items vom Boden entfernt!");
        config.addDefault("command-success-message", "&aItem Clear wurde sofort ausgeführt!");
        config.addDefault("command-broadcast-message", "&eEin Admin hat den Item Clear manuell ausgelöst!");
        config.addDefault("command-no-permission-message", "&cDu hast keine Berechtigung für diesen Command!");
        config.addDefault("command-player-only-message", "&cDieser Command kann nur von Spielern ausgeführt werden!");
        config.options().copyDefaults(true);
        saveConfig();


        clearInterval = config.getInt("clear-interval") * 20; 
        warningTime = config.getInt("warning-time");
        clearMessage = ChatColor.translateAlternateColorCodes('&', config.getString("clear-message"));
        warningMessage = ChatColor.translateAlternateColorCodes('&', config.getString("warning-message"));
        commandSuccessMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-success-message"));
        commandBroadcastMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-broadcast-message"));
        commandNoPermissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-no-permission-message"));
        commandPlayerOnlyMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-player-only-message"));
    }

    private void scheduleClearTask() {

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().runTaskLater(ItemClear.this, () -> {
                    Bukkit.broadcastMessage(warningMessage);
                }, (clearInterval - (warningTime * 20)) / 20);

                Bukkit.getScheduler().runTaskLater(ItemClear.this, () -> {
                    Bukkit.getWorlds().forEach(world -> {
                        world.getEntities().stream()
                                .filter(entity -> entity.getType().toString().contains("ITEM"))
                                .forEach(org.bukkit.entity.Entity::remove);
                    });
                    Bukkit.broadcastMessage(clearMessage);
                }, clearInterval / 20);
            }
        }.runTaskTimer(this, 0, clearInterval);
    }

    public String getCommandSuccessMessage() {
        return commandSuccessMessage;
    }

    public String getCommandBroadcastMessage() {
        return commandBroadcastMessage;
    }

    public String getCommandNoPermissionMessage() {
        return commandNoPermissionMessage;
    }

    public String getCommandPlayerOnlyMessage() {
        return commandPlayerOnlyMessage;
    }

    @Override
    public void onDisable() {
        getLogger().info("ItemClear wurde deaktiviert!");
    }
}
