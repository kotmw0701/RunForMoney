package mission;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scoreboard.Team;

import rfm.RFMmain;
import rfm.RFMscoreboard;

public class RFMnotification implements CommandExecutor {

	private RFMmain plugin;

	public static String prefix = "§4[RFM]§f ";

	public RFMnotification(RFMmain plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("notification")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GREEN + "/notification [番号]");
				return false;
			}

			if (args.length == 1) {
				sender.sendMessage(ChatColor.GREEN + "/notification [番号]");
				return false;
			}

			if (args[0].equals("1")) {
				List<String> notification1 = plugin.getConfig().getStringList("notification1");
				String content = "";
				for (String str : notification1) {
					content += str + "\n";
				}
				content = ChatColor.translateAlternateColorCodes('&', content);

				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta meta = (BookMeta) book.getItemMeta();
				meta.setTitle("§e§l§oRunForMoney Notification 1");
				meta.setPages(content);
				book.setItemMeta(meta);

				Team team = RFMscoreboard.sb.getTeam("team_admin");
				Team team1 = RFMscoreboard.sb.getTeam("team_report");
				Team team2 = RFMscoreboard.sb.getTeam("team_flee");
				Team team3 = RFMscoreboard.sb.getTeam("team_jail");

				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					if ((team.hasEntry(players.getName()) || (team1.hasEntry(players.getName())
							|| (team2.hasEntry(players.getName()) || (team3.hasEntry(players.getName())))))) {
						players.getInventory().addItem(book);
						players.sendMessage("§e通達が届いた。");
						players.getPlayer().getWorld().playSound(players.getPlayer().getLocation(),
								Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					}
				}
			}

			if (args[0].equals("2")) {
				List<String> notification2 = plugin.getConfig().getStringList("notification2");
				String content = "";
				for (String str : notification2) {
					content += str + "\n";
				}
				content = ChatColor.translateAlternateColorCodes('&', content);

				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta meta = (BookMeta) book.getItemMeta();
				meta.setTitle("§e§l§oRunForMoney Notification 2");
				meta.setPages(content);
				book.setItemMeta(meta);

				Team team = RFMscoreboard.sb.getTeam("team_admin");
				Team team1 = RFMscoreboard.sb.getTeam("team_report");
				Team team2 = RFMscoreboard.sb.getTeam("team_flee");
				Team team3 = RFMscoreboard.sb.getTeam("team_jail");

				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					if ((team.hasEntry(players.getName()) || (team1.hasEntry(players.getName())
							|| (team2.hasEntry(players.getName()) || (team3.hasEntry(players.getName())))))) {
						players.getInventory().addItem(book);
						players.sendMessage("§e通達が届いた。");
						players.getPlayer().getWorld().playSound(players.getPlayer().getLocation(),
								Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					}
				}
			}
		}
		return false;
	}
}
