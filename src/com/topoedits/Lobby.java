package com.topoedits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright SwordCraft 2015 (c)
 * Anything from this project or IDE is written by ToPoEdiTs under the copyright terms of 2015
 * Copying, Changing or Using any of this code without my permission will result in a void of our
 * Agreements.
 * Thank you.
 */

public class Lobby extends JavaPlugin implements Listener {

    private Servidores servidores;

    public static String TAG = ChatColor.RED.toString() + ChatColor.BOLD + "Network >> §7";
    public static Lobby plugin;
    public static GameEstado estado;

    public void onEnable() {
        servidores = new Servidores(this);
        getLogger().info("Activando todo el [Servidor]");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ScoreBoard(this), this);
        pm.registerEvents(new Servidores(this), this);
        pm.registerEvents(new eventos(this), this);
        pm.registerEvents(new HidePlayers(this), this);
        pm.registerEvents(new PlayerJoinEstado(this), this);
        setEstado(GameEstado.PUBLICO);
        getLogger().info("Activado Correctamente todo el [Servidor]");
    }

    public static GameEstado getEstado() {
        return estado;
    }

    public static void setEstado(GameEstado estado) {
        Lobby.estado = estado;
    }

    public static ItemStack servidores() {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta im = item.getItemMeta();

        im.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Juegos");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Selecciona un modo de juego");
        lore.add(ChatColor.GRAY + "y diviertete con tus amigos!");

        im.setLore(lore);
        item.setItemMeta(im);
        return item;

    }

    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("estado")) {
            if (player.hasPermission("lobby.estado")) {

                if (args.length == 0) {
                    player.sendMessage(Lobby.TAG + " Especifica el estado:");
                    player.sendMessage(Lobby.TAG + " PUBLICO, MANTENIMIENTO, BLOQUEADO");
                    player.sendMessage(Lobby.TAG + " Ejemplo: /estado publico");
                    return true;
                }

                if (args[0].equalsIgnoreCase("publico")) {
                    Lobby.setEstado(GameEstado.PUBLICO);
                    player.sendMessage(Lobby.TAG + " Has puesto el servidor en Modo Publico");
                    return true;
                }
                if (args[0].equalsIgnoreCase("mantenimiento")) {
                    Lobby.setEstado(GameEstado.MANTENIMIENTO);
                    player.sendMessage(Lobby.TAG + " Has puesto el servidor en Modo Mantenimiento");
                    return true;
                }
                if (args[0].equalsIgnoreCase("bloqueado")) {
                    Lobby.setEstado(GameEstado.BLOQUEADO);
                    player.sendMessage(Lobby.TAG + " Has puesto el servidor en Modo Bloqueado");
                    return true;
                }
            }
        }
        return false;
    }


    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                p.getInventory().setItem(0, servidores());
                p.updateInventory();
                p.setOp(false);
            }
        }, 5L);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Player player = event.getPlayer();
            if(player.getItemInHand() != null) {
                ItemStack iteminhand = player.getItemInHand();
                if(iteminhand.hasItemMeta()){
                    if(iteminhand.getItemMeta().hasDisplayName() && iteminhand.getItemMeta().hasLore()){
                        if(iteminhand.equals(servidores())) {
                            servidores.show(player);
                        }
                    }
                }
            }
        }
    }
}
