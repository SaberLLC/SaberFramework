package pw.saber.framework.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public class GeneralUtil {
    public static Material[] swords = new Material[]{Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD};

    public static Material[] swords_and_bow = new Material[]{Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD, Material.BOW};

    public static Material[] armor = new Material[]{
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS};

    public static Material[] helmets = new Material[]{Material.LEATHER_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET};

    public static Material[] helmets_and_boots = new Material[]{Material.LEATHER_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET, Material.LEATHER_BOOTS, Material.GOLD_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS};

    public static Material[] chestplates = new Material[]{Material.LEATHER_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE};

    public static Material[] leggings = new Material[]{Material.LEATHER_LEGGINGS, Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS};

    public static Material[] boots = new Material[]{Material.LEATHER_BOOTS, Material.GOLD_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS};

    public static Material[] pickaxe = new Material[]{Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};

    public static Material[] tools = new Material[]{
            Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE, Material.WOOD_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLD_HOE, Material.DIAMOND_HOE,
            Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE, Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE,
            Material.FISHING_ROD};

    public static Material[] axe = new Material[]{Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE};

    public static Material[] weapons = new Material[]{
            Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD, Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE,
            Material.BOW};

    public static Material[] weapons_and_tools = new Material[]{
            Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD, Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE,
            Material.BOW, Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE, Material.WOOD_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLD_HOE,
            Material.DIAMOND_HOE, Material.WOOD_SPADE, Material.STONE_SPADE, Material.IRON_SPADE, Material.GOLD_SPADE, Material.DIAMOND_SPADE, Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE,
            Material.DIAMOND_AXE};

    public static ItemStack getPlayerHead(String p_name) {
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta sm = (SkullMeta) is.getItemMeta();
        sm.setOwner(p_name);
        sm.setDisplayName(ChatColor.WHITE + "Skull of " + p_name);
        is.setItemMeta(sm);
        return is;
    }

    public static boolean isArmor(ItemStack i) {
        for (Material m : helmets) {
            if (i.getType() == m)
                return true;
        }
        for (Material m : chestplates) {
            if (i.getType() == m)
                return true;
        }
        for (Material m : leggings) {
            if (i.getType() == m)
                return true;
        }
        for (Material m : boots) {
            if (i.getType() == m)
                return true;
        }
        return false;
    }

    public static boolean isWeapon(ItemStack i) {
        for (Material m : weapons) {
            if (i.getType() == m)
                return true;
        }
        return false;
    }

    public static boolean isSword(ItemStack i) {
        for (Material m : swords) {
            if (i.getType() == m)
                return true;
        }
        return false;
    }

    public static boolean isTool(ItemStack i) {
        for (Material m : tools) {
            if (i.getType() == m)
                return true;
        }
        return false;
    }

    public static void healPlayer(LivingEntity p, double amount) {
        p.setHealth(Math.min(p.getHealth() + amount, p.getMaxHealth()));
    }

    public static void pushAwayEntity(LivingEntity center, Entity entity, double speed) {
        Vector unitVector = entity.getLocation().toVector().subtract(center.getLocation().toVector()).normalize();
        entity.setVelocity(unitVector.multiply(speed));
    }
}
