package de.xcuzimsmart.pluginhelper.code.main.java.commands;

import de.api.devtools.audience.Messenger;
import de.api.devtools.command.SimpleCommand;
import de.api.devtools.plugin.MinecraftPlugin;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class UnbanCommand extends SimpleCommand {

    public UnbanCommand(final MinecraftPlugin plugin) {
        super(plugin, "unban", "*", false);
    }

    @Override
    public void run(CommandSender commandSender, String[] args) {
        if (args.length != 1) {
            sendHelpInfo(commandSender);
            return;
        }

        final String playerName = args[0];
        final BanList banList = Bukkit.getBanList(BanList.Type.NAME);

        if (!banList.isBanned(playerName)) {
            Messenger.sendMessage(plugin, commandSender, "This Player is not banned!", null);
            return;
        }

        banList.pardon(playerName);
        Messenger.sendMessage(plugin, commandSender, ChatColor.AQUA + playerName + ChatColor.GRAY + " has been " + ChatColor.RED + "Unbanned!", null);
    }

    @Override
    public void sendHelpInfo(CommandSender commandSender) {

    }
}
