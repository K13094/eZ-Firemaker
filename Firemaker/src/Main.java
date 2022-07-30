import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import util.MouseCursor;
import util.MouseTrail;

@ScriptManifest(author = "A&K", info = "Most up to date firemaking bot. Supporting Multiple locations", name = "ez Firemaker", version = 1.1, logo = "https://i.imgur.com/9nUfZKI.png")
public class Main extends Script {

	String pLog;

	// PAINT + GUI
	private MouseTrail trail = new MouseTrail(0, 255, 255, 2000, this);
	private MouseCursor cursor = new MouseCursor(25, 3, Color.ORANGE, this);
	private int beginningXP;
	private int currentXp;
	private int xpGained;
	private long XpHourly;
	private long startTime = System.currentTimeMillis();
	private GUI gui = new GUI();
	private LOGLIST loglist;
	private AREALIST arealist;

	// items
	int TINDERBOX = 590;

	// LOGS
	int selectedLog;
	int Normal_Logs = 1511;
	int Oak_Logs = 1521;
	int WILLOW_LOGS = 1519;
	int TEAK_LOGS = 6333;
	int MAPLE_LOGS = 1517;
	int YEW_LOGS = 1515;
	int MAGIC_LOGS = 1513;
	int REDWOOD_LOGS = 19669;

	// XAREA
	Area PORTPHASMATYS_XAREA = new Area(3699, 3475, 3663, 3472);// phasmatys accepted area
	Area EDGEVILLE_XAREA = new Area(3111, 3505, 3074, 3500);// edge xarea
	Area FALADOR_XAREA = new Area(3032, 3366, 2997, 3359);// falador accepted area
	Area SEERS_XAREA = new Area(2733, 3484, 2706, 3486);// seers accepted area
	Area GE_XAREA = new Area(3146, 3506, 3193, 3473);// ge xarea
	Area VARROCKEAST_XAREA = new Area(3290, 3430, 3240, 3428);// east varrock x area
	Area VARROCKWEST_XAREA = new Area(3173, 3432, 3209, 3428);// west varrock xarea

	// BANKS
	Area GE_BANK = new Area(3160, 3494, 3169, 3485);// ge bank
	Area VARROCKWEST_BANK = new Area(3180, 3447, 3185, 3433);//
	Area VARROCKEAST_BANK = new Area(3252, 3423, 3254, 3420); // east varrock bank
	Area SEERS_BANK = new Area(2730, 3493, 2721, 3490);// seers bank
	Area FALADOR_EASTBANK = new Area(3009, 3358, 3018, 3355);// // falador east bank
	Area DRAYNOR_BANK = new Area(3091, 3246, 3096, 3240);// draynor bank area
	Area EDGEVILLE_BANK = new Area(3098, 3499, 3091, 3488);// edgeville bank
	Area PORTPHASMATYS_BANK = new Area(3691, 3471, 3686, 3461);// port phasmatys bank area

	// FMing START AREAS
	Area[] FALADOREAST_STARTAREA = { new Area(3032, 3359, 3031, 3359), new Area(3032, 3360, 3031, 3360),
			new Area(3032, 3361, 3031, 3361), new Area(3032, 3362, 3031, 3362), new Area(3030, 3362, 3030, 3361),
			new Area(3030, 3360, 3030, 3359) };// falador start area
	Area SEERS_STARTAREA[] = { new Area(2734, 3486, 2733, 3486), new Area(2733, 3485, 2732, 3485),
			new Area(2732, 3486, 2731, 3486), new Area(2733, 3484, 2730, 3484), new Area(2731, 3485, 2730, 3485),
			new Area(2730, 3486, 2730, 3486) };// seers start area// seers start area
	Area VARROCKWEST_STARTAREA[] = { new Area(3208, 3429, 3207, 3429), new Area(3208, 3430, 3207, 3430),
			new Area(3208, 3428, 3207, 3428), new Area(3200, 3431, 3199, 3431), new Area(3200, 3432, 3199, 3432) };// west
	Area VARROCKEAST_STARTAREA[] = { new Area(3281, 3429, 3282, 3429), new Area(3281, 3428, 3282, 3428),
			new Area(3285, 3429, 3285, 3428), new Area(3286, 3428, 3286, 3429), new Area(3284, 3428, 3284, 3429),
			new Area(3283, 3428, 3283, 3429) };// varrock-east star
	Area[] DRAYNOR_STARTAREA = { new Area(3105, 3250, 3103, 3250), new Area(3097, 3249, 3096, 3249),
			new Area(3097, 3248, 3096, 3248), new Area(3097, 3247, 3096, 3247), new Area(3097, 3250, 3096, 3250),
			new Area(3095, 3250, 3095, 3247) };// draynor start area
	Area[] GE_STARTAREA = { new Area(3181, 3506, 3178, 3504), new Area(3181, 3503, 3178, 3501),
			new Area(3180, 3496, 3176, 3497), new Area(3188, 3491, 3195, 3488), new Area(3174, 3483, 3180, 3482),
			new Area(3177, 3475, 3171, 3477) };// ge fm start area
	Area[] EDGEVILLE_STARTAREA = { new Area(3122, 3508, 3119, 3508), new Area(3116, 3507, 3112, 3507),
			new Area(3111, 3505, 3107, 3505), new Area(3106, 3504, 3104, 3504), new Area(3112, 3502, 3109, 3502),
			new Area(3106, 3503, 3104, 3503) };// edgeville fm start area
	Area[] PORTPHASMATYS_STARTAREA = { new Area(3701, 3472, 3700, 3472), new Area(3701, 3473, 3700, 3473),
			new Area(3701, 3474, 3700, 3474), new Area(3701, 3475, 3700, 3475) };// phasmatys fm area start phasmatys fm
																					// area start

