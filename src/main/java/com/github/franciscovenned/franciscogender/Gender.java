package com.github.franciscovenned.franciscogender;

import com.github.franciscovenned.franciscogender.commands.GenderCommand;
import com.github.franciscovenned.franciscogender.playerdata.PlayerData;
import com.github.franciscovenned.franciscogender.playerdata.PlayerDataListener;
import com.github.franciscovenned.franciscogender.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class Gender extends JavaPlugin {

    private final PluginDescriptionFile pdfFile = getDescription();
    private final String version = pdfFile.getVersion();
    private static Gender instance;

    @Override
    public void onEnable() {

        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerDataListener(), this);
        new GenderPlaceholder().register();
        registerConfig();
        registerCommands();
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Gender Core]&f-------------------");
        TextUtils.send(Bukkit.getConsoleSender(), "&fEstado del Plugin: &e&lENABLED");
        TextUtils.send(Bukkit.getConsoleSender(), "&fVersion: &e1.0");

        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Gender Core]&f-------------------");

    }

    @Override
    public void onDisable() {
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Gender Core]&f-------------------");
        TextUtils.send(Bukkit.getConsoleSender(), "&fStatus Plugin: &e&lDisabled ):");
        TextUtils.send(Bukkit.getConsoleSender(), "&fGood bye!");
        TextUtils.send(Bukkit.getConsoleSender(), "&f-------------------------&c[Gender Core]&f-------------------");
    }

    private void registerConfig() {

        File config = new File(getDataFolder(),"config.yml");
        if(!config.exists()) {
            saveDefaultConfig();
        }
    }

    public void saveConfig(){
        try {
            getConfig().save(new File(getDataFolder(), "config.yml")); //Guardo el item en el yml
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        this.getCommand("gender").setExecutor(new GenderCommand());
    }

    public static PlayerData getData(UUID uuid){
        PlayerData playerData = PlayerDataListener.dataMap.get(uuid);

        if(playerData == null) {
            playerData = new PlayerData(uuid);
        }

        return playerData;
    }

    public static Gender getInstance(){
        return instance;
    }
}
