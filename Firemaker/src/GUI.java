import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI {
	private final JDialog mainDialog;
	private final JComboBox<LOGLIST> logSelector;
	private final JComboBox<AREALIST> areaSelector;

	private boolean started;

	public GUI() {

		mainDialog = new JDialog();
		mainDialog.setTitle("eZ Firemaker");
		mainDialog.setModal(true);
		mainDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		mainDialog.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainDialog.getContentPane().add(mainPanel);

		JPanel logSelectionPanel = new JPanel();
		logSelectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel logSelectionLabel = new JLabel("Select Location & Logs:");
		logSelectionPanel.add(logSelectionLabel);

		// JLabel bankSelectionLabel = new JLabel("Select Bank:");
		// logSelectionPanel.add(bankSelectionLabel);

		areaSelector = new JComboBox<>(AREALIST.values());
		logSelectionPanel.add(areaSelector);

		logSelector = new JComboBox<>(LOGLIST.values());
		logSelectionPanel.add(logSelector);

		mainPanel.add(logSelectionPanel);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(e -> {
			started = true;
			close();
		});
		mainPanel.add(startButton);

		mainDialog.pack();

	}

	public AREALIST getSelectedBank() {
		return (AREALIST) areaSelector.getSelectedItem();
	}

	public boolean isStarted() {
		return started;
	}

	public LOGLIST getSelectedlog() {
		return (LOGLIST) logSelector.getSelectedItem();
	}

	public void open() {
		mainDialog.setVisible(true);
	}

	public void close() {
		mainDialog.setVisible(false);
		mainDialog.dispose();
	}
}

enum AREALIST {
	Grand_Exchange, Edgeville, Seers, Falador, West_Varrock, East_Varrock, Port_Phasmatys;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}

enum LOGLIST {
	Logs, Oak_logs, Willow_logs, Teak_logs, Maple_Logs, Yew_Logs, Magic_Logs, Redwood_logs;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}