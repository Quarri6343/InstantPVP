package quarri6343.instantpvp;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.PlayerArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.scheduler.BukkitRunnable;

public class FightCommand extends Command {
    public FightCommand() {
        super("fight");

        argument(new PlayerArgument("player"), (player, ctx) -> {
            if(Data.stats != Data.fightStats.INACTIVE){
                ctx.getSender().sendMessage("他のプレイヤーのセッションが進行中です");
                return;
            }
            
            if(!(ctx.getSender() instanceof Player))
                return;
            
            if(ctx.getSender().equals(player)){
                ctx.getSender().sendMessage("己との戦いは他所でやってください");
                return;
            }
            
            player.sendMessage(Component.text(ctx.getSender().getName() + "から" + "勝負を申し込まれた！\n")
                    .append(Component.text("[承諾]").color(NamedTextColor.GREEN).clickEvent(ClickEvent.runCommand("/acceptinvitation " + ctx.getSender().getName())))
                    .append(Component.text(" "))
                    .append(Component.text("[拒否]").color(NamedTextColor.RED).clickEvent(ClickEvent.runCommand("/refuseinvitation　"  + ctx.getSender().getName()))));
            ctx.getSender().sendMessage(player.getName() + "に勝負を申し込んだ");
            
            Data.stats = Data.fightStats.INVITING;
            new BukkitRunnable(){
                @Override
                public void run() {
                    if(Data.stats != Data.fightStats.FIGHTING){
                        Data.stats = Data.fightStats.INACTIVE;
                        player.sendMessage("招待の有効期限が切れました");
                    }
                }
            }.runTaskLater(InstantPVP.getInstance(), 100);
        });
        
        setPermission(PermissionDefault.TRUE);
    }
}
