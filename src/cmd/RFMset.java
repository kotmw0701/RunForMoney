package cmd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rfm.RFMmain;
import rfm.RFMscore;
import rfm.RFMscoreboard;
import rfm.RFMtosotime;
import rfm.RFMutil;

public class RFMset implements CommandExecutor {

	private static RFMmain plugin;

	public RFMset(RFMmain plugin) {
		RFMset.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length == 0) {
			player.sendMessage("§a----[RunForMoney Teleport Setting]----");
			player.sendMessage("§a/rfm set fs §7- §b逃走者のスポーン地点を設定");
			player.sendMessage("§a/rfm set hs §7- §bハンターのスポーン地点を設定");
			player.sendMessage("§a/rfm set js §7- §b牢獄地点を設定");
			player.sendMessage("§a/rfm set rs §7- §bクリア時の地点を設定");
			player.sendMessage("§a/rfm set rj §7- §b牢獄から復活地点の設定");
			player.sendMessage("");
			player.sendMessage("§a/rfm setting §7- §bMain(Flee,Hunter,Jail,Resurrection,RandomSpawns)の座標アイテムでの設定");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney Start&End&Reset]----");
			player.sendMessage("§a/rfm start §7- §b逃走中の開始");
			player.sendMessage("§a/rfm end §7- §b逃走中の終了");
			player.sendMessage("§a/rfm reset §7- §b逃走中のリセット");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney Check]----");
			player.sendMessage("§a/rfm check §7- §bMain(Flee,Hunter,Jail,Resurrection,ReJail)の座標の確認");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney RandomHunter]----");
			player.sendMessage("§a/rfm rf §7- §bランダムで逃走者から一人、ハンターを選びます。");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney GiveItem&RemoveItem]----");
			player.sendMessage("§a/rfm gib <amount> §7- §b逃走者全員に、骨を<amount>で指定した数、追加します。");
			player.sendMessage("§a/rfm gie <amount> §7- §b逃走者全員に、卵を<amount>で指定した数、追加します。");
			player.sendMessage("§a/rfm gif <amount> §7- §b逃走者全員に、羽を<amount>で指定した数、追加します。");
			player.sendMessage("");
			player.sendMessage("§a/rfm rib <amount> §7- §b逃走者全員に、骨を<amount>で指定した数、削除します。");
			player.sendMessage("§a/rfm rie <amount> §7- §b逃走者全員に、卵を<amount>で指定した数、削除します。");
			player.sendMessage("§a/rfm rif <amount> §7- §b逃走者全員に、羽を<amount>で指定した数、削除します。");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney Team]----");
			player.sendMessage("§a/admin <player> §7- §b運営になります。");
			player.sendMessage("§a/report <player> §7- §b通報部隊になります。");
			player.sendMessage("§a/flee <player> §7- §b逃走者になります。");
			player.sendMessage("§a/hunter <player> §7- §bハンターになります。");
			player.sendMessage("§a/jail <player> §7- §b牢獄者になります。(テスト時のみ)");
			player.sendMessage("§a/survivor <player> §7- §b生存者になります。");
			player.sendMessage("§a/aj <player> §7- §b全プレイヤーを逃走者にします。");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney Entry]----");
			player.sendMessage("§a/startentry <number> §7- §bハンターを<number>で指定した数、募集します。");
			player.sendMessage("§a/endentry §7- §bハンターの募集を締め切ります。");
			player.sendMessage("§a/check §7- §bハンターをエントリーしている数を確認します。");
			player.sendMessage("§a/add §7- §bハンターをエントリーします。");
			player.sendMessage("§a/cancel §7- §bハンターのエントリーを取り消します。");
			player.sendMessage("");
			player.sendMessage("§a----[RunForMoney Mission Set]----");
			player.sendMessage("§a/bf 1 §7- §bボタンを探せミッションのボタン①の省略コマンド");
			player.sendMessage("§a/bf 2 §7- §bボタンを探せミッションのボタン②の省略コマンド");
			player.sendMessage("§a/bf 3 §7- §bボタンを探せミッションのボタン③の省略コマンド");
			player.sendMessage("");
			player.sendMessage("§a/ss §7- §b生存ミッションのボタンの省略コマンド");
			player.sendMessage("");
			return true;
		} else {
			if (args[0].equalsIgnoreCase("set") && player.hasPermission("rfm.setting")) {
				if (args[1].equalsIgnoreCase("fs")) {
					String world = player.getLocation().getWorld().getName();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					float pi = player.getLocation().getPitch();
					float ya = player.getLocation().getYaw();

					RFMmain.loadLocation();
					RFMmain.LocationStatsYaml.set("Flee.world", world);
					RFMmain.LocationStatsYaml.set("Flee.x", x);
					RFMmain.LocationStatsYaml.set("Flee.y", y);
					RFMmain.LocationStatsYaml.set("Flee.z", z);
					RFMmain.LocationStatsYaml.set("Flee.pitch", pi);
					RFMmain.LocationStatsYaml.set("Flee.yaw", ya);
					RFMmain.saveLocation();
					player.sendMessage("FleeSet:§6 " + x + "§7,§6 " + y + "§7,§6 " + z + "§7,§6 " + pi + "§7,§6 " + ya
							+ "§7,§6 " + world);
					return true;
				}

				if (args[1].equalsIgnoreCase("hs")) {
					String world = player.getLocation().getWorld().getName();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					float pi = player.getLocation().getPitch();
					float ya = player.getLocation().getYaw();

					RFMmain.loadLocation();
					RFMmain.LocationStatsYaml.set("Hunter.world", world);
					RFMmain.LocationStatsYaml.set("Hunter.x", x);
					RFMmain.LocationStatsYaml.set("Hunter.y", y);
					RFMmain.LocationStatsYaml.set("Hunter.z", z);
					RFMmain.LocationStatsYaml.set("Hunter.pitch", pi);
					RFMmain.LocationStatsYaml.set("Hunter.yaw", ya);
					RFMmain.saveLocation();
					player.sendMessage("HunterSet:§6 " + x + "§7,§6 " + y + "§7,§6 " + z + "§7,§6 " + pi + "§7,§6 " + ya
							+ "§7,§6 " + world);
					return true;
				}

				if (args[1].equalsIgnoreCase("js")) {
					String world = player.getLocation().getWorld().getName();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					float pi = player.getLocation().getPitch();
					float ya = player.getLocation().getYaw();

					RFMmain.loadLocation();
					RFMmain.LocationStatsYaml.set("Jail.world", world);
					RFMmain.LocationStatsYaml.set("Jail.x", x);
					RFMmain.LocationStatsYaml.set("Jail.y", y);
					RFMmain.LocationStatsYaml.set("Jail.z", z);
					RFMmain.LocationStatsYaml.set("Jail.pitch", pi);
					RFMmain.LocationStatsYaml.set("Jail.yaw", ya);
					RFMmain.saveLocation();
					player.sendMessage("JailSet:§6 " + x + "§7,§6 " + y + "§7,§6 " + z + "§7,§6 " + pi + "§7,§6 " + ya
							+ "§7,§6 " + world);
					return true;
				}

				if (args[1].equalsIgnoreCase("rs")) {
					String world = player.getLocation().getWorld().getName();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					float pi = player.getLocation().getPitch();
					float ya = player.getLocation().getYaw();

					RFMmain.loadLocation();
					RFMmain.LocationStatsYaml.set("Resurrection.world", world);
					RFMmain.LocationStatsYaml.set("Resurrection.x", x);
					RFMmain.LocationStatsYaml.set("Resurrection.y", y);
					RFMmain.LocationStatsYaml.set("Resurrection.z", z);
					RFMmain.LocationStatsYaml.set("Resurrection.pitch", pi);
					RFMmain.LocationStatsYaml.set("Resurrection.yaw", ya);
					RFMmain.saveLocation();
					player.sendMessage("ResurrectionSet:§6 " + x + "§7,§6 " + y + "§7,§6 " + z + "§7,§6 " + pi
							+ "§7,§6 " + ya + "§7,§6 " + world);
					return true;
				}

				if (args[1].equalsIgnoreCase("rj")) {
					if (sender instanceof Player) {
						YamlConfiguration yml = RFMmain.LocationStatsYaml;
						ArrayList<String> list = new ArrayList<String>();

						String world = player.getWorld().getName();
						int x = (int) player.getLocation().getBlockX();
						int y = (int) player.getLocation().getBlockY() + 5;
						int z = (int) player.getLocation().getBlockZ();
						String loc = world + "," + x + "," + y + "," + z;
						list.add(loc);
						for (String str : yml.getStringList("RandomSpawns")) {
							list.add(str);
						}
						yml.set("RandomSpawns", list);
						try {
							yml.save(new File(RFMmain.LocationStatsFilePath));
							player.sendMessage("RandomSpawns:§6 " + x + "§7,§6 " + y + "§7,§6 " + z + "§7,§6 " + world);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

			if (args[0].equalsIgnoreCase("gib") && (player.hasPermission("rfm.gi"))) {
				try {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							int amount = Integer.parseInt(args[1]);
							players.getInventory().addItem(new ItemStack(Material.BONE, amount));
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				}
			}

			if (args[0].equalsIgnoreCase("gie") && (player.hasPermission("rfm.gi"))) {
				try {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							int amount = Integer.parseInt(args[1]);
							players.getInventory().addItem(new ItemStack(Material.EGG, amount));
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				}
			}

			if (args[0].equalsIgnoreCase("gif") && (player.hasPermission("rfm.gi"))) {
				try {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							int amount = Integer.parseInt(args[1]);
							players.getInventory().addItem(new ItemStack(Material.FEATHER, amount));
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				}
			}

			if (args[0].equalsIgnoreCase("rib") && (player.hasPermission("rfm.ri"))) {
				try {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							int amount = Integer.parseInt(args[1]);
							players.getInventory().removeItem(new ItemStack(Material.BONE, amount));
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				}
			}

			if (args[0].equalsIgnoreCase("rie") && (player.hasPermission("rfm.ri"))) {
				try {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							int amount = Integer.parseInt(args[1]);
							players.getInventory().removeItem(new ItemStack(Material.EGG, amount));
						}
					}
				} catch (NumberFormatException ex) {
					return false;
				}

				if (args[0].equalsIgnoreCase("rif") && (player.hasPermission("rfm.ri"))) {
					try {
						for (Player players : Bukkit.getOnlinePlayers()) {
							if (players.getScoreboard().getPlayerTeam(players).getName()
									.equalsIgnoreCase("team_flee")) {
								int amount = Integer.parseInt(args[1]);
								players.getInventory().removeItem(new ItemStack(Material.FEATHER, amount));
							}
						}
					} catch (NumberFormatException ex) {
						return false;
					}
				}

			} else if (args[0].equalsIgnoreCase("start") && (player.hasPermission("op.start"))) {
				if (RFMmain.game == true) {
					player.sendMessage("既にゲームが始まっています");
					return true;
				}
				RFMmain.loadLocation();
				if (RFMmain.LocationStatsYaml.getString("Flee.world") == null) {
					player.sendMessage("§e⚠fsが設定されていません⚠");
					return true;
				}
				if (RFMmain.LocationStatsYaml.getString("Hunter.world") == null) {
					player.sendMessage("§e⚠hsが設定されていません⚠");
					return true;
				}
				if (RFMmain.LocationStatsYaml.getString("Jail.world") == null) {
					player.sendMessage("§️⚠jsが設定されていません⚠");
					return true;
				}
				if (RFMmain.LocationStatsYaml.getString("Resurrection.world") == null) {
					player.sendMessage("§e⚠rsが設定されていません⚠");
					return true;
				}
				List<String> loclist = RFMmain.LocationStatsYaml.getStringList("RandomSpawns");
				Collections.shuffle(loclist);
				String targetloc = loclist.get(0);

				World world = Bukkit.getWorld(targetloc.split(",")[0]);
				if (world == null) {
					player.sendMessage("§e⚠rjが設定されていません⚠");
					return true;
				}
				RFMmain.reset = false;
				new RFMtosotime(plugin.getConfig().getInt("RFM.setting.time"),
						plugin.getConfig().getInt("RFM.setting.rejail.endtime")).runTaskTimer(RFMmain.plugin, 0, 20);

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.setScoreboard(RFMscoreboard.sb);
					RFMscoreboard.ResetScoreboard(RFMmain.time);
					RFMscoreboard.TosoChangeScoreboard(plugin.getConfig().getInt("RFM.setting.time") + 30);
				}
			} else if (args[0].equalsIgnoreCase("setting")) {
				player.getInventory().clear();
				ItemStack dye8 = new ItemStack(Material.INK_SACK, 1, (byte) 8);
				ItemStack dye1 = new ItemStack(Material.INK_SACK, 1, (byte) 1);
				ItemStack dye = new ItemStack(Material.INK_SACK);
				ItemStack dye10 = new ItemStack(Material.INK_SACK, 1, (byte) 10);
				ItemStack dye14 = new ItemStack(Material.INK_SACK, 1, (byte) 14);

				player.getInventory().addItem(new ItemStack(Material.COMPASS));
				player.getInventory().addItem(new ItemStack(dye8 = RFMmain.setName(dye8, "§7Click to FleeSpawn")));
				player.getInventory().addItem(new ItemStack(dye1 = RFMmain.setName(dye1, "§4Click to HunterSpawn")));
				player.getInventory().addItem(new ItemStack(dye = RFMmain.setName(dye, "§0Click to JailSpawn")));
				player.getInventory()
						.addItem(new ItemStack(dye10 = RFMmain.setName(dye10, "§aClick to ResurrectionSpawn")));
				player.getInventory().addItem(new ItemStack(dye14 = RFMmain.setName(dye14, "§eClick to ReJailSpawn")));

			} else if (args[0].equalsIgnoreCase("check")) {
				RFMmain.loadLocation();

				String world = RFMmain.LocationStatsYaml.getString("Flee.world");
				int x = RFMmain.LocationStatsYaml.getInt("Flee.x");
				int y = RFMmain.LocationStatsYaml.getInt("Flee.y");
				int z = RFMmain.LocationStatsYaml.getInt("Flee.z");
				player.sendMessage("");
				player.sendMessage("§7FleeWorld §e" + world + "§f, §b" + x + "§f, §b" + y + "§f, §b" + z);
				if (RFMmain.LocationStatsYaml.getString("Flee.world") == null) {
					player.sendMessage("§3⚠fsが設定されていません⚠");
				}

				String world1 = RFMmain.LocationStatsYaml.getString("Hunter.world");
				int x1 = RFMmain.LocationStatsYaml.getInt("Hunter.x");
				int y1 = RFMmain.LocationStatsYaml.getInt("Hunter.y");
				int z1 = RFMmain.LocationStatsYaml.getInt("Hunter.z");
				player.sendMessage("");
				player.sendMessage("§4HunterWorld §e" + world1 + "§f, §b" + x1 + "§f, §b" + y1 + "§f, §b" + z1);
				if (RFMmain.LocationStatsYaml.getString("Hunter.world") == null) {
					player.sendMessage("§3⚠hsが設定されていません⚠");
				}

				String world2 = RFMmain.LocationStatsYaml.getString("Jail.world");
				int x2 = RFMmain.LocationStatsYaml.getInt("Jail.x");
				int y2 = RFMmain.LocationStatsYaml.getInt("Jail.y");
				int z2 = RFMmain.LocationStatsYaml.getInt("Jail.z");
				player.sendMessage("");
				player.sendMessage("§0JailWorld §e" + world2 + "§f, §b" + x2 + "§f, §b" + y2 + "§f, §b" + z2);
				if (RFMmain.LocationStatsYaml.getString("Jail.world") == null) {
					player.sendMessage("§3⚠jsが設定されていません⚠");
				}

				String world3 = RFMmain.LocationStatsYaml.getString("Resurrection.world");
				int x3 = RFMmain.LocationStatsYaml.getInt("Resurrection.x");
				int y3 = RFMmain.LocationStatsYaml.getInt("Resurrection.y");
				int z3 = RFMmain.LocationStatsYaml.getInt("Resurrection.z");
				player.sendMessage("");
				player.sendMessage("§aResurrectionWorld §e" + world3 + "§f, §b" + x3 + "§f, §b" + y3 + "§f, §b" + z3);
				if (RFMmain.LocationStatsYaml.getString("Resurrection.world") == null) {
					player.sendMessage("§3⚠rsが設定されていません⚠");
				}

				List<String> loclist = RFMmain.LocationStatsYaml.getStringList("RandomSpawns");
				Collections.shuffle(loclist);
				String targetloc = loclist.get(0);

				World world4 = Bukkit.getWorld(targetloc.split(",")[0]);
				int x4 = RFMmain.LocationStatsYaml.getInt(targetloc.split(",")[1]);
				int y4 = RFMmain.LocationStatsYaml.getInt(targetloc.split(",")[2]);
				int z4 = RFMmain.LocationStatsYaml.getInt(targetloc.split(",")[3]);
				player.sendMessage("");
				player.sendMessage("§eReJailWorld §e" + world4 + "§f, §b" + x4 + "§f, §b" + y4 + "§f, §b" + z4);
				if (world4 == null) {
					player.sendMessage("§3⚠rjが設定されていません⚠");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("rf")) {
				int randomNumber = new Random().nextInt(Bukkit.getOnlinePlayers().size());
				Player randomPlayer = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[randomNumber];
				if ((randomPlayer).getScoreboard().getPlayerTeam(randomPlayer).getName()
						.equalsIgnoreCase("team_flee")) {

					RFMutil.HunterSet(randomPlayer);
					Bukkit.broadcastMessage(RFMmain.prefix + "§b" + randomPlayer.getName() + " §rが§4Hunter§rとなってしまった。");
				}

			} else if (args[0].equalsIgnoreCase("end")) {
				Bukkit.getScheduler().cancelTasks(plugin);
				RFMmain.loadLocation();

				Bukkit.broadcastMessage("§b>>>>>>>>>>>>>>>>>");
				Bukkit.broadcastMessage("§b>§cRFM End!§b<");
				Bukkit.broadcastMessage("§b>§aHunter Win!§b<");
				Bukkit.broadcastMessage("§b<<<<<<<<<<<<<<<<<");
				RFMmain.reset = true;

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 100, 1);
				}

				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
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
						continue;
					}
				}
			} else if (args[0].equalsIgnoreCase("reset")) {
				Bukkit.getScheduler().cancelTasks(plugin);
				RFMmain.reset = true;
				RFMutil.Allremove();
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void GameEnd() {
		RFMmain.game = false;
		Bukkit.getScheduler().cancelTasks(RFMmain.plugin);
		RFMmain.loadLocation();

		int size = RFMscore.TeamSize("team_flee");
		int size1 = RFMscore.TeamSize("team_survivor");
		if (size == 0) {
			if (size1 == 0) {
				Bukkit.broadcastMessage("§b>>>>>>>>>>>>>>>>>");
				Bukkit.broadcastMessage("§b>§cBFM End!§b<");
				Bukkit.broadcastMessage("§b>§aHunter Win!§b<");
				Bukkit.broadcastMessage("§b<<<<<<<<<<<<<<<<<");

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
					if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
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
						continue;
					}
				}
			}
		}
	}
}