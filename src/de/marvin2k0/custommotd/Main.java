package de.marvin2k0.custommotd;

import de.marvin2k0.custommotd.commands.MotdCommand;
import de.marvin2k0.custommotd.listener.ServerListPingListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    private static Main instance;

    @Override
    public void onEnable()
    {
        registerListeners();
        registerCommands();
        loadConfig();

        instance = this;
    }

    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new ServerListPingListener(), this);
    }

    private void registerCommands()
    {
        getCommand("motd").setExecutor(new MotdCommand());
    }

    private void loadConfig()
    {
        getConfig().options().copyDefaults(true);
        getConfig().addDefault("prefix", "&7[&9" + getDescription().getName() + "&7] ");
        getConfig().addDefault("wrong-usage", "&7Wrong usage. Use &c/motd set <motd>");
        getConfig().addDefault("motd-set", "&7Motd &9" + getMotd() + " &7has been set.");
        getConfig().addDefault("no-permission", "&7You don't have the &cpermission &7to use this command.");
        saveConfig();
    }

    public static Main getInstance()
    {
        return instance;
    }

    public String getMessage(String path)
    {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")
                + getConfig().getString(path));
    }

    public void setMotd(String motd)
    {
        getConfig().set("motd", motd);
    }

    public String getMotd()
    {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("motd"));
    }
}
