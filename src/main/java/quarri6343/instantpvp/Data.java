package quarri6343.instantpvp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Data {
    public static fightStats stats = fightStats.INACTIVE;
    public static Player challenger;
    public static Player opponent;
    public static ItemStack[] challengerInventory;
    public static ItemStack[] opponentInventory;
    public static GameMode challengerGameMode;
    public static GameMode opponentGameMode;
    
    public enum fightStats{
        INACTIVE,
        INVITING,
        FIGHTING
    }
}
