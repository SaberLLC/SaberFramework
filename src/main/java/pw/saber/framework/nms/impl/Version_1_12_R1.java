package pw.saber.framework.nms.impl;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import pw.saber.framework.nms.NMSManager;

public class Version_1_12_R1 implements NMSManager {

    @Override
    public void setBlock(World world, int x, int y, int z, int id, byte data) {
        if (y > 255) return;

        net.minecraft.server.v1_12_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_12_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        net.minecraft.server.v1_12_R1.BlockPosition bp = new net.minecraft.server.v1_12_R1.BlockPosition(x, y, z);
        int combined = id + (data << 12);
        net.minecraft.server.v1_12_R1.IBlockData ibd = net.minecraft.server.v1_12_R1.Block.getByCombinedId(combined);
        w.setTypeAndData(bp, ibd, 2);

        chunk.a(bp, ibd);
    }

    @Override
    public String getVersion() {
        return "v1_12_R1";
    }

}
