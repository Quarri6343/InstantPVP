package quarri6343.instantpvp;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.PlayerArgument;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionDefault;

public class AcceptCommand extends Command {
    public AcceptCommand() {
        super("acceptinvitation");

        argument(new PlayerArgument("player"), (player, ctx) -> {
            if(!(ctx.getSender() instanceof Player))
                return;
            
            Data.challenger = player;
            Data.opponent = (Player) ctx.getSender();
            
            Data.challenger.sendMessage(Data.opponent.getName() + "との戦いが始まる！");
            Data.opponent.sendMessage(Data.challenger.getName() + "との戦いが始まる！");
            
            Data.stats = Data.fightStats.FIGHTING;
            
            Data.challengerInventory = Data.challenger.getInventory().getContents().clone();
            Data.opponentInventory = Data.opponent.getInventory().getContents().clone();
            
            Data.challenger.getInventory().clear();
            Data.opponent.getInventory().clear();
            
            ItemStack stone_Sword = new ItemStack(Material.STONE_SWORD);
            ItemMeta swordMeta = stone_Sword.getItemMeta();
            swordMeta.setCustomModelData(114514);
            stone_Sword.setItemMeta(swordMeta);

            ItemStack cooked_beef = new ItemStack(Material.COOKED_BEEF, 64);
            ItemMeta beefMeta = cooked_beef.getItemMeta();
            beefMeta.setCustomModelData(114514);
            stone_Sword.setItemMeta(beefMeta);
            
            Data.challenger.getInventory().addItem(stone_Sword, cooked_beef);
            Data.opponent.getInventory().addItem(stone_Sword, cooked_beef);
            
            Data.challengerGameMode = Data.challenger.getGameMode();
            Data.opponentGameMode = Data.opponent.getGameMode();
            
            Data.challenger.setGameMode(GameMode.SURVIVAL);
            Data.opponent.setGameMode(GameMode.SURVIVAL);
        });

        setPermission(PermissionDefault.TRUE);
    }
}
