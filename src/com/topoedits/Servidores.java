package com.topoedits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Servidores implements Listener {

    private Inventory inv;
    private ItemStack ANNIHILATION, SKYWARS, TEAMSKYWARS, SURVIVALGAMES;

    public Servidores(Plugin p) {
        inv = Bukkit.getServer().createInventory(null, 27, ChatColor.WHITE + "Selecciona un Juego");

        ANNIHILATION = createItem(ChatColor.RED + "Annihilation", 121);

        SKYWARS = createItem(ChatColor.AQUA + "SkyWars", 261);

        TEAMSKYWARS = createItem(ChatColor.YELLOW + "TeamSkyWars", 296);

        SURVIVALGAMES = createItem(ChatColor.GREEN + "SurvivalGames", 54);

        inv.setItem(3, ANNIHILATION);
        inv.setItem(5, SKYWARS);
        inv.setItem(11, TEAMSKYWARS);
        inv.setItem(13, SURVIVALGAMES);

        Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }

    private ItemStack createItem(String name, Integer Itemid) {
        ItemStack i = new ItemStack(Itemid, 1);
       // ItemStack i = new Wool(dc).toItemStack(1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        //  im.setLore(Arrays.asList("Elige el " + name.toLowerCase()));
        i.setItemMeta(im);
        return i;
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (!event.getInventory().getName().equalsIgnoreCase(inv.getName()))
            return;
        if (event.getCurrentItem().getItemMeta() == null)
            return;

        //Annihilation
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("ANNIHILATION")) {
            event.setCancelled(true);
            player.performCommand("server anni_1");
            event.getWhoClicked().closeInventory();
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("SKYWARS")) {
            event.setCancelled(true);
            player.performCommand("server anni_1");
            event.getWhoClicked().closeInventory();
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("TEAMSKYWARS")) {
            event.setCancelled(true);
            player.performCommand("server anni_1");
            event.getWhoClicked().closeInventory();
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("SURVIVALGAMES")) {
            event.setCancelled(true);
            player.performCommand("server anni_1");
            event.getWhoClicked().closeInventory();
        }
    }
}
