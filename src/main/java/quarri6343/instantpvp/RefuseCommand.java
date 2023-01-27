package quarri6343.instantpvp;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.argument.PlayerArgument;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class RefuseCommand extends Command {
    public RefuseCommand() {
        super("refuseinvitation");

        argument(new PlayerArgument("player"), (player, ctx) -> {
            if(!(ctx.getSender() instanceof Player))
                return;

            Player challenger = player;
            Player opponent = (Player) ctx.getSender();

            challenger.sendMessage(opponent.getName() + "は戦いを拒んだ！");
            opponent.sendMessage(challenger.getName() + "との戦いを拒否した");
        });

        setPermission(PermissionDefault.TRUE);
    }
}