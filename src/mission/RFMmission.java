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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import me.confuser.barapi.BarAPI;
import rfm.RFMmain;
import rfm.RFMscoreboard;

public class RFMmission implements CommandExecutor {

	private RFMmain plugin;

	public static String prefix = "§4[RFM]§f ";

	public RFMmission(RFMmain plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mission")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GREEN + "/mission [番号] [ミッション時間]");
				return false;
			}

			if (args.length == 1) {
				sender.sendMessage(ChatColor.GREEN + "/mission [番号] [ミッション時間]");
				return false;
			}

			if (args[0].equals("1")) {
				try {
					List<String> mission1 = plugin.getConfig().getStringList("mission1");
					String content = "";
					for (String str : mission1) {
						content += str + "\n";
					}
					content = ChatColor.translateAlternateColorCodes('&', content);

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setTitle("§e§l§oRunForMoney Mission 1");
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

							new BukkitRunnable() {
								int i = Integer.parseInt(args[1]);

								public void run() {
									if (i > 0) {
										BarAPI.setMessage(players, ChatColor.RED + "Mission 1 発令中！ " + ChatColor.GOLD
												+ "ミッション終了まで " + ChatColor.GREEN + RFMscoreboard.Time(i));
										i--;
									} else {
										BarAPI.removeBar(players);
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0, 20);
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					return false;
				}
			}

			if (args[0].equals("2")) {
				try {
					List<String> mission2 = plugin.getConfig().getStringList("mission2");
					String content = "";
					for (String str : mission2) {
						content += str + "\n";
					}
					content = ChatColor.translateAlternateColorCodes('&', content);

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setTitle("§e§l§oRunForMoney Mission 2");
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

							new BukkitRunnable() {
								int i = Integer.parseInt(args[1]);

								public void run() {
									if (i > 0) {
										BarAPI.setMessage(players, ChatColor.RED + "Mission 2 発令中！ " + ChatColor.GOLD
												+ "ミッション終了まで " + ChatColor.GREEN + RFMscoreboard.Time(i));
										i--;
									} else {
										BarAPI.removeBar(players);
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0, 20);
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					return false;
				}
			}

			if (args[0].equals("3")) {
				try {
					List<String> mission3 = plugin.getConfig().getStringList("mission3");
					String content = "";
					for (String str : mission3) {
						content += str + "\n";
					}
					content = ChatColor.translateAlternateColorCodes('&', content);

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setTitle("§e§l§oRunForMoney Mission 3");
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

							new BukkitRunnable() {
								int i = Integer.parseInt(args[1]);

								public void run() {
									if (i > 0) {
										BarAPI.setMessage(players, ChatColor.RED + "Mission 3 発令中！ " + ChatColor.GOLD
												+ "ミッション終了まで " + ChatColor.GREEN + RFMscoreboard.Time(i));
										i--;
									} else {
										BarAPI.removeBar(players);
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0, 20);
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					return false;
				}
			}

			if (args[0].equals("4")) {
				try {
					List<String> mission4 = plugin.getConfig().getStringList("mission4");
					String content = "";
					for (String str : mission4) {
						content += str + "\n";
					}
					content = ChatColor.translateAlternateColorCodes('&', content);

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setTitle("§e§l§oRunForMoney Mission 4");
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

							new BukkitRunnable() {
								int i = Integer.parseInt(args[1]);

								public void run() {
									if (i > 0) {
										BarAPI.setMessage(players, ChatColor.RED + "Mission 4 発令中！ " + ChatColor.GOLD
												+ "ミッション終了まで " + ChatColor.GREEN + RFMscoreboard.Time(i));
										i--;
									} else {
										BarAPI.removeBar(players);
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0, 20);
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					return false;
				}
			}

			if (args[0].equals("5")) {
				try {
					List<String> mission5 = plugin.getConfig().getStringList("mission5");
					String content = "";
					for (String str : mission5) {
						content += str + "\n";
					}
					content = ChatColor.translateAlternateColorCodes('&', content);

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setTitle("§e§l§oRunForMoney Mission 5");
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

							new BukkitRunnable() {
								int i = Integer.parseInt(args[1]);

								public void run() {
									if (i > 0) {
										BarAPI.setMessage(players, ChatColor.RED + "Mission 5 発令中！ " + ChatColor.GOLD
												+ "ミッション終了まで " + ChatColor.GREEN + RFMscoreboard.Time(i));
										i--;
									} else {
										BarAPI.removeBar(players);
										cancel();
									}
								}
							}.runTaskTimer(plugin, 0, 20);
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					return false;
				}
			}
		}
		return false;
	}
}
