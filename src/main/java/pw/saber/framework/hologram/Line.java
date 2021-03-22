package pw.saber.framework.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import pw.saber.framework.utils.CC;

public class Line {

    private Line lineInstance;
    private Hologram hologram;
    private Location location;
    private String text;
    private ArmorStand armorStand;


    public Line(Hologram hologram, Location location, String text) {
        this.hologram = hologram;
        this.location = location;
        this.text = text;

        ArmorStand armorStand = getLocation().getWorld().spawn(getLocation(), ArmorStand.class);//spawn the hologram
        armorStand.setVisible(false);//invisible
        armorStand.setGravity(false);
        //armorStand.setInvulnerable(true); // damage
        if (text.equalsIgnoreCase("")) {
            armorStand.setCustomName(CC.translate("&1"));
        } else {
            armorStand.setCustomName(CC.translate(getText()));
        }
        armorStand.setCustomNameVisible(true);
        this.armorStand = armorStand;

        lineInstance = this;
    }

    public void rename(String text) {
        setText(text);
        getArmorStand().setCustomName(CC.translate(text));
        getArmorStand().setCustomNameVisible(true);
    }

    public void teleport(Location location) {
        getArmorStand().teleport(location);
        this.location = location;
    }

    public void show(boolean show) {
        getArmorStand().setCustomNameVisible(show);
    }

    public void removeFromHologram() {
        if (getArmorStand() != null) {
            getArmorStand().remove();//remove the entity
        }
        setArmorStand(null);
        //setText(null);
        setLocation(null);
        setHologram(null);
        lineInstance = null;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void setArmorStand(ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public Line getLineInstance() {
        return lineInstance;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if (text == null) {
            getArmorStand().setCustomName(null);
        } else {
            getArmorStand().setCustomName(CC.translate(getText()));
        }
        getArmorStand().setCustomNameVisible(true);
    }

    public Hologram getHologram() {
        return hologram;
    }

    public void setHologram(Hologram hologram) {
        this.hologram = hologram;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
