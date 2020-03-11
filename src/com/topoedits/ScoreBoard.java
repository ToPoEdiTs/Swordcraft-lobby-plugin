package com.topoedits;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoard implements Listener {

    private Lobby plugin;
    int sched;
    public ScoreBoard(Lobby lobby){
        this.plugin = lobby;
    }

    @EventHandler
    public void alEntrar(PlayerJoinEvent event){
        final Player p = event.getPlayer();
        this.updateScoreboard(p);
        if(!Bukkit.getScheduler().isCurrentlyRunning(sched)){
        sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
                    updateScoreboard(p);
            }
        }, 20, 20);
        }
    }

    public void updateScoreboard(Player p){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("aaa", "bbb");

        obj.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + p.getDisplayName());

        /*
         *
         *                                      Proyecto Del ScoreBoard cambiando de color:
         *                        | https://bukkit.org/threads/animated-scoreboard-title.334418/ |
         *                                            IMPLEMENTARLO EN LA LINEA 43.
         */

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        PlayerPointsAPI pp = ((PlayerPoints)Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints")).getAPI();
        Score once = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE.toString()  + ChatColor.BOLD + "Puntos"));
        Score diez = obj.getScore(Bukkit.getOfflinePlayer("" + pp.look(p.getDisplayName())));
        Score nueve = obj.getScore(Bukkit.getOfflinePlayer("       "));
        Score ocho = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.RED.toString() + ChatColor.BOLD + "Rango"));
        Score siete = obj.getScore(Bukkit.getOfflinePlayer("Administrador"));
        Score seis = obj.getScore(Bukkit.getOfflinePlayer("    "));
        Score cinco = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA.toString() + ChatColor.BOLD + "Servidor"));
        Score cuatro = obj.getScore(Bukkit.getOfflinePlayer("Lobby 1"));
        Score tres = obj.getScore(Bukkit.getOfflinePlayer("                "));
        Score dos = obj.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Pagina Web"));
        Score uno = obj.getScore(Bukkit.getOfflinePlayer("Swordcraft.co"));

        once.setScore(11);
        diez.setScore(10);
        nueve.setScore(9);
        ocho.setScore(8);
        siete.setScore(7);
        seis.setScore(6);
        cinco.setScore(5);
        cuatro.setScore(4);
        tres.setScore(3);
        dos.setScore(2);
        uno.setScore(1);
            p.setScoreboard(scoreboard);
    }

}
