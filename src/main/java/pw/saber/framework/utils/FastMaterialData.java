package pw.saber.framework.utils;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class FastMaterialData {
    private MaterialData original;

    private Material material;

    private short data;

    public FastMaterialData(MaterialData data) {
        this.material = data.getItemType();
        this.data = data.getData();
    }

    public MaterialData getOriginal() {
        return this.original;
    }

    public Material getMaterial() {
        return this.material;
    }

    public short getData() {
        return this.data;
    }
}
