package pw.saber.framework;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.saber.framework.gui.SaberGUI;
import pw.saber.framework.gui.listener.SaberGUIListener;
import pw.saber.framework.pearl.FenceGatePearl;
import pw.saber.framework.storage.Config;
import pw.saber.framework.storage.Persist;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SaberFramework extends JavaPlugin {

    public static SaberFramework instance;
    private static Logger logger;
    private Persist persist;

    public static void log(String message) {
        logger.log(Level.INFO, message);
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    public static SaberFramework getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        logger = this.getLogger();
        registerListeners();
        getDataFolder().mkdirs();
        persist = new Persist();
        Config.load();
    }

    public void onDisable() {
        instance = null;
        SaberGUI.activeGUIs.values().forEach(SaberGUI::close);
        Config.save();
    }

    public Persist getPersist() {
        return persist;
    }

    private void registerListeners() {
        Listener[] listeners = {
                new SaberGUIListener()
        };

        if (Config.pearlThroughFenceGates) {
            getServer().getPluginManager().registerEvents(new FenceGatePearl(), this);
        }

        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