	public void bank() throws InterruptedException {
		if (!getBankArea().contains(myPlayer())) {
			pLog = "Walking to start area.";
			walkToBankArea();
		}
		if (getBankArea().contains(myPlayer()) && !checkTinderbox()) {
			pLog = "Withdrawing tinderbox & logs";
			getBank().open();
			sleep(random(800, 1600));
			getBank().withdraw(TINDERBOX, 1);
			sleep(random(800, 1600));
			withdrawLogs();
		} else if (checkTinderbox() && getBankArea().contains(myPlayer())) {
			pLog = "Withdrawing logs";
			withdrawLogs();
		}
	}

	public void checkInventory() throws InterruptedException {
		if (!getInventory().onlyContains(getLogs(), TINDERBOX)) {
			pLog = "Banking wrong items...";
			walkToBankArea();
			getBank().open();
			sleep(random(1000, 2000));
			getBank().depositAllExcept(TINDERBOX, getLogs());
		}
	}

	public void walkToBankArea() {
		if ((!getInventory().contains(TINDERBOX) && !getInventory().contains(getLogs())
				|| (!getInventory().contains(getLogs()) && !myPlayer().isAnimating()))) {
			pLog = "Walking to " + arealist.toString();
			getWalking().webWalk(getBankArea());
		}
	}

	public void walkToFMArea() {
		if (checkTinderbox() && getInventory().contains(getLogs())) {
			pLog = "Walking to a random tile";
			getWalking().webWalk(getFMArea());
		}
	}

	public boolean checkTinderbox() {
		return getInventory().contains(TINDERBOX, 1);
	}

	public void withdrawLogs() throws InterruptedException {
		if (!getInventory().contains(getLogs()) && !myPlayer().isAnimating()) {
			if (getBankArea().contains(myPlayer())) {
				getBank().open();
				sleep(random(800, 1600));
				pLog = "Withdrawing logs...";
				getBank().withdrawAll(getLogs());
				sleep(random(800, 1600));
			}
			if (!getBank().contains(getLogs())) {
				log("No logs left. Stopping script.");
				stop();
			}
		}
	}

	public void firemaking() throws InterruptedException {
		// Checks to see if player is in the firemaking area and has a tinderbox and
		// logs
		if (getInventory().contains(TINDERBOX, 1)
				&& (getInventory().contains(getLogs()) && xArea().contains(myPosition()))) {
			if (checkSpot()) {
				pLog = "Changing spot...";
				walkToFMArea();
			}
			pLog = "Using tinderbox..";
			getInventory().interact("Use", TINDERBOX);
			if (myPlayer().isAnimating()) {
				getInventory().hover(getLogs());
			}
			if (!myPlayer().isAnimating()) {
				pLog = "Lighting logs..";
				getInventory().interact("Use", getLogs());
				sleep(random(600, 1200));
			}
			// check if the inventory contains logs / walks to fm area
		} else if (getInventory().contains(getLogs())) {
			pLog = "Walking to " + getFMArea();
			walkToFMArea();
		}
	}

	public boolean checkSpot() {
		for (RS2Object o : objects.getAll()) {
			if (o.getName().equals("Fire") || o.getName().equals("Daisies")) {
				if (o.getPosition().getX() == myPlayer().getX() && o.getPosition().getY() == myPlayer().getY()) {
					pLog = "Found: " + o.getName() + "! Moving to new tile..";
					return true;
				}
			}
		}
		return false;
	}

	public Area xArea() {
		if (arealist.toString().equals("east_varrock")) {
			return VARROCKEAST_XAREA;
		} else if (arealist.toString().equals("west_varrock")) {
			return VARROCKWEST_XAREA;
		} else if (arealist.toString().equals("edgeville")) {
			return EDGEVILLE_XAREA;
		} else if (arealist.toString().equals("grand_exchange")) {
			return GE_XAREA;
		} else if (arealist.toString().equals("seers")) {
			return SEERS_XAREA;
		} else if (arealist.toString().equals("falador")) {
			return FALADOR_XAREA;
		} else if (arealist.toString().equals("port_phasmatys"))
			return PORTPHASMATYS_XAREA;
		return null;
	}

