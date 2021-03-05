package pw.saber.framework.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class InventoryUtils {
    public static int fitSize(int size) {
        return (size <= 9) ? 9 : ((size <= 18) ? 18 : ((size <= 27) ? 27 : ((size <= 36) ? 36 : ((size <= 45) ? 45 : ((size <= 54) ? 54 : 54)))));
    }

    public static boolean hasSpaceforMaterial(Inventory inventory, MaterialData data, int count) {
        return hasSpaceforMaterial(inventory, new FastMaterialData(data), count);
    }

    public static boolean hasSpaceforMaterial(Inventory inventory, FastMaterialData data, int count) {
        int neededToAdd = count;
        for (int i = 0; i < inventory.getSize(); i++) {
            if (neededToAdd <= 0)
                return true;
            ItemStack item = inventory.getItem(i);
            Material type = null;
            if (item == null || (type = item.getType()) == Material.AIR) {
                neededToAdd -= (type == null) ? data.getMaterial().getMaxStackSize() : type.getMaxStackSize();
            } else if (type == data.getMaterial()) {
                neededToAdd -= data.getMaterial().getMaxStackSize() - item.getAmount();
            }
        }
        return (neededToAdd <= 0);
    }
}