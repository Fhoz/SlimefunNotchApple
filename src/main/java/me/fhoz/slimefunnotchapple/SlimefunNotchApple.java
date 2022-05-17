package me.fhoz.slimefunnotchapple;

import me.fhoz.slimefunnotchapple.listeners.CraftingListener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

public class SlimefunNotchApple extends JavaPlugin implements SlimefunAddon {
    private static SlimefunNotchApple instance;
    @Override
    public void onEnable() {
        instance = this;
        new CraftingListener(this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
