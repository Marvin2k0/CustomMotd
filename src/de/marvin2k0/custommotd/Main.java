package de.marvin2k0.custommotd;

import de.marvin2k0.custommotd.commands.MotdCommand;
import de.marvin2k0.custommotd.listener.ServerListPingListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin
{
    private static Main instance;

    private ArrayList<String> motdList;

    @Override
    public void onEnable()
    {
        registerListeners();
        registerCommands();
        loadConfig();

        if (getConfig().getBoolean("auto-change"))
            changeMotd();

        instance = this;
        motdList = new  ArrayList<>();
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
        getConfig().addDefault("motd", "&cDefault motd, please change &7-> &9/motd set <motd>");
        getConfig().addDefault("motd-list", motdList);
        getConfig().addDefault("auto-change", true);
        getConfig().addDefault("interval", 5);
        getConfig().addDefault("prefix", "&7[&9" + getDescription().getName() + "&7] ");
        getConfig().addDefault("wrong-usage", "&7Wrong usage. Use &c/motd set <motd>");
        getConfig().addDefault("motd-set", "&7Motd has been set.");
        getConfig().addDefault("motd-added", "&7Motd has been added.");
        getConfig().addDefault("no-permission", "&7You don't have the &cpermission &7to use this command.");
        getConfig().addDefault("cmd-not-found", "&7This command is &cinvalid.");
        getConfig().addDefault("error-number", "&7You can only enter a number.");
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
        saveConfig();
    }

    public String getMotd()
    {
        return ChatColor.translateAlternateColorCodes('&', "&7" + getConfig().getString("motd"));
    }

    public List<String> getMotdList()
    {
        return getConfig().getStringList("motd-list");
    }

    public void addMotd(String motd)
    {
        motdList.add(motd);
        getConfig().set("motd-list", motdList);
        saveConfig();
    }

    public void changeMotd()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            int i = 0;

            public void run()
            {
                setMotd(getMotdList().get(i));
            }
        }, 0, getConfig().getInt("interval") * 20);
    }
}
