package listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RFMsettingtool implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void SettingTool(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if (player.getScoreboard().getPlayerTeam(player) == null) {
				return;
			}

			if (player.getScoreboard().getPlayerTeam(player).getName().equalsIgnoreCase("team_admin")) {
				if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
						|| (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
					if (player.getItemInHand().getType().equals(Material.INK_SACK)) {
						switch (player.getItemInHand().getData().getData()) {
						case (byte) 8:
							player.performCommand("rfm set fs");
							break;
						case (byte) 1:
							player.performCommand("rfm set hs");
							break;
						case (byte) 0:
							player.performCommand("rfm set js");
							break;
						case (byte) 10:
							player.performCommand("rfm set rs");
							break;
						case (byte) 14:
							player.performCommand("rfm set rs");
							break;
						}
					}
				}
			}
		}
	}
}