package cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RFMhunterbox implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			int r = 20;
			Location startLoc = player.getLocation().subtract(r, r, r);
			int j;
			int k;
			Location loc;
			for (int i = startLoc.getBlockX(); i < startLoc.getBlockX() + r * 2; i++) {
				for (j = startLoc.getBlockY(); j < startLoc.getBlockY() + r * 2; j++) {
					for (k = startLoc.getBlockZ(); k < startLoc.getBlockZ() + r * 2; k++) {
						loc = new Location(startLoc.getWorld(), i, j, k);
						Block block = loc.getBlock();
						if (block.getType() == Material.IRON_DOOR_BLOCK) {
							Material material = loc.subtract(0.0D, 1.0D, 0.0D).getBlock().getType();
							if ((material.equals(Material.IRON_DOOR)) || (material.equals(Material.OBSIDIAN))) {
								block.setType(Material.AIR);
							}
						}
					}
				}
			}
			Bukkit.broadcastMessage(ChatColor.DARK_RED + "[RFM]" + ChatColor.AQUA + " ハンターが放出されました");
		}

		if (sender instanceof ConsoleCommandSender) {
			Player console = Bukkit.getPlayer("UseH4ck");
			int r1 = 20;
			Location startLoc1 = console.getLocation().subtract(r1, r1, r1);
			int j1;
			int k1;
			Location loc1;
			for (int i = startLoc1.getBlockX(); i < startLoc1.getBlockX() + r1 * 2; i++) {
				for (j1 = startLoc1.getBlockY(); j1 < startLoc1.getBlockY() + r1 * 2; j1++) {
					for (k1 = startLoc1.getBlockZ(); k1 < startLoc1.getBlockZ() + r1 * 2; k1++) {
						loc1 = new Location(startLoc1.getWorld(), i, j1, k1);
						Block block = loc1.getBlock();
						if (block.getType() == Material.IRON_DOOR_BLOCK) {
							Material material = loc1.subtract(0.0D, 1.0D, 0.0D).getBlock().getType();
							if ((material.equals(Material.IRON_DOOR)) || (material.equals(Material.OBSIDIAN))) {
								block.setType(Material.AIR);
							}
						}
					}
				}
			}
			Bukkit.broadcastMessage(ChatColor.DARK_RED + "[RFM]" + ChatColor.AQUA + " ハンターが放出されました");
		}

		for (Player players : Bukkit.getOnlinePlayers()) {
			players.playSound(players.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 5.0F, 5.0F);
		}
		return false;
	}
}
