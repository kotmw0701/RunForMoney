package rfm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import cmd.RFMhunterbox;
import cmd.RFMplayer;
import cmd.RFMset;
import listener.RFMitem;
import listener.RFMlistener;
import listener.RFMsettingtool;
import mission.RFMmission;
import mission.RFMnotification;
import warn.RFMfw;
import warn.RFMfwarn;
import warn.RFMhw;
import warn.RFMhwarn;

@SuppressWarnings("deprecation")
public class RFMmain extends JavaPlugin {

    public static RFMmain plugin;

    static final String TEAM_ADMIN_NAME = "team_admin";
    static final String TEAM_REPORT_NAME = "team_report";
    static final String TEAM_FLEE_NAME = "team_flee";
    static final String TEAM_HUNTER_NAME = "team_hunter";
    static final String TEAM_JAIL_NAME = "team_jail";
    static final String TEAM_SURVIVOR_NAME = "team_survivor";

    public static boolean game = false;
    public static boolean reset = false;
    public static boolean relese = true;

    public static Team teamAdmin;
    public static Team teamReport;
    public static Team teamFlee;
    public static Team teamHunter;
    public static Team teamJail;
    public static Team teamSurvivor;

    public static String prefix = "§4[RFM] §f";

    public static int time;
    
    public static BossBar bossbar = Bukkit.createBossBar("", BarColor.PURPLE, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);

	public static final String StatsBaseDirPath = "plugins/RunForMoney/";

	public static final String LocationStatsFilePath = StatsBaseDirPath + "location.yml";

	public static final YamlConfiguration LocationStatsYaml = new YamlConfiguration();

