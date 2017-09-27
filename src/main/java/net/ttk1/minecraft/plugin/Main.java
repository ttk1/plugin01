package net.ttk1.minecraft.plugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	private Map<String, Integer> mode;
	public Main(){
		mode = new HashMap<String, Integer>();
	}
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(new Tama(this), this);
        getLogger().info("enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("disable");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("tama")) {
    		if (sender instanceof Player) {
	    		String uid = ((Player)sender).getUniqueId().toString();
	    		if (!mode.containsKey(uid)) {
	    			mode.put(uid, 1);
	    			sender.sendMessage("switched on");
	    		} else if(mode.get(uid) == 0) {
	    			mode.put(uid, 1);
	    			sender.sendMessage("switched on");
	    		} else {
	    			mode.put(uid, 0);
	    			sender.sendMessage("switched off");
	    		}
    		} else {
    			sender.sendMessage("players only command");
    		}
    		return true;
    	} else {
    		return false;
    	}
    }
    
    class Tama implements Listener {
    	private final Main plg;
    	public Tama(Main plg) {
    		this.plg = plg;
    	}
    	@EventHandler
    	public void onPlayerInteractEvent(PlayerInteractEvent event) {
    		String uid = event.getPlayer().getUniqueId().toString();
    		if (plg.mode.get(uid) == 1 && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
    			event.getClickedBlock().setType(Material.DIAMOND_BLOCK);
        		event.getPlayer().sendMessage("This block changed to DIAMOND BLOCK!");
    		}
    	}
    }
}