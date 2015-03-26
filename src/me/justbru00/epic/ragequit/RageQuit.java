/**
  EpicRageQuit adds /ragequit which kicks you and broadcasts a message.
  
  @author Justin Brubaker
  
    Copyright (C) 2015  Justin Brubaker

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/   
package me.justbru00.epic.ragequit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
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
		clogger.sendMessage(prefix + ChatColor.RED + "Has Been Disabled.");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();

		clogger.sendMessage(prefix + ChatColor.GOLD + "Version: "
				+ pdfFile.getVersion() + " Has Been Enabled.");
	}

	
	public String color(String uncolored) {
		String colored = ChatColor.translateAlternateColorCodes('&', uncolored);
		return colored;
	}
}
