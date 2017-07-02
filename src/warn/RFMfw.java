package warn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RFMfw implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("fw"))
			;
		player.sendMessage(ChatColor.DARK_RED + "[RFM]" + ChatColor.WHITE + "---" + ChatColor.AQUA + "<禁止事項-逃走者>");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ハンターのいけない所に行く行為(ダッシュジャンプのみで行けるなど)");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "ハンターをアイテムなどで煽る行為");
		player.sendMessage(ChatColor.YELLOW + "*" + ChatColor.AQUA + "特定のミッション以外でのアイテム受け渡し");
		player.sendMessage(ChatColor.GREEN + "-----------------------------------------------------");
		return false;
	}
}
