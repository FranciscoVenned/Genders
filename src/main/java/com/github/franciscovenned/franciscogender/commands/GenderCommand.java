package com.github.franciscovenned.franciscogender.commands;

import com.github.franciscovenned.franciscogender.Gender;
import com.github.franciscovenned.franciscogender.playerdata.PlayerData;
import com.github.franciscovenned.franciscogender.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")){
            TextUtils.send(sender, "&f[Gender Help]");
            TextUtils.send(sender, "&7/Gender <genero>");
            TextUtils.send(sender, "&f&m----------------------------------");

            return true;
        }
        else if (sender instanceof Player){
            Player player = (Player) sender;
            PlayerData playerData = Gender.getData(player.getUniqueId());
            if(args.length == 0){
                TextUtils.send(sender, "&c&l Debes usar correctamente el comando");
                TextUtils.send(sender, "&cPrueba usando /gender <genero>");
            }

            if (!Gender.getInstance().getConfig().getStringList("GENERO-LIST").contains(args[0])){
                    TextUtils.send(player, "&cGenero invalido");
                    return true;
            }

            playerData.setGenero(args[0]);
            playerData.save();
            TextUtils.send(player, "Genero cambiado");
        }
        return false;
    }
}
