package net.suggesting.randomutils.command;

import net.suggesting.randomutils.command.api.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Suggesting on 28/09/2017 @ 8:54 AM.
 *
 * @author Suggesting
 */
public class FlyCommand extends CommandBase {

    public FlyCommand() {
        super("fly");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0 && (!(sender instanceof Player))) {
            //we check this manually so console can set other people's flight
            sender.sendMessage(ChatColor.RED + "Wrong sender");
            return;
        }

        Player target = (args.length == 0) ? (Player)sender : Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return;
        }

        target.setAllowFlight(!target.getAllowFlight());
        sender.sendMessage((target.getAllowFlight() ?
                ChatColor.GREEN + "Flight enabled for " + target.getName() + "." :
                ChatColor.RED + "Flight disabled for " + target.getName() + "."));
    }
}
