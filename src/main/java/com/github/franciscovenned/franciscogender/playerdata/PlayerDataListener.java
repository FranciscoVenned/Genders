package com.github.franciscovenned.franciscogender.playerdata;

import com.github.franciscovenned.franciscogender.Gender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataListener implements Listener {

    public static Map<UUID, PlayerData> dataMap = new HashMap<>();

    @EventHandler
    public void asyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event){

        PlayerData playerData = Gender.getData(event.getUniqueId());

        playerData.load();

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

        PlayerData playerData = Gender.getData(event.getPlayer().getUniqueId());

        playerData.setLastlocation(event.getPlayer().getLocation());

        playerData.save();

    }

    @EventHandler
    public void onDeathQuit(PlayerDeathEvent event){


        Player player = (Player) event.getEntity();

        if(player.getKiller() instanceof Player) {

            FileConfiguration config = Gender.getInstance().getConfig();

            Player killer = player.getKiller();

            PlayerData playerData = Gender.getData(player.getUniqueId());

            playerData.setDeaths(playerData.getDeaths() + 1);

            PlayerData killerdata = Gender.getData(killer.getUniqueId());

            killerdata.setKills(killerdata.getKills() + 1);
        }
    }

    @EventHandler
    void onXPQuit(PlayerExpChangeEvent event){

        Player player = (Player) event.getPlayer();

        PlayerData playerData = Gender.getData(player.getUniqueId());

        playerData.setXp(event.getAmount());
    }

}