package pw.saber.framework.utils;

import java.util.concurrent.ThreadLocalRandom;

public enum EquipmentType {
    HELMET(0),
    CHESTPLATE(1),
    LEGGINGS(2),
    BOOTS(3),
    WEAPON(-1);

    private int index;

    EquipmentType(int index) {
        this.index = index;
    }

    public static EquipmentType getRandomArmor() {
        return values()[ThreadLocalRandom.current().nextInt(4)];
    }

    public int getIndex() {
        return this.index;
    }
}
