package de.marvin2k0.custommotd.commands;

import de.marvin2k0.custommotd.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MotdCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length >= 2)
        {
            if (sender instanceof Player && !((Player) sender).hasPermission("custummotd.set"))
            {
                sender.sendMessage(Main.getInstance().getMessage("no-permission"));

                return true;
            }

            String msg = "";

            for (int i = 1; i < args.length; i++)
            {
                msg += args[i] + " ";
            }

            Main.getInstance().setMotd(msg);
            sender.sendMessage(Main.getInstance().getMessage("motd-set"));

            return true;
        }
        else
        {
            sender.sendMessage(Main.getInstance().getMessage("wrong-usage"));

            return true;
        }
    }
}