	public void onEnable() {
        plugin = this;

        loadLocation();
        saveLocation();
        
        RFMscoreboard.createScoreBoard(); 

        time = plugin.getConfig().getInt("RFM.setting.time") + 30;

        this.saveDefaultConfig();

        getCommand("bo").setExecutor(new RFMhunterbox());
        getCommand("admin").setExecutor(new RFMplayer(this));
        getCommand("report").setExecutor(new RFMplayer(this));
        getCommand("flee").setExecutor(new RFMplayer(this));
        getCommand("hunter").setExecutor(new RFMplayer(this));
        getCommand("jail").setExecutor(new RFMplayer(this));
        getCommand("survivor").setExecutor(new RFMplayer(this));
        getCommand("aj").setExecutor(new RFMplayer(this));
        getCommand("rfm").setExecutor(new RFMset(this));
        getCommand("mission").setExecutor(new RFMmission(this));
        getCommand("notification").setExecutor(new RFMnotification(this));
        getCommand("rfmreload").setExecutor(this);
        getCommand("fw").setExecutor(new RFMfw());
        getCommand("fwarn").setExecutor(new RFMfwarn());
        getCommand("hw").setExecutor(new RFMhw());
        getCommand("hwarn").setExecutor(new RFMhwarn());

        getServer().getPluginManager().registerEvents(new RFMitem(this), this);
        getServer().getPluginManager().registerEvents(new RFMlistener(this), this);
        getServer().getPluginManager().registerEvents(new RFMsettingtool(), this);
        getServer().getPluginManager().registerEvents(new RFMutil(this), this);
        
        for (Player players : Bukkit.getOnlinePlayers()) {
        		players.setHealth(20);
        		if (players.getScoreboard().getPlayerTeam(players) != null) {
            		if (!players.getScoreboard().getPlayerTeam(players).getName().equalsIgnoreCase("team_hunter")) {
            			players.setWalkSpeed(0.25F);
            		}
        		}
        }

        teamAdmin = RFMscoreboard.sb.getTeam("team_admin");
        if (teamAdmin != null) {
            teamAdmin.unregister();
            teamAdmin = RFMscoreboard.sb.registerNewTeam("team_admin");
            teamAdmin.setPrefix(ChatColor.GOLD.toString());
            teamAdmin.setSuffix(ChatColor.RESET.toString());
            teamAdmin.setAllowFriendlyFire(false);
            teamAdmin.setCanSeeFriendlyInvisibles(true);
            teamAdmin.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        if (teamAdmin == null) {
            teamAdmin = RFMscoreboard.sb.registerNewTeam("team_admin");
            teamAdmin.setPrefix(ChatColor.GOLD.toString());
            teamAdmin.setSuffix(ChatColor.RESET.toString());
            teamAdmin.setAllowFriendlyFire(false);
            teamAdmin.setCanSeeFriendlyInvisibles(true);
            teamAdmin.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        teamReport = RFMscoreboard.sb.getTeam("team_report");
        if (teamReport != null) {
            teamReport.unregister();
            teamReport = RFMscoreboard.sb.registerNewTeam("team_report");
            teamReport.setPrefix(ChatColor.LIGHT_PURPLE.toString());
            teamReport.setSuffix(ChatColor.RESET.toString());
            teamReport.setAllowFriendlyFire(false);
            teamReport.setCanSeeFriendlyInvisibles(true);
            teamAdmin.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        if (teamReport == null) {
            teamReport = RFMscoreboard.sb.registerNewTeam("team_report");
            teamReport.setPrefix(ChatColor.LIGHT_PURPLE.toString());
            teamReport.setSuffix(ChatColor.RESET.toString());
            teamReport.setAllowFriendlyFire(false);
            teamReport.setCanSeeFriendlyInvisibles(true);
            teamAdmin.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        teamFlee = RFMscoreboard.sb.getTeam("team_flee");
        if (teamFlee != null) {
            teamFlee.unregister();
            teamFlee = RFMscoreboard.sb.registerNewTeam("team_flee");
            teamFlee.setPrefix(ChatColor.GRAY.toString());
            teamFlee.setSuffix(ChatColor.RESET.toString());
            teamFlee.setAllowFriendlyFire(false);
            teamFlee.setCanSeeFriendlyInvisibles(true);
            teamFlee.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        if (teamFlee == null) {
            teamFlee = RFMscoreboard.sb.registerNewTeam("team_flee");
            teamFlee.setPrefix(ChatColor.GRAY.toString());
            teamFlee.setSuffix(ChatColor.RESET.toString());
            teamFlee.setAllowFriendlyFire(false);
            teamFlee.setCanSeeFriendlyInvisibles(true);
            teamFlee.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        teamHunter = RFMscoreboard.sb.getTeam("team_hunter");
        if (teamHunter != null) {
            teamHunter.unregister();
            teamHunter = RFMscoreboard.sb.registerNewTeam("team_hunter");
            teamHunter.setPrefix(ChatColor.DARK_RED.toString());
            teamHunter.setSuffix(ChatColor.RESET.toString());
            teamHunter.setAllowFriendlyFire(false);
            teamHunter.setCanSeeFriendlyInvisibles(true);
            teamHunter.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        if (teamHunter == null) {
            teamHunter = RFMscoreboard.sb.registerNewTeam("team_hunter");
            teamHunter.setPrefix(ChatColor.DARK_RED.toString());
            teamHunter.setSuffix(ChatColor.RESET.toString());
            teamHunter.setAllowFriendlyFire(false);
            teamHunter.setCanSeeFriendlyInvisibles(true);
            teamHunter.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        teamJail = RFMscoreboard.sb.getTeam("team_jail");
        if (teamJail != null) {
            teamJail.unregister();
            teamJail = RFMscoreboard.sb.registerNewTeam("team_jail");
            teamJail.setPrefix(ChatColor.BLACK.toString());
            teamJail.setSuffix(ChatColor.RESET.toString());
            teamJail.setAllowFriendlyFire(false);
            teamJail.setCanSeeFriendlyInvisibles(true);
            teamJail.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        if (teamJail == null) {
            teamJail = RFMscoreboard.sb.registerNewTeam("team_jail");
            teamJail.setPrefix(ChatColor.BLACK.toString());
            teamJail.setSuffix(ChatColor.RESET.toString());
            teamJail.setAllowFriendlyFire(false);
            teamJail.setCanSeeFriendlyInvisibles(true);
            teamJail.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        teamSurvivor = RFMscoreboard.sb.getTeam("team_survivor");
        if (teamSurvivor != null) {
            teamSurvivor.unregister();
            teamSurvivor = RFMscoreboard.sb.registerNewTeam("team_survivor");
            teamSurvivor.setPrefix(ChatColor.GREEN.toString());
            teamSurvivor.setSuffix(ChatColor.RESET.toString());
            teamSurvivor.setAllowFriendlyFire(false);
            teamSurvivor.setCanSeeFriendlyInvisibles(true);
            teamSurvivor.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        if (teamSurvivor == null) {
            teamSurvivor = RFMscoreboard.sb.registerNewTeam("team_survivor");
            teamSurvivor.setPrefix(ChatColor.GREEN.toString());
            teamSurvivor.setSuffix(ChatColor.RESET.toString());
            teamSurvivor.setAllowFriendlyFire(false);
            teamSurvivor.setCanSeeFriendlyInvisibles(true);
            teamSurvivor.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }
    }

    @Override
    public void onDisable() {
        RFMutil.Allremove();
        
        saveLocation();
    }

	public static void loadLocation() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(LocationStatsFilePath)).exists()) {
			try {
				(new File(LocationStatsFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			LocationStatsYaml.load((new File(LocationStatsFilePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void saveLocation() {
		if (!(new File(StatsBaseDirPath)).exists()) {
			(new File(StatsBaseDirPath)).mkdir();
		}
		if (!(new File(LocationStatsFilePath)).exists()) {
			try {
				(new File(LocationStatsFilePath)).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			LocationStatsYaml.save(LocationStatsFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static ItemStack setName(ItemStack is, String name) {
        ItemMeta im = is.getItemMeta();
        if (name != null) {
            im.setDisplayName(name);
        }
        is.setItemMeta(im);
        return is;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rfmreload")) {
            reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "RunForMoneyのConfigがリロードしました。");
        }
        return false;
    }
}