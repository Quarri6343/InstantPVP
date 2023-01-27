package quarri6343.instantpvp;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EventHandler implements Listener {
    
    public EventHandler(){
        InstantPVP.getInstance().getServer().getPluginManager().registerEvents(this, InstantPVP.getInstance());
    }
    
    @org.bukkit.event.EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if(Data.stats != Data.fightStats.FIGHTING)
            return;
        
        if(!(event.getEntity().equals(Data.challenger) || event.getEntity().equals(Data.opponent))){
            return;
        }
        
        event.getDrops().clear();
        
        if(event.getEntity().equals(Data.challenger)){
            Data.challenger.sendMessage("負けてしまった...");
            Data.opponent.sendMessage(Data.challenger.getName() + "に勝った！");
        }
        
        if(event.getEntity().equals(Data.opponent)){
            Data.opponent.sendMessage("負けてしまった...");
            Data.challenger.sendMessage(Data.opponent.getName() + "に勝った！");
        }
        
        Data.stats = Data.fightStats.INACTIVE;
        event.setCancelled(true);
        Data.challenger.getInventory().setContents(Data.challengerInventory);
        Data.opponent.getInventory().setContents(Data.opponentInventory);
        Data.challenger.setHealth(Data.challenger.getMaxHealth());
        Data.opponent.setHealth(Data.opponent.getMaxHealth());
        Data.challenger.setGameMode(Data.challengerGameMode);
        Data.opponent.setGameMode(Data.opponentGameMode);
        
        Data.challenger = null;
        Data.opponent = null;
        Data.challengerInventory = null;
        Data.opponentInventory = null;
    }
    
    @org.bukkit.event.EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        if(event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == 114514)
            event.setCancelled(true);
    }
}
