package pw.saber.framework.utils;

import org.bukkit.ChatColor;
import pw.saber.framework.SaberFramework;

public class Logger {

    public static void print(String message, PrefixType type) {
        SaberFramework.getInstance().getServer().getConsoleSender().sendMessage(type.getPrefix() + message);
    }

    public enum PrefixType {

        WARNING(ChatColor.RED + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD + "[SaberFramework] "), FAILED(ChatColor.RED + "FAILED: ");

        private String prefix;

        PrefixType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

}
