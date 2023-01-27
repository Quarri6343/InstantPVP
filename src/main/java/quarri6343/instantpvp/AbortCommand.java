package quarri6343.instantpvp;

import net.kunmc.lab.commandlib.Command;

public class AbortCommand extends Command {

    public AbortCommand() {
        super("abort");

        execute(ctx -> {
            
            if(Data.stats != Data.fightStats.FIGHTING){
                ctx.getSender().sendMessage("戦闘中ではありません");
                return;
            }

            Data.stats = Data.fightStats.INACTIVE;
            Data.challenger.getInventory().setContents(Data.challengerInventory);
            Data.opponent.getInventory().setContents(Data.opponentInventory);
            Data.challenger.setGameMode(Data.challengerGameMode);
            Data.opponent.setGameMode(Data.opponentGameMode);
            Data.challenger = null;
            Data.opponent = null;
            Data.challengerInventory = null;
            Data.opponentInventory = null;
            
            ctx.getSender().sendMessage("戦闘を強制終了しました");
        });
    }
}
