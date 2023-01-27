package quarri6343.instantpvp;

import net.kunmc.lab.commandlib.CommandLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class InstantPVP extends JavaPlugin {

    /**
     * シングルトンで管理されているこのクラスのインスタンス
     */
    private static InstantPVP instance;

    public InstantPVP() {
        instance = this;
    }
    
    public static InstantPVP getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CommandLib.register(this, new FightCommand(), new AcceptCommand(), new RefuseCommand(), new AbortCommand());
        new EventHandler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