	public Area getBankArea() {
		if (arealist.toString().equals("east_varrock")) {
			return VARROCKEAST_BANK;
		} else if (arealist.toString().equals("west_varrock")) {
			return VARROCKWEST_BANK;
		} else if (arealist.toString().equals("edgeville")) {
			return EDGEVILLE_BANK;
		} else if (arealist.toString().equals("grand_exchange")) {
			return GE_BANK;
		} else if (arealist.toString().equals("seers")) {
			return SEERS_BANK;
		} else if (arealist.toString().equals("falador")) {
			return FALADOR_EASTBANK;
		} else if (arealist.toString().equals("port_phasmatys"))
			return PORTPHASMATYS_BANK;
		return null;
	}

	public Area getFMArea() {
		if (arealist.toString().equals("east_varrock")) {
			return VARROCKEAST_STARTAREA[random(0, 5)];
		} else if (arealist.toString().equals("west_varrock")) {
			return VARROCKWEST_STARTAREA[random(0, 5)];
		} else if (arealist.toString().equals("edgeville")) {
			return EDGEVILLE_STARTAREA[random(0, 5)];
		} else if (arealist.toString().equals("grand_exchange")) {
			return GE_STARTAREA[random(0, 5)];
		} else if (arealist.toString().equals("seers")) {
			return SEERS_STARTAREA[random(0, 5)];
		} else if (arealist.toString().equals("falador")) {
			return FALADOREAST_STARTAREA[random(0, 5)];
		} else if (arealist.toString().equals("port_phasmatys")) {
			return PORTPHASMATYS_STARTAREA[random(0, 3)];
		}
		return null;
	}

	public int getLogs() {
		if (loglist.toString().equals("logs")) {
			return Normal_Logs;
		} else if (loglist.toString().equals("oak_logs")) {
			return Oak_Logs;
		} else if (loglist.toString().equals("willow_logs")) {
			return WILLOW_LOGS;
		} else if (loglist.toString().equals("teak_logs")) {
			return TEAK_LOGS;
		} else if (loglist.toString().equals("maple_logs")) {
			return MAPLE_LOGS;
		} else if (loglist.toString().equals("yew_logs")) {
			return YEW_LOGS;
		} else if (loglist.toString().equals("magic_logs")) {
			return MAGIC_LOGS;
		} else if (loglist.toString().equals("redwood_logs")) {
			return REDWOOD_LOGS;
		}
		return 0;
	}

	@Override
	public int onLoop() throws InterruptedException {
		checkInventory();
		walkToBankArea();
		withdrawLogs();
		firemaking();
		return random(200, 300);
	}

	@Override
	public void onStart() throws InterruptedException {
		try {
			SwingUtilities.invokeAndWait(() -> {
				beginningXP = skills.getExperience(Skill.FIREMAKING);
				pLog = "Starting up...";
				log("ez Firemaker starting.");
				gui = new GUI();
				gui.open();
			});
		} catch (InterruptedException | InvocationTargetException e) {
			e.printStackTrace();
			stop();
			return;
		}

		if (!gui.isStarted()) {
			stop();
			return;
		}
		loglist = gui.getSelectedlog();
		arealist = gui.getSelectedBank();
		checkInventory();
		bank();
	}

	@Override
	public void onExit() {
		log("Thanks for using eZ Firemaker!");
	}

	@Override
	public void onPaint(Graphics2D g) {
		trail.paint(g);
		cursor.paint(g);

		currentXp = skills.getExperience(Skill.FIREMAKING);
		xpGained = currentXp - beginningXP;
		XpHourly = (int) (xpGained / ((System.currentTimeMillis() - startTime) / 3600000.0D));

		g.setColor(Color.ORANGE);
		g.drawString("eZ Firemaker", 8, 255);
		g.drawString("Run time: " + String.valueOf(formatTime(System.currentTimeMillis() - startTime)), 8, 275);
		g.drawString("Firemaking xp gained:  " + xpGained, 8, 295);
		g.drawString("Firemaking xp p/h :  " + XpHourly, 8, 315);
		g.drawString("Status: " + pLog, 8, 335);

		final Color BlockName = new Color(208, 188, 149);
		Graphics2D gr = g;
		gr.setColor(BlockName);
		gr.fillRect(7, 460, 100, 14);

	}

	private String formatTime(final long ms) {
		long s = ms / 1000, m = s / 60, h = m / 60;
		s %= 60;
		m %= 60;
		h %= 24;
		return String.format("%02d:%02d:%02d", h, m, s);
	}

}