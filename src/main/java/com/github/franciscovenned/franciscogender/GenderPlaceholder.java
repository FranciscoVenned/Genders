package com.github.franciscovenned.franciscogender;

import com.github.franciscovenned.franciscogender.playerdata.PlayerData;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GenderPlaceholder extends PlaceholderExpansion {

    // We get an instance of the plugin later.
    private Gender plugin;

    @Override
    public boolean canRegister() {
        return (plugin = (Gender) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String getAuthor() {
        return "FranciscoVenned";
    }

    @Override
    public String getIdentifier() {
        return "Gender";
    }

    @Override
    public String getRequiredPlugin() {
        return "";
    }

    @Override
    public String getVersion() {
        return "2.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        if (player.hasPermission("online.placeholder")) {
            if (identifier.equals("online")) {
                return Integer.toString(Bukkit.getOnlinePlayers().size());
            }
        }

        PlayerData playerData = Gender.getData(player.getUniqueId());

        if (player.hasPermission("playerdata.genero")) {
            if (playerData.getGenero() != null) {
                if (identifier.equals("genero")) {
                    return playerData.getGenero();
                }
            }
        }

        return null;
    }
}
