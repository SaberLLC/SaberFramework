package pw.saber.framework.nms;

import org.bukkit.World;

public interface NMSManager {

    void setBlock(World world, int x, int y, int z, int id, byte data);

    String getVersion();

}
