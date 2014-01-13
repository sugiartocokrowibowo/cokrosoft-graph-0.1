package com.gie.scw;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class CokrosoftGraph extends JFrame {
	
	/**
	 * @author sugiarto cokrowibowo
	 * @author sugiartocokrowibowo@gmail.com
	 * @category Mathematics and Computer Science
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JTabbedPane jTabbedPane = null;
	private PanelGraph jPanelGraph1 = null;
	private JPanel jPanel3 = null;
	private JButton jButton = null;

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Graph", null, getJPanelGraph1(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanelGraph1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private PanelGraph getJPanelGraph1() {
		if (jPanelGraph1 == null) {
			jPanelGraph1 = new PanelGraph();
			jPanelGraph1.setLayout(new BorderLayout());
			jPanelGraph1.add(getJPanel3(), BorderLayout.SOUTH);
		}
		return jPanelGraph1;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.add(getJButton(), new GridBagConstraints());
		}
		return jPanel3;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Set Edge");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jPanelGraph1.status==0){
						jPanelGraph1.status=1;
						jButton.setText("Set Vertex");
					}else{
						jPanelGraph1.status=0;
						jButton.setText("Set Edge");
					}
					
					System.out.println("actionPerformed(setEdge)"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CokrosoftGraph thisClass = new CokrosoftGraph();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public CokrosoftGraph() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
        } catch (Exception e) {}
		this.setSize(450, 680);
		this.setContentPane(getJContentPane());
		this.setTitle("Cokrosoft Graph");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
