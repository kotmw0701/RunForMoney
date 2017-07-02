package rfm;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import cmd.RFMplayer;

public class RFMutil implements Listener {

	private static RFMmain plugin;

	public RFMutil(RFMmain plugin) {
		RFMutil.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public static void AdminSet(Player target) {
		target.getInventory().clear();
		for (PotionEffect effect : target.getActivePotionEffects()) {
			target.removePotionEffect(effect.getType());
		}
		RFMmain.teamAdmin.removePlayer(target);
		RFMmain.teamReport.removePlayer(target);
		RFMmain.teamFlee.removePlayer(target);
		RFMmain.teamHunter.removePlayer(target);
		RFMmain.teamJail.removePlayer(target);
		RFMmain.teamSurvivor.removePlayer(target);
		Team team = RFMmain.teamAdmin;
		team.addPlayer(target);
		target.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);

		target.setGameMode(GameMode.CREATIVE);

		target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, (int) 0.5));
		target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, (int) 0.5));

		target.getInventory().addItem(new ItemStack(Material.COMPASS));
		target.getInventory().addItem(new ItemStack(Material.DIAMOND));

		target.getEquipment().setHelmet(new ItemStack(Material.AIR));
		target.getEquipment().setChestplate(new ItemStack(Material.AIR));
		target.getEquipment().setLeggings(new ItemStack(Material.AIR));
		target.getEquipment().setBoots(new ItemStack(Material.AIR));
		target.updateInventory();
	}

	@SuppressWarnings("deprecation")
	public static void ReportSet(Player target) {
		target.getInventory().clear();
		for (PotionEffect effect : target.getActivePotionEffects()) {
			target.removePotionEffect(effect.getType());
		}
		RFMmain.teamAdmin.removePlayer(target);
		RFMmain.teamReport.removePlayer(target);
		RFMmain.teamFlee.removePlayer(target);
		RFMmain.teamHunter.removePlayer(target);
		RFMmain.teamJail.removePlayer(target);
		RFMmain.teamSurvivor.removePlayer(target);
		Team team = RFMmain.teamReport;
		team.addPlayer(target);
		target.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);

		target.setGameMode(GameMode.CREATIVE);

		target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, (int) 0.5));
		target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, (int) 0.5));

		target.getInventory().addItem(new ItemStack(Material.COMPASS));
		target.getInventory().addItem(new ItemStack(Material.DIAMOND));
		target.getInventory().addItem(new ItemStack(Material.STICK));

		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);

		ItemMeta helmetmeta = helmet.getItemMeta();
		helmetmeta.spigot().setUnbreakable(true);
		helmet.setItemMeta(helmetmeta);

		ItemMeta chestplatemeta = chestplate.getItemMeta();
		chestplatemeta.spigot().setUnbreakable(true);
		chestplate.setItemMeta(chestplatemeta);

		ItemMeta leggingsmeta = leggings.getItemMeta();
		leggingsmeta.spigot().setUnbreakable(true);
		leggings.setItemMeta(leggingsmeta);

		ItemMeta bootsmeta = boots.getItemMeta();
		bootsmeta.spigot().setUnbreakable(true);
		boots.setItemMeta(bootsmeta);

		target.getEquipment().setHelmet(helmet);
		target.getEquipment().setChestplate(chestplate);
		target.getEquipment().setLeggings(leggings);
		target.getEquipment().setBoots(boots);
		target.updateInventory();
	}

	@SuppressWarnings("deprecation")
	public static void FleeSet(Player target) {
		target.getInventory().clear();
		for (PotionEffect effect : target.getActivePotionEffects()) {
			target.removePotionEffect(effect.getType());
		}
		RFMmain.teamAdmin.removePlayer(target);
		RFMmain.teamReport.removePlayer(target);
		RFMmain.teamFlee.removePlayer(target);
		RFMmain.teamHunter.removePlayer(target);
		RFMmain.teamJail.removePlayer(target);
		RFMmain.teamSurvivor.removePlayer(target);
		Team team = RFMmain.teamFlee;
		team.addPlayer(target);
		target.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);

		target.setGameMode(GameMode.ADVENTURE);

		target.getEquipment().setHelmet(new ItemStack(Material.AIR));
		target.getEquipment().setChestplate(new ItemStack(Material.AIR));
		target.getEquipment().setLeggings(new ItemStack(Material.AIR));
		target.getEquipment().setBoots(new ItemStack(Material.AIR));
		target.updateInventory();
	}

	@SuppressWarnings("deprecation")
	public static void HunterSet(Player target) {
		target.getInventory().clear();
		for (PotionEffect effect : target.getActivePotionEffects()) {
			target.removePotionEffect(effect.getType());
		}
		RFMmain.teamAdmin.removePlayer(target);
		RFMmain.teamReport.removePlayer(target);
		RFMmain.teamFlee.removePlayer(target);
		RFMmain.teamHunter.removePlayer(target);
		RFMmain.teamJail.removePlayer(target);
		RFMmain.teamSurvivor.removePlayer(target);
		Team team = RFMmain.teamHunter;
		team.addPlayer(target);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);

		target.setGameMode(GameMode.ADVENTURE);

		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

		ItemMeta helmetmeta = helmet.getItemMeta();
		helmetmeta.spigot().setUnbreakable(true);
		helmet.setItemMeta(helmetmeta);

		ItemMeta chestplatemeta = chestplate.getItemMeta();
		chestplatemeta.spigot().setUnbreakable(true);
		chestplate.setItemMeta(chestplatemeta);

		ItemMeta leggingsmeta = leggings.getItemMeta();
		leggingsmeta.spigot().setUnbreakable(true);
		leggings.setItemMeta(leggingsmeta);

		ItemMeta bootsmeta = boots.getItemMeta();
		bootsmeta.spigot().setUnbreakable(true);
		boots.setItemMeta(bootsmeta);

		target.getEquipment().setHelmet(helmet);
		target.getEquipment().setChestplate(chestplate);
		target.getEquipment().setLeggings(leggings);
		target.getEquipment().setBoots(boots);
		target.updateInventory();
	}

	@SuppressWarnings("deprecation")
	public static void JailSet(Player target) {
		for (PotionEffect effect : target.getActivePotionEffects()) {
			target.removePotionEffect(effect.getType());
		}
		RFMmain.teamAdmin.removePlayer(target);
		RFMmain.teamReport.removePlayer(target);
		RFMmain.teamFlee.removePlayer(target);
		RFMmain.teamHunter.removePlayer(target);
		RFMmain.teamJail.removePlayer(target);
		RFMmain.teamSurvivor.removePlayer(target);
		Team team = RFMmain.teamJail;
		team.addPlayer(target);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);

		target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, (int) 0.5));

		target.getInventory().removeItem(new ItemStack(Material.BONE, 5));
		target.getInventory().removeItem(new ItemStack(Material.EGG, 5));
		target.getInventory().removeItem(new ItemStack(Material.FEATHER, 5));

		target.getEquipment().setHelmet(null);
		target.getEquipment().setChestplate(null);
		target.getEquipment().setLeggings(null);
		target.getEquipment().setBoots(null);
		target.updateInventory();
	}

	@SuppressWarnings("deprecation")
	public static void SurvivorSet(Player target) {
		RFMmain.teamAdmin.removePlayer(target);
		RFMmain.teamReport.removePlayer(target);
		RFMmain.teamFlee.removePlayer(target);
		RFMmain.teamHunter.removePlayer(target);
		RFMmain.teamJail.removePlayer(target);
		RFMmain.teamSurvivor.removePlayer(target);
		Team team = RFMmain.teamSurvivor;
		team.addPlayer(target);
		target.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void AutoJailPlayer(Player player) {
		Team team = RFMmain.teamJail;
		team.addPlayer(player);
		player.setScoreboard(RFMscoreboard.sb);
		RFMscoreboard.ResetScoreboard(RFMmain.time);
		RFMscoreboard.TosoChangeScoreboard(RFMmain.time);
	}

	@SuppressWarnings("deprecation")
	public static void HunterTp() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			if ((players.getScoreboard().getPlayerTeam(players) == null)) {
				Bukkit.broadcastMessage("§e§7");
				continue;
			}
			if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
				RFMmain.loadLocation();
				String world = RFMmain.LocationStatsYaml.getString("Hunter.world");
				double x = RFMmain.LocationStatsYaml.getDouble("Hunter.x");
				double y = RFMmain.LocationStatsYaml.getDouble("Hunter.y");
				double z = RFMmain.LocationStatsYaml.getDouble("Hunter.z");
				float pi = (float) RFMmain.LocationStatsYaml.getDouble("Hunter.pitch");
				float ya = (float) RFMmain.LocationStatsYaml.getDouble("Hunter.yaw");

				Location loc = new Location(plugin.getServer().getWorld(world), x, y, z);
				loc.setPitch(pi);
				loc.setYaw(ya);
				players.teleport(loc);
				continue;
			}
		}
	}

	public static void JailTp(Player player) {
		RFMmain.loadLocation();
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
	}

	@SuppressWarnings("deprecation")
	public static void randomSpawns(Player player) {
		ItemStack book = new ItemStack(Material.BOOK);
		ItemMeta bookmeta = book.getItemMeta();
		bookmeta.setDisplayName("§aPvP Menu");
		book.setItemMeta(bookmeta);

		List<String> loclist = RFMmain.LocationStatsYaml.getStringList("RandomSpawns");
		Collections.shuffle(loclist);
		String targetloc = loclist.get(0);

		World world = Bukkit.getWorld(targetloc.split(",")[0]);
		double x = Integer.parseInt(targetloc.split(",")[1]) + 0.5;
		double y = Integer.parseInt(targetloc.split(",")[2]) + 0.5;
		double z = Integer.parseInt(targetloc.split(",")[3]) + 0.5;

		RFMmain.teamAdmin.removePlayer(player);
		RFMmain.teamAdmin.removePlayer(player);
		RFMmain.teamReport.removePlayer(player);
		RFMmain.teamFlee.removePlayer(player);
		RFMmain.teamHunter.removePlayer(player);
		RFMmain.teamJail.removePlayer(player);
		RFMmain.teamSurvivor.removePlayer(player);
		Team team = RFMmain.teamJail;
		team.addPlayer(player);
		RFMutil.FleeSet(player);

		player.teleport(new Location(world, x, y, z));

		player.getInventory().addItem(new ItemStack(Material.BONE, 3));
		player.getInventory().addItem(new ItemStack(Material.EGG, 3));
		player.getInventory().addItem(new ItemStack(Material.FEATHER, 3));
		player.updateInventory();

		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
		Bukkit.broadcastMessage("§a" + player.getName() + " §7が逃走中に復帰した。");
	}

	public static void GameEndSet(Player target) {
		RFMplayer.checkGameEnd();
	}

	@SuppressWarnings("deprecation")
	public static void SetInventory() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			if ((players.getScoreboard().getPlayerTeam(players) == null)) {
				continue;
			}
			if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_flee")) {
				players.getInventory().clear();
				players.getInventory().addItem(new ItemStack(Material.BONE, 5));
				players.getInventory().addItem(new ItemStack(Material.EGG, 5));
				players.getInventory().addItem(new ItemStack(Material.FEATHER, 5));
				players.updateInventory();
				continue;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void Allremove() {
		Bukkit.getScheduler().cancelTasks(plugin);
		for (Player players : Bukkit.getOnlinePlayers()) {
			RFMmain.loadLocation();
			String world = RFMmain.LocationStatsYaml.getString("Flee.world");
			double x = RFMmain.LocationStatsYaml.getDouble("Flee.x");
			double y = RFMmain.LocationStatsYaml.getDouble("Flee.y");
			double z = RFMmain.LocationStatsYaml.getDouble("Flee.z");
			float pi = (float) RFMmain.LocationStatsYaml.getDouble("Flee.pitch");
			float ya = (float) RFMmain.LocationStatsYaml.getDouble("Flee.yaw");

			Location loc = new Location(plugin.getServer().getWorld(world), x, y, z);
			loc.setPitch(pi);
			loc.setYaw(ya);
			players.teleport(loc);
			players.getEquipment().setHelmet(new ItemStack(Material.AIR));
			players.getEquipment().setChestplate(new ItemStack(Material.AIR));
			players.getEquipment().setLeggings(new ItemStack(Material.AIR));
			players.getEquipment().setBoots(new ItemStack(Material.AIR));
			players.getInventory().clear();
			players.updateInventory();

			players.setScoreboard(RFMscoreboard.sb);
			RFMscoreboard.ResetScoreboard(RFMmain.time);
			RFMscoreboard.TosoChangeScoreboard(RFMmain.time);

			for (PotionEffect effect : players.getActivePotionEffects()) {
				players.removePotionEffect(effect.getType());
			}

			players.sendTitle("§eRFM Reset", "");

			RFMmain.reset = true;
			RFMmain.relese = true;
			RFMmain.game = false;
		}
	}
}