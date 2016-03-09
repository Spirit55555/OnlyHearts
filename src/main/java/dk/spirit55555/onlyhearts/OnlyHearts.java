package dk.spirit55555.onlyhearts;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnlyHearts extends JavaPlugin implements Listener {
	static final String HEART = "❤";
	static final String HEART_SIGN = "<3";
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("onlyheartsreload"))
			return false;
		
		if (sender.hasPermission("onlyhearts.reload")) {
			reloadConfig();
			
			sender.sendMessage(ChatColor.RED + "Config has been reloaded");
			
			return true;
		}
		
		return false;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (!getConfig().getBoolean("chat", true))
			return;
		
		if (!event.getPlayer().hasPermission("onlyhearts.chat"))
			return;
			
		String message = event.getMessage();
		
		message = message.replaceAll(HEART_SIGN, HEART);
		
		event.setMessage(message);
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (!getConfig().getBoolean("signs", true))
			return;
		
		if (!event.getPlayer().hasPermission("onlyhearts.signs"))
			return;
		
		for (int x = 0; x <= 3; x++) {
			String line = event.getLine(x);
			
			line = line.replaceAll(HEART_SIGN, HEART);
			
			event.setLine(x, line);
		}
	}
	
	@EventHandler
	public void onPreCommand(PlayerCommandPreprocessEvent event) {
		if (!getConfig().getBoolean("commands", true))
			return;
		
		if (!event.getPlayer().hasPermission("onlyhearts.commands"))
			return;
		
		String message = event.getMessage();
		
		message = message.replaceAll(HEART_SIGN, HEART);
		
		event.setMessage(message);
	}
}
