package net.suggesting.randomutils.command.api;

import net.suggesting.randomutils.RandomUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Suggesting on 28/09/2017 @ 8:54 AM.
 *
 * @author Suggesting
 */
public abstract class CommandBase implements CommandExecutor {

    private RandomUtils pl; //plugin class
    private String command, permission; //command name and the permission
    private boolean playerOnly = false; //if true, only players can use this command

    /**
     * Creates a new CommandBase.
     * @param command Command name that will be used to execute the command in-game
     */
    public CommandBase(String command) {
        this.pl = RandomUtils.getUtils();
        this.command = command;
        this.permission = "utils.command." + command;
    }

    public RandomUtils getUtils() { return pl; }
    public String getCommand() { return command; }
    public String getPermission() { return permission; }
    public boolean isPlayerOnly() { return playerOnly; }

    public void setPlayerOnly(boolean playerOnly) { this.playerOnly = playerOnly; }
    public void setPermission(String permission) { this.permission = permission; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase(getCommand()))
            run(commandSender, strings);
        return true;
    }

    /**
     * Performs pre-command tasks.
     * @param sender The CommandSender
     * @param args The arguments (these are only used here to pass on to the execute() void)
     */
    private void run(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this.");
            return;
        }
        if (playerOnly && (!(sender instanceof Player))) {
            sender.sendMessage(ChatColor.RED + "Wrong sender");
            return;
        }
        execute(sender, args);
    }

    /**
     * Executes the command.
     * @param sender The CommandSender
     * @param args Command arguments
     */
    protected abstract void execute(CommandSender sender, String[] args);

    /**
     * Registers the command.
     */
    public void register() {
        RandomUtils.getUtils().getCommand(getCommand()).setExecutor(this);
    }

}
