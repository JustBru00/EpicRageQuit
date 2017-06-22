package me.justbru00.epic.ragequit.v2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
						if (CooldownManager.isOnCooldown(p)) {
							// kick person with proper messages and put on cooldown
						} else {
							Messager.msgSender(Main.getInstance().getConfig().getString("messages.on_cooldown").replace("{cooldown}", String.valueOf(CooldownManager.getCooldown(p))), sender);
							return true;
						}
					} else {
						// No cooldown
						
						// kick person with proper messages
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

}
