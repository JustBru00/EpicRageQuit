package me.justbru00.epic.ragequit.v2.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.justbru00.epic.ragequit.v2.commands.RageQuitCommand;
import me.justbru00.epic.ragequit.v2.utils.CooldownManager;
import me.justbru00.epic.ragequit.v2.utils.Messager;

public class Main extends JavaPlugin {

	public static ConsoleCommandSender console = Bukkit.getConsoleSender();
	public static Logger log = Bukkit.getLogger();
	public static String prefix = Messager.color("&8[&bEpic&fRageQuit&8] &c");
	private static Main plugin;

	@Override
	public void onEnable() {
		plugin = this;
		
		Messager.msgConsole("&aStarting Plugin...");
		
		saveDefaultConfig();
		
		prefix = Messager.color(getConfig().getString("messages.prefix"));
		Messager.msgConsole("&aSet prefix...");
		
		CooldownManager.init();
		getCommand("ragequit").setExecutor(new RageQuitCommand());
		
		@SuppressWarnings("unused")
		BStats bstats = new BStats(this);
		
		Messager.msgConsole("&aVersion: " + getDescription().getVersion() + " has been enabled.");
	}
	
	@Override
	public void onDisable() {
		Messager.msgConsole("&aPlugin disabled.");
		plugin = null;
	}
	
	public static Main getInstance() {
		return plugin;
	}
	
}
