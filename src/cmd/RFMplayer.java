package cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rfm.RFMmain;
import rfm.RFMscoreboard;
import rfm.RFMutil;

public class RFMplayer implements CommandExecutor {

	private RFMmain plugin;

	public RFMplayer(RFMmain plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("admin")) {
			Player target = (Player) sender;
			if (target == null) {
				sender.sendMessage("§7プレイヤーが見つかりません。");
				return true;
			}
			if (args.length == 0) {
				RFMutil.AdminSet(target);
				sender.sendMessage("§7" + target.getName() + " を運営にしました。");
				return true;
			}
			if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					RFMutil.AdminSet(player);
					sender.sendMessage("§7" + player.getName() + " を運営にしました。");
					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("report")) {
			Player target = (Player) sender;
			if (target == null) {
				sender.sendMessage("§7プレイヤーが見つかりません。");
				return true;
			}
			if (args.length == 0) {
				RFMutil.ReportSet(target);
				sender.sendMessage("§7" + target.getName() + " を通報部隊にしました。");
				return true;
			}
			if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					RFMutil.ReportSet(player);
					sender.sendMessage("§7" + player.getName() + " を通報部隊にしました。");
					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("flee")) {
			Player target = (Player) sender;
			if (target == null) {
				sender.sendMessage("§7プレイヤーが見つかりません。");
				return true;
			}
			if (args.length == 0) {
				RFMutil.FleeSet(target);
				sender.sendMessage("§7" + target.getName() + " を逃走者にしました。");
				return true;
			}
			if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					RFMutil.FleeSet(player);
					sender.sendMessage("§7" + player.getName() + " を逃走者にしました。");
					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("hunter")) {
			Player target = (Player) sender;
			if (target == null) {
				sender.sendMessage("§7プレイヤーが見つかりません。");
				return true;
			}
			if (args.length == 0) {
				RFMutil.HunterSet(target);
				sender.sendMessage("§7" + target.getName() + " をハンターにしました。");
				return true;
			}
			if (args.length == 1) {
				if ((sender instanceof Player)) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					RFMutil.HunterSet(player);
					sender.sendMessage("§7" + player.getName() + " をハンターにしました。");
					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("jail")) {
			Player target = (Player) sender;
			if (target == null) {
				sender.sendMessage("§7プレイヤーが見つかりません。");
				return true;
			}
			if (args.length == 0) {
				RFMutil.JailSet(target);
				sender.sendMessage("§7" + target.getName() + " を牢獄者にしました。");
				return true;
			}
			if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					RFMutil.JailSet(player);
					sender.sendMessage("§7" + player.getName() + " を牢獄者にしました。");
					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("survivor")) {
			Player target = (Player) sender;
			if (target == null) {
				sender.sendMessage("§7プレイヤーが見つかりません。");
				return true;
			}
			if (args.length == 0) {
				RFMutil.SurvivorSet(target);
				sender.sendMessage("§7" + target.getName() + " を生存者にしました。");
				return true;
			}
			if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = Bukkit.getServer().getPlayer(args[0]);
					RFMutil.SurvivorSet(player);
					sender.sendMessage("§7" + player.getName() + " を生存者にしました。");
					return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("aj")) {
			if (sender instanceof Player) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.setScoreboard(RFMscoreboard.sb);
					RFMscoreboard.ResetScoreboard(RFMmain.time);
					RFMscoreboard.TosoChangeScoreboard(plugin.getConfig().getInt("RFM.setting.time" + 30));
					RFMutil.FleeSet(players);
				}
				sender.sendMessage("§7全プレイヤーを逃走者にしました。");
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void checkGameEnd() {
		if ((RFMmain.game) && (RFMmain.teamFlee.getPlayers().size() == 0)) {
			RFMset.GameEnd();
		}
	}
}
