package me.justbru00.epic.ragequit.v2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.justbru00.epic.ragequit.v2.main.Main;
import me.justbru00.epic.ragequit.v2.utils.CooldownManager;
import me.justbru00.epic.ragequit.v2.utils.Messager;

public class RageQuitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("ragequit")) {
			if (sender.hasPermission("epicragequit.ragequit")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (CooldownManager.useCooldown) {
						// Use cooldown
						if (!CooldownManager.isOnCooldown(p)) {
							// kick person with proper messages and put on cooldown
							rageQuit(p, getFormat(p));
							CooldownManager.putOnCooldown(p);
						} else {
							Messager.msgSender(Main.getInstance().getConfig().getString("messages.on_cooldown").replace("{cooldown}", String.valueOf(CooldownManager.getCooldown(p))), sender);
							return true;
						}
					} else {
						// No cooldown						
						// kick person with proper messages
						
						rageQuit(p, getFormat(p));
					}
				} else {
					Messager.msgSender(Main.getInstance().getConfig().getString("messages.console_deny"), sender);
					return true;
				}
			} else {
				Messager.msgSender(Main.getInstance().getConfig().getString("messages.no_permission"), sender);
				return true;
			}
		}
		
		return false;
	}
	
	public static String getFormat(Player p) {
		FileConfiguration config = Main.getInstance().getConfig();
		String format = "NONE";
		int currentPriority = -2000000;
		
		for (String key : config.getConfigurationSection("formats").getKeys(false)) {
			if (p.hasPermission(config.getString("formats." + key + ".permission"))) {
				if (currentPriority < config.getInt("formats." + key + ".priority")) {
					format = key;
					currentPriority = config.getInt("formats." + key + ".priority");
				}
			}
		}
		
		
		return format;
	}
	
	public static void rageQuit(Player p, String formatName) {
		if (formatName.equals("NONE")) {
			if (Main.getInstance().getConfig().getBoolean("ragequit.use_broadcasts")) {
				Messager.sendBC("&c{player} rage quit.".replace("{player}", p.getName()));
			}
			p.kickPlayer(Messager.color("&cYou rage quit.".replace("{player}", p.getName())));
			Messager.msgConsole("&c&l" + p.getName() + " just used /ragequit without any specific format permissions. You should give them the permission of formats.default.permission at least. Otherwise you will see this message everytime.");
			return;
		}	
		
		if (Main.getInstance().getConfig().getBoolean("ragequit.use_broadcasts")) {
			Messager.sendBC(Main.getInstance().getConfig().getString("formats." + formatName + ".broadcast_msg").replace("{player}", p.getName()));
		}
		p.kickPlayer(Messager.color(Main.getInstance().getConfig().getString("formats." + formatName + ".kick_msg").replace("{player}", p.getName())));
	}

}
