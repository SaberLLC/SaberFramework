package pw.saber.framework.utils;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import pw.saber.framework.SaberFramework;

import java.util.concurrent.TimeUnit;

public class CooldownUtils {

    public static void setCooldown(Player player, String name, int seconds) {
        player.setMetadata(name, new FixedMetadataValue(SaberFramework.getInstance(), (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds))));
    }

    public static boolean isOnCooldown(Player player, String name) {
        if (!player.hasMetadata(name) || player.getMetadata(name).size() <= 0) {
            return false;
        }
        long time = player.getMetadata(name).get(0).asLong();
        return time > System.currentTimeMillis();
    }
}
