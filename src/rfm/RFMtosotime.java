package rfm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RFMtosotime extends BukkitRunnable {

	private static RFMmain plugin;

	int tosotime;
	int endtime;
	int tosotime2;
	int i;

	public RFMtosotime(int i2, int i3) {
		tosotime = i2;
		endtime = i3;
		tosotime2 = tosotime;
		i = this.tosotime2 + 30;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		RFMscoreboard.TosoChangeScoreboard(i);
		RFMmain.time = i;

		if (i == tosotime2 + 30) {
			RFMutil.HunterTp();

			Bukkit.broadcastMessage(RFMmain.prefix + "逃走者をエリアに解放しました");
			Bukkit.broadcastMessage(RFMmain.prefix + "逃走者に逃走中のルールを提示しました");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fwarn");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "hwarn");
		}

		if (i == tosotime2 + 15) {
			RFMutil.SetInventory();
			Bukkit.broadcastMessage(RFMmain.prefix + "逃走者に装備を配布しました");
		}

		if ((i <= tosotime2 + 10) && (i >= tosotime2 + 1)) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.playSound(players.getLocation(), Sound.UI_BUTTON_CLICK, 100, 1);
				players.sendTitle((i - tosotime2) + "", "");
			}
		}

		if (i == tosotime2) {
			Bukkit.broadcastMessage("§b>>>>>>>>>>>>>>>>");
			Bukkit.broadcastMessage("§b>§cRFM Start!§b<");
			Bukkit.broadcastMessage("§b<<<<<<<<<<<<<<<<");

			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "bo");

			RFMmain.game = true;

			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendTitle("§bRFM Start!", "");
			}
		}

		if (i == tosotime2 + 11) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendTitle("§4逃走中開始まで", "");
			}
		}

		if ((i == 3000) || (i == 2700) || (i == 2400) || (i == 2100) || (i == 1800) || (i == 1500) || (i == 1200)
				|| (i == 900) || (i == 600) || (i == 300) || (i == 240) || (i == 180) || (i == 120) || (i == 60)) {
			Bukkit.broadcastMessage(RFMmain.prefix + "終了まで残り" + ChatColor.AQUA + i / 60 + ChatColor.RESET + "分§R");
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.playSound(players.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 100, 1);
			}
		}

		if (i == endtime) {
			RFMmain.relese = false;
			Bukkit.broadcastMessage(RFMmain.prefix + ChatColor.YELLOW + "復活システムを停止しました");
		}

		if (i == 11) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.sendTitle("§4逃走中終了まで", "");
			}
		}

		if ((i <= 10) && (i >= 1)) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.playSound(players.getLocation(), Sound.UI_BUTTON_CLICK, 100, 1);
				players.sendTitle("§e" + i + "", "");
			}
		}

		if (this.i == 0) {
			RFMmain.game = false;
			int size = RFMscore.TeamSize("team_survivor");
			if (size == 0) {
				Bukkit.broadcastMessage("§b>>>>>>>>>>>>>>>>>");
				Bukkit.broadcastMessage("§b>§cRFM End!§b<");
				Bukkit.broadcastMessage("§b>§aHunter Win!§b<");
				Bukkit.broadcastMessage("§b<<<<<<<<<<<<<<<<<");
				cancel();

				Bukkit.getScheduler().cancelTasks(plugin);

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 1.0F);
					players.sendTitle("§bHunter Win!", "");
					if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
						RFMmain.loadLocation();
						String world = RFMmain.LocationStatsYaml.getString("Resurrection.world");
						double x = RFMmain.LocationStatsYaml.getDouble("Resurrection.x");
						double y = RFMmain.LocationStatsYaml.getDouble("Resurrection.y");
						double z = RFMmain.LocationStatsYaml.getDouble("Resurrection.z");
						float pi = (float) RFMmain.LocationStatsYaml.getDouble("Resurrection.pitch");
						float ya = (float) RFMmain.LocationStatsYaml.getDouble("Resurrection.yaw");

						Location loc = new Location(plugin.getServer().getWorld(world), x, y, z);
						loc.setPitch(pi);
						loc.setYaw(ya);
						players.teleport(loc);
						players.getInventory().clear();
						players.updateInventory();
					}
				}
			} else {
				Bukkit.broadcastMessage("§b>>>>>>>>>>>>>>>");
				Bukkit.broadcastMessage("§b>§cRFM End!§b<");
				Bukkit.broadcastMessage("§b>§aFlee Win!!§b<");
				Bukkit.broadcastMessage("§b<<<<<<<<<<<<<<<");
				cancel();

				Bukkit.getScheduler().cancelTasks(plugin);

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
					players.sendTitle("§bFlee Win!", "");
					if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_survivor")) {
						RFMmain.loadLocation();
						String world = RFMmain.LocationStatsYaml.getString("Resurrection.world");
						double x = RFMmain.LocationStatsYaml.getDouble("Resurrection.x");
						double y = RFMmain.LocationStatsYaml.getDouble("Resurrection.y");
						double z = RFMmain.LocationStatsYaml.getDouble("Resurrection.z");
						float pi = (float) RFMmain.LocationStatsYaml.getDouble("Resurrection.pitch");
						float ya = (float) RFMmain.LocationStatsYaml.getDouble("Resurrection.yaw");

						Location loc = new Location(plugin.getServer().getWorld(world), x, y, z);
						loc.setPitch(pi);
						loc.setYaw(ya);
						players.teleport(loc);
						players.getInventory().clear();
						players.updateInventory();
					}
				}
			}
		}
		i -= 1;
	}
}