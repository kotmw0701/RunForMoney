package warn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rfm.RFMmain;

public class RFMfwarn implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fwarn"))
			;
		Bukkit.broadcastMessage(RFMmain.prefix + ChatColor.WHITE + "---" + ChatColor.AQUA + "<禁止事項-逃走者>");
		Bukkit.broadcastMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ハンターのいけない所に行く行為(ダッシュジャンプのみで行けるなど)");
		Bukkit.broadcastMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ハンターをアイテムなどで煽る行為");
		Bukkit.broadcastMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "特定のミッション以外でのアイテム受け渡し");
		Bukkit.broadcastMessage(ChatColor.GREEN + "-----------------------------------------------------");
		return false;
	}
}
