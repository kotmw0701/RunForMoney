package warn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RFMhw implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("hw"))
			;
		player.sendMessage(ChatColor.DARK_RED + "[RFM]" + ChatColor.WHITE + "---" + ChatColor.AQUA + "<禁止事項-ハンター>");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "逃走者を視界に捉えていないのに走る行為");
		player.sendMessage(ChatColor.AQUA + "(通報部隊投入による通報を受けた場合は見つけてなくても走って構いません)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ゲーム終了前にサーバーから抜ける行為(例外,有)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "同じ場所に留まる行為(芋行為)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "放送を見ながらの捜索(音声もNG)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "緊急時以外のチャット");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "卵の意図的な回避");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		return false;
	}
}
