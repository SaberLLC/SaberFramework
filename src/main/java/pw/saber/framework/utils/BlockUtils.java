package pw.saber.framework.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockUtils {
    public static List<Block> getCube(Location loc, Integer radius) {
        List<Block> blocks = new ArrayList<>();
        for (int x = radius * -1 - 1; x <= radius + 1; x++) {
            for (int y = radius * -1; y <= radius; y++) {
                for (int z = radius * -1 - 1; z <= radius + 1; z++) {
                    Block b = loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + y, loc.getBlockZ() + z);
                    if (!b.getType().equals(Material.AIR))
                        blocks.add(b);
                }
            }
        }
        return blocks;
    }

    private static List<Block> getBlocks(Location base, int changeX, int changeY, int changeZ) {
        List<Block> blocks = new ArrayList<>();
        for (int x = base.getBlockX() - changeX; x <= base.getBlockX() + changeX; x++) {
            for (int y = base.getBlockY() - changeY; y <= base.getBlockY() + changeY; y++) {
                for (int z = base.getBlockZ() - changeZ; z <= base.getBlockZ() + changeZ; z++) {
                    Location loc = new Location(base.getWorld(), x, y, z);
                    Block b = loc.getBlock();
                    if (!b.getType().equals(Material.AIR))
                        blocks.add(b);
                }
            }
        }
        return blocks;
    }

    private static List<Block> getBlocksUpDown(Location base, int changeX, int changeY, int changeZ, int depthY) {
        List<Block> blocks = new ArrayList<>();
        boolean depthPos = (depthY >= 0);
        Block baseBlock = base.getBlock();
        for (int i = 0; i < Math.abs(depthY); i++) {
            if (i != 0) {
                baseBlock = baseBlock.getLocation().add(0.0D, depthPos ? 1.0D : -1.0D, 0.0D).getBlock();
            }
            blocks.addAll(getBlocks(baseBlock.getLocation(), changeX, changeY, changeZ));
        }
        return blocks;
    }

    private static List<Block> getBlocksWestEast(Location base, int changeX, int changeY, int changeZ, int depthX) {
        List<Block> blocks = new ArrayList<>();
        boolean depthPos = (depthX >= 0);
        Block baseBlock = base.getBlock();
        for (int i = 0; i < Math.abs(depthX); i++) {
            if (i != 0) {
                baseBlock = baseBlock.getLocation().add(depthPos ? 1.0D : -1.0D, 0.0D, 0.0D).getBlock();
            }
            blocks.addAll(getBlocks(baseBlock.getLocation(), changeX, changeY, changeZ));
        }
        return blocks;
    }

    private static List<Block> getBlocksNorthSouth(Location base, int changeX, int changeY, int changeZ, int depthZ) {
        List<Block> blocks = new ArrayList<>();
        boolean depthPos = (depthZ >= 0);
        Block baseBlock = base.getBlock();
        for (int i = 0; i < Math.abs(depthZ); i++) {
            if (i != 0) {
                baseBlock = baseBlock.getLocation().add(0.0D, 0.0D, depthPos ? 1.0D : -1.0D).getBlock();
            }
            blocks.addAll(getBlocks(baseBlock.getLocation(), changeX, changeY, changeZ));
        }
        return blocks;
    }

    public static List<Block> getSquareRaw(Block b, BlockFace face) {
        List<Block> blocks = new ArrayList<>();
        switch (face) {
            case UP:
            case DOWN:
                blocks.addAll(getBlocks(b.getLocation(), 1, 0, 1));
                break;
            case EAST:
            case WEST:
                blocks.addAll(getBlocks(b.getLocation(), 0, 1, 1));
                break;
            case NORTH:
            case SOUTH:
                blocks.addAll(getBlocks(b.getLocation(), 1, 1, 0));
                break;
        }
        return blocks;
    }

    public static Set<Block> getSquare(Block b, BlockFace face, int level) {
        Set<Block> blocks = new HashSet<>();
        int radius = 1;
        int depth = getDepthByLevel(level);
        if (depth == 0)
            return blocks;
        switch (face) {
            case UP:
                blocks.addAll(getBlocksUpDown(b.getLocation(), radius, 0, radius, -depth));
                break;
            case DOWN:
                blocks.addAll(getBlocksUpDown(b.getLocation(), radius, 0, radius, depth));
                break;
            case EAST:
                blocks.addAll(getBlocksWestEast(b.getLocation(), 0, radius, radius, -depth));
                break;
            case WEST:
                blocks.addAll(getBlocksWestEast(b.getLocation(), 0, radius, radius, depth));
                break;
            case NORTH:
                blocks.addAll(getBlocksNorthSouth(b.getLocation(), radius, radius, 0, depth));
                break;
            case SOUTH:
                blocks.addAll(getBlocksNorthSouth(b.getLocation(), radius, radius, 0, -depth));
                break;
        }
        return blocks;
    }

    private static int getDepthByLevel(int level) {
        int depth = 0;
        if (level == 1 || level == 2)
            if (Math.random() < level * 0.33D) {
                depth = 1;
            } else {
                return 0;
            }
        if (level == 3)
            depth = 1;
        if (level == 4 || level == 5)
            if (Math.random() < (level - 3) * 0.33D) {
                depth = 2;
            } else {
                depth = 1;
            }
        if (level == 6)
            depth = 2;
        if (level == 7 || level == 8)
            if (Math.random() < (level - 6) * 0.33D) {
                depth = 3;
            } else {
                depth = 2;
            }
        if (level == 9)
            depth = 3;
        return depth;
    }
}
