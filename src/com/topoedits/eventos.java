package com.topoedits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class eventos implements Listener {

    private Lobby plugin;

    public eventos(Lobby lobby) {
        this.plugin = lobby;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        ItemStack tienda = new ItemStack(Material.CHEST);
        ItemMeta tiendaMeta = tienda.getItemMeta();
        tiendaMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Tienda VIP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Click para abrir la tienda!");
        tiendaMeta.setLore(lore);
        tienda.setItemMeta(tiendaMeta);
        player.getInventory().remove(Material.CHEST);
        player.getInventory().setItem(4, tienda);

    }
    @EventHandler(priority = EventPriority.HIGH)
    public void compass(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand().getType() == Material.CHEST){
            player.performCommand("buy");
            //   player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            return;
        }
    }

    @EventHandler
    public void alSalir(PlayerKickEvent event) {
        Player player = event.getPlayer();
        event.setLeaveMessage("");
        player.setOp(false);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void VIP(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if(!(player.hasPermission("lobby.vip")) && (Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers())) {
            event.setResult(PlayerLoginEvent.Result.ALLOWED);

        } else {
            event.setKickMessage(Lobby.TAG + " El servidor esta lleno, Compra VIP para poder entrar cuando este lleno >> §owww.shop.swordcraft.co");
        }
    }
}
