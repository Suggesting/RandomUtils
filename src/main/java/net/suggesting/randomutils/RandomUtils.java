package net.suggesting.randomutils;

import net.suggesting.randomutils.command.FlyCommand;
import net.suggesting.randomutils.command.HealCommand;
import net.suggesting.randomutils.command.api.CommandBase;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Suggesting on 28/09/2017 @ 8:53 AM.
 *
 * @author Suggesting
 */
public final class RandomUtils extends JavaPlugin {

    private static RandomUtils utils;

    private final List<CommandBase> commands = Arrays.asList(
            new FlyCommand(), new HealCommand()
    );

    @Override
    public void onEnable() {
        utils = this;

        for (CommandBase c : commands)
            c.register();

    }

    @Override
    public void onDisable() {}

    public static RandomUtils getUtils() { return utils; }

}
