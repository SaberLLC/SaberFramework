package pw.saber.framework.utils;

import java.util.*;

public class LootTable<T> {
    private final NavigableMap<Double, T> table = new TreeMap<>();
    private final Map<T, Double> lootRates = new HashMap<>();
    Random random = new Random();
    double total = 0.0D;
    private List<T> loot = new ArrayList<>();

    public Map<T, Double> getLootRates() {
        return this.lootRates;
    }

    public List<T> getLoot() {
        return this.loot;
    }

    public void addItem(T loot, double chance) {
        this.loot.add(loot);
        if (chance <= 0.0D)
            return;
        this.total += chance;
        this.table.put(this.total, loot);
        this.lootRates.put(loot, chance);
    }

    public double getTotalWeight() {
        return this.total;
    }

    public T getRandomLoot() {
        double value = this.random.nextDouble() * this.total;
        return this.table.ceilingEntry(value).getValue();
    }
}
