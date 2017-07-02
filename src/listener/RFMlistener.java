package listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import rfm.RFMmain;
import rfm.RFMscoreboard;
import rfm.RFMutil;

public class RFMlistener implements Listener {

	static HashMap<Player, Integer> dash = new HashMap<>();
	static HashMap<Player, Integer> sneak = new HashMap<>();

	private static RFMmain plugin;

	public RFMlistener(RFMmain plugin) {
		RFMlistener.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		player.setLevel(0);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
		if ((RFMmain.game == false) && (player.getScoreboard().getPlayerTeam(player) == null)) {
			FleeJoin(player);
		}
		if ((RFMmain.game == true) && (player.getScoreboard().getPlayerTeam(player) == null)) {
			if (player.getScoreboard().getPlayerTeam(player) == null) {
				RFMutil.AutoJailPlayer(player);
				String world = RFMmain.LocationStatsYaml.getString("Jail.world");
				double x = RFMmain.LocationStatsYaml.getDouble("Jail.x");
				double y = RFMmain.LocationStatsYaml.getDouble("Jail.y");
				double z = RFMmain.LocationStatsYaml.getDouble("Jail.z");
				float pi = (float) RFMmain.LocationStatsYaml.getDouble("Jail.pitch");
				float ya = (float) RFMmain.LocationStatsYaml.getDouble("Jail.yaw");

				Location loc = new Location(plugin.getServer().getWorld(world), x, y, z);
				loc.setPitch(pi);
				loc.setYaw(ya);
				player.teleport(loc);
				Bukkit.broadcastMessage("§b" + player.getName() + "§7が逃走中に途中参加しました。");
				player.getInventory().clear();
				player.updateInventory();
			}
		}

		event.setJoinMessage(null);

		Bukkit.broadcastMessage(RFMmain.prefix + "§e" + player.getName() + ChatColor.AQUA + " がRunForMoneyにJoinしました。");

		player.sendMessage(RFMmain.prefix + ChatColor.WHITE + "---" + ChatColor.AQUA + "<禁止事項-逃走者>");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ハンターのいけない所に行く行為(ダッシュジャンプのみで行けるなど)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ハンターをアイテムなどで煽る行為");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "特定のミッション以外でのアイテム受け渡し");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		player.sendMessage(RFMmain.prefix + ChatColor.WHITE + "---" + ChatColor.AQUA + "<禁止事項-ハンター>");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "逃走者を視界に捉えていないのに走る行為");
		player.sendMessage(ChatColor.AQUA + "(通報部隊投入による通報を受けた場合は見つけてなくても走って構いません)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ゲーム終了前にサーバーから抜ける行為(例外,有)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "同じ場所に留まる行為(芋行為)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "放送を見ながらの捜索(音声もNG)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "緊急時以外のチャット");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "卵の意図的な回避");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		AdminQuit(player);
		ReportQuit(player);
		FleeQuit(player);
		HunterQuit(player);
		JailQuit(player);
		SurvivorQuit(player);

		event.setQuitMessage(null);
		Bukkit.broadcastMessage(
				RFMmain.prefix + "§e" + player.getName() + ChatColor.AQUA + " がRunForMoneyからLogoutしました。");
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (player.getScoreboard().getPlayerTeam(player) == null) {
			return;
		}

		if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
				|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")
						|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_hunter")))) {
			if ((player.isSprinting()) && (event.getFrom().getBlockY() + 1 < event.getTo().getY())) {
				if (RFMmain.game == false) {
					return;
				}
				event.setCancelled(true);
				ActionBarAPI.sendActionBar(player, "§e⚠ダッシュジャンプはできません⚠");
			}
		}

		if (event.getFrom().getBlockY() != event.getTo().getBlockY()) {
			Block block = event.getFrom().getBlock().getRelative(BlockFace.DOWN, 2);
			if ((block.getType().equals(Material.SIGN)
					|| (block.getType().equals(Material.SIGN_POST) || (block.getType().equals(Material.WALL_SIGN))))) {
				Sign sign = (Sign) block.getState();
				if (sign.getLine(0).equalsIgnoreCase("§3[ReJailE]")
						&& (sign.getLine(1).equalsIgnoreCase("§3Jump here"))) {
					if (player.getScoreboard().getPlayerTeam(player) == null) {
						player.sendMessage(player.getName() + " はどのチームにも入っていません。");
						return;
					}
					if (!player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_jail")) {
						player.sendMessage(player.getName() + " は牢獄ではありません。");
						return;
					}
					if (player.hasPotionEffect(PotionEffectType.REGENERATION)) {
						player.sendMessage(" §e復活クールタイム中は復活できません。");
						return;
					}
					if (RFMmain.game == false) {
						player.sendMessage(" §eゲームは終了しています。");
						return;
					}
					if (!RFMmain.relese) {
						player.sendMessage(" §e復活は終了しています。");
						return;
					}
					RFMutil.randomSpawns(player);
				}

				if (sign.getLine(0).equalsIgnoreCase("§3[ReJailH]")
						&& (sign.getLine(1).equalsIgnoreCase("§3Jump here"))) {
					if (player.getScoreboard().getPlayerTeam(player) == null) {
						player.sendMessage(player.getName() + " はどのチームにも入っていません。");
						return;
					}
					if (!player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_jail")) {
						player.sendMessage(player.getName() + " は牢獄ではありません。");
						return;
					}
					if (player.hasPotionEffect(PotionEffectType.REGENERATION)) {
						player.sendMessage(" §e復活クールタイム中は復活できません。");
						return;
					}
					if (RFMmain.game == false) {
						player.sendMessage(" §eゲームは終了しています。");
						return;
					}
					if (!RFMmain.relese) {
						player.sendMessage(" §e復活は終了しています。");
						return;
					}
					RFMutil.randomSpawns(player);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_report")) {
			if (player.getItemInHand().getType().equals(Material.STICK)) {
				Player target = (Player) event.getRightClicked();
				if ((target.getScoreboard().getPlayerTeam(target).getName().equalsIgnoreCase("team_flee") || (target
						.getScoreboard().getPlayerTeam(target).getName().equalsIgnoreCase("team_survivor")))) {
					ActionBarAPI.sendActionBar(target, "§e⚠通報されています！⚠");
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
							Location loc = target.getLocation();

							players.sendMessage("");
							players.sendMessage(
									RFMmain.prefix + "§7" + target.getName() + "'s §bLocation: §6" + loc.getBlockX()
											+ "§r, §6" + loc.getBlockY() + "§r, §6" + loc.getBlockZ() + "§r.");
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();
		if (player.getScoreboard().getPlayerTeam(player) == null) {
			player.setWalkSpeed(0.25F);
			return;
		}

		if ((player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
				|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")))) {
			if (RFMmain.game == false) {
				return;
			}
			if (dash.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(dash.get(player));
			}
			if (sneak.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(sneak.get(player));
			}
			dash.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

				@Override
				public void run() {
					if (player.getHealth() > 2) {
						player.setHealth((int) (player.getHealth() - 1));
					}
					if (!player.isSprinting()) {
						Bukkit.getScheduler().cancelTask(dash.get(player));
					}
					if (player.getHealth() < 6) {
						player.setWalkSpeed(0.1F);
					}
					if (player.getHealth() <= 2) {
						if (dash.containsKey(player)) {
							Bukkit.getScheduler().cancelTask(dash.get(player));
						}
					}
				}
			}, 60, 60));
		} else {
			if (dash.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(dash.get(player));
			}
			if (sneak.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(sneak.get(player));
			}
			player.setHealth(20);
			player.setWalkSpeed(0.2F);
		}

		if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_hunter")) {
			if (event.isSprinting()) {
				player.setWalkSpeed(0.3F);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();

		if ((player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
				|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")))) {
			if (RFMmain.game == false) {
				return;
			}
			if (dash.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(dash.get(player));
			}
			if (sneak.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(sneak.get(player));
			}
			sneak.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

				@Override
				public void run() {
					if (player.getHealth() < 20) {
						player.setHealth((int) (player.getHealth() + 1));
					}
					if (!player.isSneaking()) {
						Bukkit.getScheduler().cancelTask(sneak.get(player));
					}
					if (player.getHealth() > 6) {
						player.setWalkSpeed(0.2F);
					}
				}
			}, 60, 60));
		} else {
			if (dash.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(dash.get(player));
			}
			if (sneak.containsKey(player)) {
				Bukkit.getScheduler().cancelTask(sneak.get(player));
			}
			player.setHealth(20);
			player.setWalkSpeed(0.2F);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.FALL) {
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)
				&& (event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
			Player def = (Player) event.getEntity();
			Player att = (Player) event.getDamager();

			if (att.getScoreboard().getPlayerTeam(att) == null) {
				event.setCancelled(true);
				return;
			}
			if (def.getScoreboard().getPlayerTeam(def) == null) {
				event.setCancelled(true);
				return;
			}
			if (att.getScoreboard().getPlayerTeam(att).getName().equalsIgnoreCase("team_hunter")) {
				if (def.getScoreboard().getPlayerTeam(def).getName().equalsIgnoreCase("team_flee")
						|| (def.getScoreboard().getPlayerTeam(def).getName().equalsIgnoreCase("team_survivor"))) {
					if (def.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						event.setCancelled(true);
						return;
					}
					if (att.hasPotionEffect(PotionEffectType.SLOW)) {
						event.setCancelled(true);
						return;
					}
					if (RFMmain.game == false) {
						return;
					}
					Bukkit.broadcastMessage(RFMmain.prefix + "§b" + def.getPlayer().getName() + "§cが確保されました。");
					def.sendMessage("§7あなたは確保されました。");
					RFMutil.JailSet(def);
					RFMutil.GameEndSet(def);
					RFMutil.JailTp(def);
					att.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 70));
					att.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 70));
					event.setDamage(0);
				}
			}
			event.setDamage(0);
		}
	}

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		HumanEntity entity = event.getView().getPlayer();
		if (entity instanceof Player) {
			Player player = (Player) entity;
			if (event.getSlotType().equals(SlotType.ARMOR)) {
				if (!player.isOp()) {
					event.setCancelled(true);
					player.sendMessage("§c⚠装備は外せません⚠");
				}
			}
		}
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		Player player = (Player) event.getEntity();
		event.setCancelled(true);
		player.setFoodLevel(20);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void AdminChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		if (player.getScoreboard().getPlayerTeam(player) != null) {
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_admin")) {
				String message = ChatColor.GOLD + "[運営]" + ChatColor.WHITE + "<" + ChatColor.GREEN + player.getName()
						+ ChatColor.WHITE + ">" + ChatColor.GRAY + ": " + ChatColor.WHITE + event.getMessage();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players) != null) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_admin")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_report")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_jail")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName()
								.equalsIgnoreCase("team_survivor")) {
							players.sendMessage(message);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ReportChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		if (player.getScoreboard().getPlayerTeam(player) != null) {
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_report")) {
				String message = ChatColor.LIGHT_PURPLE + "[通報部隊]" + ChatColor.WHITE + "<" + ChatColor.GREEN
						+ player.getName() + ChatColor.WHITE + ">" + ChatColor.GRAY + ": " + ChatColor.WHITE
						+ event.getMessage();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players) != null) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_admin")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_report")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_jail")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName()
								.equalsIgnoreCase("team_survivor")) {
							players.sendMessage(message);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void FleeChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		if (player.getScoreboard().getPlayerTeam(player) != null) {
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")) {
				String message = ChatColor.GRAY + "[逃走者]" + ChatColor.WHITE + "<" + ChatColor.GREEN + player.getName()
						+ ChatColor.WHITE + ">" + ChatColor.GRAY + ": " + ChatColor.WHITE + event.getMessage();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players) != null) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_admin")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_report")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_jail")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName()
								.equalsIgnoreCase("team_survivor")) {
							players.sendMessage(message);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void HunterChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		if (player.getScoreboard().getPlayerTeam(player) != null) {
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_hunter")) {
				String message = ChatColor.DARK_RED + "[ハンター]" + ChatColor.WHITE + "<" + ChatColor.GREEN
						+ player.getName() + ChatColor.WHITE + ">" + ChatColor.GRAY + ": " + ChatColor.WHITE
						+ event.getMessage();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players) != null) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_admin")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_report")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
							players.sendMessage(message);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void JailChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		if (player.getScoreboard().getPlayerTeam(player) != null) {
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_jail")) {
				String message = ChatColor.BLACK + "[牢獄]" + ChatColor.WHITE + "<" + ChatColor.GREEN + player.getName()
						+ ChatColor.WHITE + ">" + ChatColor.GRAY + ": " + ChatColor.WHITE + event.getMessage();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players) != null) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_admin")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_report")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_jail")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName()
								.equalsIgnoreCase("team_survivor")) {
							players.sendMessage(message);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void SurvivorChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		event.setCancelled(true);
		if (player.getScoreboard().getPlayerTeam(player) != null) {
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")) {
				String message = ChatColor.GREEN + "[生存者]" + ChatColor.WHITE + "<" + ChatColor.GREEN + player.getName()
						+ ChatColor.WHITE + ">" + ChatColor.GRAY + ": " + ChatColor.WHITE + event.getMessage();
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (players.getScoreboard().getPlayerTeam(players) != null) {
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_admin")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_report")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_jail")) {
							players.sendMessage(message);
						}
						if (players.getScoreboard().getPlayerTeam(players).getName()
								.equalsIgnoreCase("team_survivor")) {
							players.sendMessage(message);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (event.getLine(0).equalsIgnoreCase("ReJailE")) {
			event.setLine(0, "§3[ReJailE]");
			event.setLine(1, "§3Jump here");

			player.sendMessage(" §7ReJailE 看板を設置しました");
		}

		if (event.getLine(0).equalsIgnoreCase("ReJailH")) {
			event.setLine(0, "§3[ReJailH]");
			event.setLine(1, "§3Jump here");

			player.sendMessage(" §7ReJailH 看板を設置しました");
		}
	}

	@SuppressWarnings("deprecation")
	public static void FleeJoin(Player player) {
		Team team = RFMmain.teamFlee;
		team.addPlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void JailJoin(Player player) {
		Team team = RFMmain.teamJail;
		team.addPlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void AdminQuit(Player player) {
		Team team = RFMmain.teamAdmin;
		team.removePlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void ReportQuit(Player player) {
		Team team = RFMmain.teamReport;
		team.removePlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void FleeQuit(Player player) {
		Team team = RFMmain.teamFlee;
		team.removePlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void HunterQuit(Player player) {
		Team team = RFMmain.teamHunter;
		team.removePlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void JailQuit(Player player) {
		Team team = RFMmain.teamJail;
		team.removePlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void SurvivorQuit(Player player) {
		Team team = RFMmain.teamSurvivor;
		team.removePlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}
}