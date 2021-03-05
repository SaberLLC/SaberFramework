package pw.saber.framework.utils;

import org.bukkit.Location;

public class StorableLocation {
    private String worldName;

    private int x;

    private int y;

    private int z;

    public StorableLocation(String worldName, int x, int y, int z) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public StorableLocation(Location location) {
        this.worldName = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }

    public String getWorldName() {
        return this.worldName;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public boolean isLocation(Location location) {
        return (this.worldName.equals(location.getWorld().getName()) && this.x == location.getBlockX() && this.y == location
                .getBlockY() && this.z == location.getBlockZ());
    }
}

