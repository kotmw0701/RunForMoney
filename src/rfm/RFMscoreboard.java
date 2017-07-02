package rfm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class RFMscoreboard {

	public static Scoreboard sb;

	public static void createScoreBoard() {
		sb = Bukkit.getScoreboardManager().getNewScoreboard();

		Objective obj = sb.registerNewObjective("RFM", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.DARK_RED + "-" + ChatColor.BOLD + "§oRunForMoney-");
	}

	public static List<String> TosoScoreboard(int i1) {
		List<String> board = new ArrayList<String>();

		board.add(ChatColor.RED + "<>-逃走時間-<>");
		board.add(ChatColor.RESET.toString());
		board.add(ChatColor.YELLOW + Time(i1));
		board.add(ChatColor.RESET.toString() + ChatColor.RESET.toString());
		board.add(ChatColor.AQUA + "<>-各チーム人数-<>");
		board.add(ChatColor.GOLD + "運営: " + RFMscore.TeamSize("team_admin"));
		board.add(ChatColor.LIGHT_PURPLE + "通報部隊: " + RFMscore.TeamSize("team_report"));
		board.add(ChatColor.GRAY + "逃走者: " + RFMscore.TeamSize("team_flee"));
		board.add(ChatColor.DARK_RED + "ハンター: " + RFMscore.TeamSize("team_hunter"));
		board.add(ChatColor.BLACK + "牢獄者: " + RFMscore.TeamSize("team_jail"));
		board.add(ChatColor.GREEN + "生存者: " + RFMscore.TeamSize("team_survivor"));
		return board;
	}

	public static void TosoChangeScoreboard(int i1) {
		Objective obj = sb.getObjective("RFM");

		int i2 = RFMscore.TeamSize("team_admin") - 1;// 増えた場合
		Score add1 = obj.getScore(ChatColor.GOLD + "運営: " + i2);
		add1.setScore(0);
		sb.resetScores(ChatColor.GOLD + "運営: " + i2);

		int i3 = RFMscore.TeamSize("team_admin") + 1;// 減った場合
		Score remove1 = obj.getScore(ChatColor.GOLD + "運営: " + i3);
		remove1.setScore(0);
		sb.resetScores(ChatColor.GOLD + "運営: " + i3);

		int i4 = RFMscore.TeamSize("team_report") - 1;// 増えた場合
		Score add2 = obj.getScore(ChatColor.LIGHT_PURPLE + "通報部隊: " + i4);
		add2.setScore(0);
		sb.resetScores(ChatColor.LIGHT_PURPLE + "通報部隊: " + i4);

		int i5 = RFMscore.TeamSize("team_report") + 1;// 減った場合
		Score remove2 = obj.getScore(ChatColor.LIGHT_PURPLE + "通報部隊: " + i5);
		remove2.setScore(0);
		sb.resetScores(ChatColor.LIGHT_PURPLE + "通報部隊: " + i5);

		int i6 = RFMscore.TeamSize("team_flee") - 1;// 増えた場合
		Score add3 = obj.getScore(ChatColor.GRAY + "逃走者: " + i6);
		add3.setScore(0);
		sb.resetScores(ChatColor.GRAY + "逃走者: " + i6);

		int i7 = RFMscore.TeamSize("team_flee") + 1;// 減った場合
		Score remove3 = obj.getScore(ChatColor.GRAY + "逃走者: " + i7);
		remove3.setScore(0);
		sb.resetScores(ChatColor.GRAY + "逃走者: " + i7);

		int i8 = RFMscore.TeamSize("team_hunter") - 1;// 増えた場合
		Score add4 = obj.getScore(ChatColor.DARK_RED + "ハンター: " + i8);
		add4.setScore(0);
		sb.resetScores(ChatColor.DARK_RED + "ハンター: " + i8);

		int i9 = RFMscore.TeamSize("team_hunter") + 1;// 減った場合
		Score remove4 = obj.getScore(ChatColor.DARK_RED + "ハンター: " + i9);
		remove4.setScore(0);
		sb.resetScores(ChatColor.DARK_RED + "ハンター: " + i9);

		int i10 = RFMscore.TeamSize("team_jail") - 1;// 増えた場合
		Score add5 = obj.getScore(ChatColor.BLACK + "牢獄者: " + i10);
		add5.setScore(0);
		sb.resetScores(ChatColor.BLACK + "牢獄者: " + i10);

		int i11 = RFMscore.TeamSize("team_jail") + 1;// 減った場合
		Score remove5 = obj.getScore(ChatColor.BLACK + "牢獄者: " + i11);
		remove5.setScore(0);
		sb.resetScores(ChatColor.BLACK + "牢獄者: " + i11);

		int i12 = RFMscore.TeamSize("team_survivor") - 1;// 増えた場合
		Score add6 = obj.getScore(ChatColor.GREEN + "生存者: " + i12);
		add6.setScore(0);
		sb.resetScores(ChatColor.GREEN + "生存者: " + i12);

		int i13 = RFMscore.TeamSize("team_survivor") + 1;// 減った場合
		Score remove6 = obj.getScore(ChatColor.GREEN + "生存者: " + i13);
		remove6.setScore(0);
		sb.resetScores(ChatColor.GREEN + "生存者: " + i13);

		Score beforescore = obj.getScore(ChatColor.YELLOW + RFMscoreboard.Time(i1 + 1));
		beforescore.setScore(0);
		RFMscoreboard.sb.resetScores(ChatColor.YELLOW + RFMscoreboard.Time(i1 + 1));

		Score zeroscore = obj.getScore(ChatColor.YELLOW + RFMscoreboard.Time(0));
		zeroscore.setScore(0);
		RFMscoreboard.sb.resetScores(ChatColor.YELLOW + RFMscoreboard.Time(0));

		Score configscore = obj.getScore(ChatColor.YELLOW + RFMscoreboard.Time(630));
		configscore.setScore(0);
		RFMscoreboard.sb.resetScores(ChatColor.YELLOW + RFMscoreboard.Time(630));

		int i = TosoScoreboard(i1).size() - 1;
		for (String text : TosoScoreboard(i1)) {
			Score test = obj.getScore(text);
			test.setScore(i);
			i--;
		}
	}

	public static void ResetScoreboard(int i1) {
		Objective obj = sb.getObjective("RFM");
		for (String text : TosoScoreboard(i1)) {
			Score test = obj.getScore(text);
			test.setScore(0);
			RFMscoreboard.sb.resetScores(text);
		}
	}

	public static String Time(int time) {
		int second = time % 60;
		int minute = time / 60;
		if (String.valueOf(second).length() == 2) {
			return minute + " : " + second;
		}
		return minute + " : 0" + second;
	}
}