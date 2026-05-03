package de.xcuzimsmart.pluginhelper.code.main.java.commands;

import de.api.devtools.audience.Messenger;
import de.api.devtools.command.SimpleCommand;
import de.api.devtools.plugin.MinecraftPlugin;
import de.xcuzimsmart.pluginhelper.code.main.java.utils.BanReason;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class PunishCommand extends SimpleCommand {

    public PunishCommand(final MinecraftPlugin plugin) {
        super(plugin, "punish", "*", false);
    }

    @Override
    public void run(CommandSender commandSender, String[] args) {
        if (args.length != 2) {
            sendHelpInfo(commandSender);
            return;
        }

        final String playerName = args[0];
        final String reason = BanReason.of(BanReason.valueOf(args[1]));
        final BanList banList = Bukkit.getBanList(BanList.Type.NAME);

        if (banList.isBanned(playerName)) {
            Messenger.sendMessage(plugin, commandSender, "This Player is already banned!", null);
            return;
        }

        banList.addBan(playerName, reason, null, null);
        Messenger.sendMessage(plugin, commandSender, ChatColor.GRAY + "The Player: " + ChatColor.AQUA + playerName + ChatColor.GRAY + " has been " + ChatColor.RED + "Banned!", null);
    }

    @Override
    public void sendHelpInfo(CommandSender commandSender) {
    }
}
