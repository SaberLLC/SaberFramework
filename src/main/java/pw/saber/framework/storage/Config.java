package pw.saber.framework.storage;

import pw.saber.framework.SaberFramework;

public class Config {

    public static boolean pearlThroughFenceGates = false;
    private static transient Config i = new Config();

    public static void load() {
        SaberFramework.getInstance().getPersist().loadOrSaveDefault(i, Config.class, "config");
    }

    public static void save() {
        SaberFramework.getInstance().getPersist().save(i, "config");
    }
}
