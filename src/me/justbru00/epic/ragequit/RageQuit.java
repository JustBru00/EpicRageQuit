package me.justbru00.epic.ragequit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RageQuit extends JavaPlugin {

	public String prefix = color("&8[&bEpic&fRageQuit&8] &c");
	ConsoleCommandSender clogger = this.getServer().getConsoleSender();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("ragequit")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {					
					getServer().broadcastMessage(prefix + ChatColor.WHITE + player.getName() + ChatColor.RED + " rage quit.");
					player.kickPlayer(ChatColor.RED + "You RAGE quit!");
				} else player.sendMessage(prefix + "Please don't put any thing after /ragequit\n" + ChatColor.WHITE + "Usage: /ragequit");						
			} else clogger.sendMessage(prefix + "Silly CONSOLE you can't rage.");
		}
		
		return false;
	}

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {

	}

	
	public String color(String uncolored) {
		String colored = ChatColor.translateAlternateColorCodes('&', uncolored);
		return colored;
	}
}
