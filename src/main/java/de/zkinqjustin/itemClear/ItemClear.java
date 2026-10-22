package de.zkinqjustin.itemClear;

import de.zkinqjustin.itemClear.commands.ItemClearCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;

public class ItemClear extends JavaPlugin {
    private int initialDelayTicks;
    private int clearIntervalTicks;
    private int warningTimeSeconds;
    private int minItemsForWarning;
    private boolean announceClearWhenEmpty;
    private String clearMessage;
    private String warningMessage;
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

        getLogger().info("ItemClear has been enabled!");
        getLogger().info("made by zKinqJustinTW, Forked and updated by dpembo");
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();

        config.addDefault("initial-delay", 0);
        config.addDefault("clear-interval", 600);
        config.addDefault("warning-time", 60);
        config.addDefault("min-items-for-warning", 1);
        config.addDefault("announce-clear-when-empty", false);
        config.addDefault("clear-message", "&cAll items on the ground have been removed! (&f{count}&c items)");
        config.addDefault("warning-message", "&eWarning! &f{count}&e items on the ground will be removed in &f{time}&e!");
        config.addDefault("command-success-message", "&aItem Clear has been executed!");
        config.addDefault("command-broadcast-message", "&eAn admin has manually triggered Item Clear!");
        config.addDefault("command-no-permission-message", "&cYou do not have permission for this command!");
        config.addDefault("command-player-only-message", "&cThis command can only be executed by players!");

        config.options().copyDefaults(true);
        saveConfig();

        int intervalSeconds = Math.max(1, config.getInt("clear-interval"));
        initialDelayTicks = Math.max(0, config.getInt("initial-delay")) * 20;
        clearIntervalTicks = intervalSeconds * 20;
        // Warning can't be earlier than the start of the cycle
        warningTimeSeconds = Math.min(Math.max(0, config.getInt("warning-time")), intervalSeconds);
        minItemsForWarning = Math.max(1, config.getInt("min-items-for-warning"));
        announceClearWhenEmpty = config.getBoolean("announce-clear-when-empty");
        clearMessage = ChatColor.translateAlternateColorCodes('&', config.getString("clear-message"));
        warningMessage = ChatColor.translateAlternateColorCodes('&', config.getString("warning-message"));
        commandSuccessMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-success-message"));
        commandBroadcastMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-broadcast-message"));
        commandNoPermissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-no-permission-message"));
        commandPlayerOnlyMessage = ChatColor.translateAlternateColorCodes('&', config.getString("command-player-only-message"));
    }

    /** Formats seconds as "1 minute", "2 minutes", "45 seconds", etc. */
    private String formatDuration(int seconds) {
        if (seconds >= 60 && seconds % 60 == 0) {
            int minutes = seconds / 60;
            return minutes + (minutes == 1 ? " minute" : " minutes");
        }
        return seconds + (seconds == 1 ? " second" : " seconds");
    }

    /** Counts dropped item entities across all worlds. Must be called on the main thread. */
    private int countItems() {
        int total = 0;
        for (org.bukkit.World world : Bukkit.getWorlds()) {
            total += world.getEntitiesByClass(Item.class).size();
        }
        return total;
    }

    /** Removes all dropped item entities and returns how many were removed. Main thread only. */
    public int clearItems() {
        int removed = 0;
        for (org.bukkit.World world : Bukkit.getWorlds()) {
            for (Item item : world.getEntitiesByClass(Item.class)) {
                item.remove();
                removed++;
            }
        }
        return removed;
    }

    private void scheduleClearTask() {
        final long warningDelayTicks = Math.max(0L, (long) clearIntervalTicks - (warningTimeSeconds * 20L));
        final String timeText = formatDuration(warningTimeSeconds);

        new BukkitRunnable() {
            @Override
            public void run() {
                // Warning: only sent if there are enough items on the ground at this point
                Bukkit.getScheduler().runTaskLater(ItemClear.this, () -> {
                    int count = countItems();
                    if (count >= minItemsForWarning) {
                        Bukkit.broadcastMessage(warningMessage
                                .replace("{count}", String.valueOf(count))
                                .replace("{time}", timeText));
                    }
                }, warningDelayTicks);

                // Clear
                Bukkit.getScheduler().runTaskLater(ItemClear.this, () -> {
                    int removed = clearItems();
                    if (removed > 0 || announceClearWhenEmpty) {
                        Bukkit.broadcastMessage(clearMessage.replace("{count}", String.valueOf(removed)));
                    }
                }, clearIntervalTicks);
            }
        }.runTaskTimer(this, initialDelayTicks, clearIntervalTicks);
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
        getLogger().info("ItemClear is disabled!");
    }
}