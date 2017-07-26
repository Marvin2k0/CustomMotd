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
            if (args[0].equalsIgnoreCase("set"))
            {
                if (sender instanceof Player && !((Player) sender).hasPermission("custummotd.cmd"))
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
            }
            else if(args[0].equalsIgnoreCase("add"))
            {
                String msg = "";

                for (int i = 1; i < args.length; i++)
                {
                    msg += args[i] + " ";
                }

                Main.getInstance().addMotd(msg);
                sender.sendMessage(Main.getInstance().getMessage("motd-added"));
            }
            else if (args[0].equalsIgnoreCase("interval"))
            {
                try
                {
                    int interval = Integer.parseInt(args[1]);


                }
                catch (Exception ex)
                {
                    sender.sendMessage(Main.getInstance().getMessage("error-number"));
                }
            }
            else
            {
                sender.sendMessage(Main.getInstance().getMessage("cmd-not-found"));
            }

            return true;
        }
        else
        {
            sender.sendMessage(Main.getInstance().getMessage("wrong-usage"));

            return true;
        }
    }
}
