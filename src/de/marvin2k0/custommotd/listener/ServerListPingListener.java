package de.marvin2k0.custommotd.listener;

import de.marvin2k0.custommotd.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener
{
    @EventHandler
    public void onPing(ServerListPingEvent e)
    {
        e.setMotd(Main.getInstance().getMotd());
    }
}
