package pw.saber.framework.math;

import org.bukkit.Chunk;

public class LongHash {
    public static long toLong(int msw, int lsw) {
        return (msw) + lsw - -2147483648L;
    }

    public static int msw(long l) {
        return (int) (l >> 32L);
    }

    public static int lsw(long l) {
        return (int) l + Integer.MIN_VALUE;
    }

    public static Long createLongHash(int x, int z) {
        return (x) + z - -2147483648L;
    }

    public static int getXFromLongHash(long val) {
        return msw(val);
    }

    public static int getZFromLongHash(long val) {
        return lsw(val);
    }

    public static boolean isEqualToChunk(long val, Chunk chunk) {
        return (val == toLong(chunk.getX(), chunk.getZ()));
    }
}

