package com.topoedits;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinEstado implements Listener {

    private Lobby plugin;

    public PlayerJoinEstado(Lobby lobby) {
        this.plugin = lobby;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if(Lobby.getEstado().equals(GameEstado.MANTENIMIENTO)) {
            if ((!(Bukkit.getServer().hasWhitelist())) || (event.getPlayer().isWhitelisted()))
                return;
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Lobby.TAG + " KICKEADO, El servidor esta en Mantenimiento!");
            }

            if(Lobby.getEstado().equals(GameEstado.BLOQUEADO)) {
                if (!player.isOp()) {
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Lobby.TAG + " KICKEADO, El servidor esta BLOQUEADO!");
                }
            }
    }


}
