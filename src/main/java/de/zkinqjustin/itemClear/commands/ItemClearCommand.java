package de.zkinqjustin.itemClear.commands;

import de.zkinqjustin.itemClear.ItemClear;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemClearCommand implements CommandExecutor {

    private final ItemClear plugin;

    public ItemClearCommand(ItemClear plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getCommandPlayerOnlyMessage());
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp() && !player.hasPermission("itemclear.admin")) {
            player.sendMessage(plugin.getCommandNoPermissionMessage());
            return true;
        }

        Bukkit.getWorlds().forEach(world -> {
            world.getEntities().stream()
                    .filter(entity -> entity.getType().toString().contains("ITEM"))
                    .forEach(org.bukkit.entity.Entity::remove);
        });

        player.sendMessage(plugin.getCommandSuccessMessage());
        Bukkit.broadcastMessage(plugin.getCommandBroadcastMessage());

        return true;
    }
}
