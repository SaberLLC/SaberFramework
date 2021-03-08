package pw.saber.framework;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.saber.framework.gui.SaberGUI;
import pw.saber.framework.gui.listener.SaberGUIListener;
import pw.saber.framework.nms.NMSManager;
import pw.saber.framework.nms.impl.*;
import pw.saber.framework.pearl.FenceGatePearl;
import pw.saber.framework.storage.Config;
import pw.saber.framework.storage.Persist;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SaberFramework extends JavaPlugin {

    public static SaberFramework instance;
    private static Logger logger;
    private Persist persist;
    private NMSManager nmsManager;

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
        this.nmsManager = setupNMS();
    }

    public void onDisable() {
        instance = null;
        SaberGUI.activeGUIs.values().forEach(SaberGUI::close);
        Config.save();
    }

    public NMSManager setupNMS() {
        switch (getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]) {
            case "v1_8_R3": return new Version_1_8_R3();
            case "v1_11_R1": return new Version_1_11_R1();
            case "v1_12_R1": return new Version_1_12_R1();
            case "v1_15_R1": return new Version_1_15_R1();
            case "v1_16_R1": return new Version_1_16_R3();
            default: return null;
        }
    }

    public NMSManager getNmsManager() {
        return nmsManager;
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
