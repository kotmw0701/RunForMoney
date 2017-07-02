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

import rfm.RFMmain;
import rfm.RFMscoreboard;

public class RFMmission implements CommandExecutor {

	private RFMmain plugin;

	public static String prefix = "§4[RFM]§f ";

	public RFMmission(RFMmain plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mission")) {
			if (args.length < 2 ) {
				sender.sendMessage(ChatColor.GREEN + "/mission [番号] [ミッション時間]");
				return false;
			}
			try {
				if (args[0].equals("1")) mission(1, args[1]);
				else if (args[0].equals("2")) mission(2, args[1]);
				else if (args[0].equals("3")) mission(3, args[1]);
				else if (args[0].equals("4")) mission(4, args[1]);
				else if (args[0].equals("5")) mission(5, args[1]);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	
	private void mission(int missionnum, String timestr) throws NumberFormatException, ArrayIndexOutOfBoundsException{
		List<String> mission5 = plugin.getConfig().getStringList("mission"+missionnum);
		String content = "";
		for (String str : mission5) content += str + "\n";
		content = ChatColor.translateAlternateColorCodes('&', content);

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setTitle("§e§l§oRunForMoney Mission "+missionnum);
		List<String> pages = meta.getPages();
		pages.add(0, content);
		meta.setPages(pages);
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
				
				RFMmain.bossbar.addPlayer(players);
				new BukkitRunnable() {
					int time = Integer.parseInt(timestr);
					
					@Override
					public void run() {
						if (time > 0) {
							RFMmain.bossbar.setTitle(ChatColor.RED + "Mission "+missionnum+" 発令中！ " + ChatColor.GOLD
									+ "ミッション終了まで " + ChatColor.GREEN + RFMscoreboard.Time(time));
							time--;
						} else {
							RFMmain.bossbar.removePlayer(players);
							cancel();
						}
					}
				}.runTaskTimer(plugin, 0, 20);
			}
		}
	}
}
