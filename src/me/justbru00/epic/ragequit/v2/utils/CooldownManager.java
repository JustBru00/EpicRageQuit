package me.justbru00.epic.ragequit.v2.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.justbru00.epic.ragequit.v2.main.Main;

public class CooldownManager {
	
	public static boolean useCooldown = true;
	public static int cooldownTime = 600;
	
	private static HashMap<UUID, Instant> cooldowns = new HashMap<UUID, Instant>();	

	public static void init() {
		cooldownTime = Main.getInstance().getConfig().getInt("ragequit.cooldown.time");
		useCooldown = Main.getInstance().getConfig().getBoolean("ragequit.cooldown.use");
	}
	
	/**
	 * Returns false if the player is not on cooldown
	 * @param p
	 * @return
	 */
	public static boolean isOnCooldown(Player p) {
		
		if (cooldowns.containsKey(p.getUniqueId())) {
			if (Duration.between(cooldowns.get(p.getUniqueId()), Instant.now()).getSeconds() > cooldownTime) {
				return false;
			} else {
				return true; // Not long enough
			}
		} 
		
		return false;
	}
	
	public static long getCooldown(Player p) {
		return Duration.between(cooldowns.get(p.getUniqueId()), Instant.now()).getSeconds();
	}
	
	public static void putOnCooldown(Player p) {
		cooldowns.put(p.getUniqueId(), Instant.now());
	}
	
}
