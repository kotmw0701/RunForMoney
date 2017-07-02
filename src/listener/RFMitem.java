package listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rfm.RFMmain;

public class RFMitem implements Listener {

	private ArrayList<String> cool = new ArrayList<String>();

	private RFMmain plugin;

	public RFMitem(RFMmain plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Bone(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || (event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
			if ((player.getScoreboard().getPlayerTeam(player) == null)) {
				return;
			}
			if ((player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
					|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")))) {
				if (player.getItemInHand().getType().equals(Material.BONE)) {
					if (cool.contains(player.getName())) {
						event.setCancelled(true);
						player.sendMessage("§c⚠アイテムの連続使用はできません⚠");
						return;
					}
					int ran = (int) (Math.random() * 9);
					switch (ran) {
					case 1:
						player.sendMessage("§f骨のはずが、、、人骨だったようだ！");
						event.setCancelled(true);
						int amount = player.getItemInHand().getAmount();
						if (amount == 1) {
							player.setItemInHand(null);
						} else {
							player.getItemInHand().setAmount(amount - 1);
						}
						cool.add(player.getName());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								cool.remove(player.getName());
								player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");
							}
						}, 100);
						break;
					default:
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1));

						for (Player players : Bukkit.getOnlinePlayers()) {
							if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
								if (player != players) {
									player.hidePlayer(players);
								}
							}
						}

						event.setCancelled(true);
						int amounts = player.getItemInHand().getAmount();
						if (amounts == 1) {
							player.setItemInHand(null);
						} else {
							player.getItemInHand().setAmount(amounts - 1);
						}
						cool.add(player.getName());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								cool.remove(player.getName());
								player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");

								for (Player players : Bukkit.getOnlinePlayers()) {
									if (players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
										if (player != players) {
											player.showPlayer(players);
										}
									}
								}
							}
						}, 100);
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Egg(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if ((player.getScoreboard().getPlayerTeam(player) == null)) {
				return;
			}
			if ((player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
					|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")))) {
				if (player.getItemInHand().getType().equals(Material.EGG)) {
					if (cool.contains(player.getName())) {
						event.setCancelled(true);
						player.sendMessage("§c⚠アイテムの連続使用はできません⚠");
						return;
					}
					int ran = (int) (Math.random() * 4);
					switch (ran) {
					case 1:
						event.setCancelled(true);
						int amount = player.getItemInHand().getAmount();

						if (amount == 1) {
							player.setItemInHand(null);
						} else {
							player.getItemInHand().setAmount(amount - 1);
						}
						player.sendMessage("§f卵が、ハンターに当たらなかった！");
						player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 100F, 100F);
						cool.add(player.getName());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								cool.remove(player.getName());
								player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");
							}
						}, 100);
						break;
					default:
						cool.add(player.getName());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								cool.remove(player.getName());
								player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");
							}
						}, 100);
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void EggAttack(EntityDamageByEntityEvent event) {
		if ((event.getDamager() instanceof Egg) && (event.getEntity() instanceof Player)) {
			Player def = (Player) event.getEntity();
			Egg egg = (Egg) event.getDamager();
			final Player att = (Player) egg.getShooter();
			if ((def.getScoreboard().getPlayerTeam(def) == null)) {
				return;
			}
			if ((att.getScoreboard().getPlayerTeam(att) == null)) {
				return;
			}
			if ((att.getScoreboard().getPlayerTeam(att).getName().equalsIgnoreCase("team_flee")
					|| (att.getScoreboard().getPlayerTeam(att).getName().equalsIgnoreCase("team_survivor")))) {
				if (def.getScoreboard().getPlayerTeam(def).getName().equalsIgnoreCase("team_hunter")) {
					if (def.hasPotionEffect(PotionEffectType.BLINDNESS)) {
						return;
					}
					def.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 2));
					def.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 5, 30));
					event.setDamage(0);

					att.playSound(def.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 50F, 50F);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Feather(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || (event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
			if ((player.getScoreboard().getPlayerTeam(player) == null)) {
				return;
			}
			if ((player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
					|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")))) {
				if (player.getItemInHand().getType() == Material.FEATHER) {
					if (cool.contains(player.getName())) {
						event.setCancelled(true);
						player.sendMessage("§c⚠アイテムの連続使用はできません⚠");
						return;
					}
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
					event.setCancelled(true);
					player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1, 1);
					int amount = player.getItemInHand().getAmount();
					if (amount == 1) {
						player.setItemInHand(null);
					} else {
						player.getItemInHand().setAmount(amount - 1);
					}
					cool.add(player.getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							cool.remove(player.getName());
							player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");
						}
					}, 100);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Snowball(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if ((player.getScoreboard().getPlayerTeam(player) == null)) {
				return;
			}
			if ((player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_flee")
					|| (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_survivor")))) {
				if (player.getItemInHand().getType().equals(Material.SNOW_BALL)) {
					if (cool.contains(player.getName())) {
						event.setCancelled(true);
						player.sendMessage("§c⚠アイテムの連続使用はできません⚠");
						return;
					}
					int ran = (int) (Math.random() * 4);
					switch (ran) {
					case 1:
						event.setCancelled(true);
						int amount = player.getItemInHand().getAmount();
						if (amount == 1) {
							player.setItemInHand(null);
						} else {
							player.getItemInHand().setAmount(amount - 1);
						}
						player.sendMessage("§f雪玉が、ハンターに当たらなかった！");
						player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 100F, 100F);
						cool.add(player.getName());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								cool.remove(player.getName());
								player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");
							}
						}, 100);
						break;
					default:
						cool.add(player.getName());
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							@Override
							public void run() {
								cool.remove(player.getName());
								player.sendMessage(ChatColor.DARK_AQUA + "アイテムの再使用が可能になりました。");
							}
						}, 100);
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void SnowballAtack(EntityDamageByEntityEvent event) {
		if ((event.getDamager() instanceof Snowball) && (event.getEntity() instanceof Player)) {
			Player def = (Player) event.getEntity();
			Snowball snowball = (Snowball) event.getDamager();
			final Player att = (Player) snowball.getShooter();
			if ((def.getScoreboard().getPlayerTeam(def) == null)) {
				return;
			}
			if ((att.getScoreboard().getPlayerTeam(att) == null)) {
				return;
			}
			if ((att.getScoreboard().getPlayerTeam(att).getName().equalsIgnoreCase("team_flee")
					|| (att.getScoreboard().getPlayerTeam(att).getName().equalsIgnoreCase("team_survivor")))) {
				if (def.getScoreboard().getPlayerTeam(def).getName().equalsIgnoreCase("team_hunter")) {
					event.setDamage(0);
					def.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 10, 9));
					final List<Block> blocks = new ArrayList<Block>();
					Block target_block = event.getEntity().getLocation().getBlock();
					for (int x = -1; x <= 1; x++) {
						for (int y = 0; y <= 1; y++) {
							for (int z = -1; z <= 1; z++) {
								if (target_block.getRelative(x, y, z).getType() != Material.AIR) {
									continue;
								}
								target_block.getRelative(x, y, z).setType(Material.WEB);
								blocks.add(target_block.getRelative(x, y, z));
							}
						}
					}
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							for (Block block : blocks) {
								block.setType(Material.AIR);
							}
						}
					}, 200);
					att.playSound(def.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 50F, 50F);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Stick(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if ((player.getScoreboard().getPlayerTeam(player) == null)) {
				return;
			}
			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_report")) {
				if (player.getItemInHand().getType().equals(Material.STICK)) {
					if (cool.contains(player.getName())) {
						event.setCancelled(true);
						player.sendMessage("§c⚠連続の通報はできません⚠");
						return;
					}
					Firework firework = (Firework) player.getWorld().spawn(event.getPlayer().getLocation(),
							Firework.class);

					FireworkMeta meta = firework.getFireworkMeta();

					FireworkEffect.Builder effect = FireworkEffect.builder();

					effect.with(FireworkEffect.Type.BALL_LARGE);
					effect.withColor(Color.YELLOW);
					effect.withFade(Color.RED);
					effect.flicker(true);
					effect.trail(true);

					meta.addEffect(effect.build());

					meta.setPower(1);

					firework.setFireworkMeta(meta);

					player.getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

					cool.add(player.getName());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						@Override
						public void run() {
							cool.remove(player.getName());
							player.sendMessage(ChatColor.DARK_AQUA + "通報が可能になりました。");
						}
					}, 100);
				}
			}
		}
	}
}