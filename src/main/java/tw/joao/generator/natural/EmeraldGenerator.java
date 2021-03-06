package tw.joao.generator.natural;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EmeraldGenerator extends NaturalGenerator{

    @Getter private final int MAX_IDLE_ITEMS = 3;
    private final int[] upgradeDelay = {300, 600};
    private final int[] generationDelay = {100, 80, 60};

    public EmeraldGenerator(int x, int y, int z) {
        setName("Esmeralda");
        setColor("&a");
        setIcon(Material.EMERALD_BLOCK);
        setItem(new ItemStack(Material.EMERALD));
        setLocation(new Location(Bukkit.getWorld("world"), x, y, z));
        setMaxIdleItems(MAX_IDLE_ITEMS);
        setUpgradeDelay(upgradeDelay);
        setGenerationDelay(generationDelay);
        setMaxLevel(generationDelay.length);

        spawnGenerator();
    }

}
