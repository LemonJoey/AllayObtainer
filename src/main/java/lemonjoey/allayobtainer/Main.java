package lemonjoey.allayobtainer;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static Main plugin;

    public void onEnable() {
        plugin = this;
        getLogger().info("AllayObtainer successfully started");
        getServer().getPluginManager().registerEvents(new Events(), this);

    }

    public void onDisable() {
        getLogger().info("AllayObtainer successfully shutdown");
    }
}
