package rfm;

public class RFMscore {

	public static int JoinTeam(String team) {
		return RFMscoreboard.sb.getTeam(team).getSize();
	}

	public static int TeamSize(String teamname) {
		return RFMscoreboard.sb.getTeam(teamname).getSize();
	}
}