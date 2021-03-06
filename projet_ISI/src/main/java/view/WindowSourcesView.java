package view;


import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import controler.MapControler;


public class WindowSourcesView {
	
	private JFrame frame;
	private MapControler controler;
	private Hashtable<String, Integer> dates = new Hashtable<String, Integer>();
	private Hashtable<String, String> countryList;
	
	public WindowSourcesView(final MapControler controler, final Hashtable<String, String> countryList) {
		this.countryList = countryList;
		this.controler = controler;
		dates.put("HomicidesIntentionnels", -1);
		dates.put("MortaliteInfantile", -1);
		dates.put("Chomage", -1);
		
		final JComboBox comboBoxHI = new JComboBox();
		final JComboBox comboBoxMI = new JComboBox();
		final JComboBox comboBoxC = new JComboBox();
		final JComboBox comboBoxPaysHI = new JComboBox();
		final JComboBox comboBoxPaysMI = new JComboBox();
		final JComboBox comboBoxPaysC = new JComboBox();
		
		JToggleButton tglbtnHI = new JToggleButton("Homicides Intentionnels");
		JToggleButton tglbtnMI = new JToggleButton("Mortalité Infantile");
		JToggleButton tglbtnC = new JToggleButton("Chômage");
		
		comboBoxHI.disable();
		comboBoxMI.disable();
		comboBoxC.disable();
		comboBoxPaysHI.disable();
		comboBoxPaysMI.disable();
		comboBoxPaysC.disable();
		
		final int[] datesHI = new int[16];
    	final int[] datesMI = new int[52];
    	final int[] datesC = new int[21];
    	
    	for(int i = 1995, j = 0; j <= datesHI.length - 1; i++, j++)
    		datesHI[j] = i;
    	for(int i = 1960, j = 0; j <= datesMI.length - 1; i++, j++)
    		datesMI[j] = i;
    	for(int i = 1990, j = 0; j <= datesC.length - 1; i++, j++)
    		datesC[j] = i;
		
		frame = new JFrame();
		frame.setBounds(800, 150, 800, 350);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tglbtnHI.setBounds(41, 68, 214, 25);
		frame.getContentPane().add(tglbtnHI);
		// Define ActionListener
	    ActionListener actionListenerHI = new ActionListener() {
	    	public void actionPerformed(ActionEvent actionEvent) {
	    		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	    		boolean selected = abstractButton.getModel().isSelected();
	    		if (selected == true){
	    			comboBoxHI.enable();
	    			comboBoxPaysHI.enable();
	    			
	    			ArrayList<String> pays = new ArrayList<String>(countryList.values());
	    			dates.put("HomicidesIntentionnels", datesHI[datesHI.length - 1]);
					controler.userQuery(dates, pays); 
	    			
	    		}
	    		else {
	    			comboBoxHI.disable();
	    			comboBoxHI.setSelectedIndex(0);
	    			comboBoxPaysHI.disable();
	    			comboBoxPaysHI.setSelectedIndex(0);
	    			
	    			ArrayList<String> pays = new ArrayList<String>(countryList.values());
	    			dates.put("HomicidesIntentionnels", -1);
	    			controler.userQuery(dates, pays);
	    		}
	        	
	    	}
	    };
	    tglbtnHI.addActionListener(actionListenerHI);
		
		comboBoxHI.setBounds(354, 68, 62, 25);
		comboBoxHI.addItem("");
		for(int k = 0; k < datesHI.length; k++)
    		comboBoxHI.addItem(datesHI[k]);
		frame.getContentPane().add(comboBoxHI);
		comboBoxHI.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (item.equals(""))	{
						ArrayList<String> pays = new ArrayList<String>(countryList.values());
		    			dates.put("HomicidesIntentionnels", datesHI[datesHI.length - 1]);
						controler.userQuery(dates, pays); 
					}
					else {
						
					ArrayList<String> pays = new ArrayList<String>(countryList.values());				
					dates.put("HomicidesIntentionnels", Integer.parseInt(item.toString()));
					controler.userQuery(dates, pays);
					}
				}
			}
		});
		
		JLabel lblAnne_1 = new JLabel("Année");
		lblAnne_1.setBounds(304, 73, 70, 15);
		frame.getContentPane().add(lblAnne_1);
		
		JLabel lblChoixDuMode = new JLabel("Choix du mode de visualisation de MyMap");
		lblChoixDuMode.setFont(new Font("Serif", Font.BOLD, 18));
		lblChoixDuMode.setBackground(Color.WHITE);
		lblChoixDuMode.setBounds(168, 12, 439, 20);
		frame.getContentPane().add(lblChoixDuMode);
		
		tglbtnMI.setBounds(41, 150, 214, 25);
		frame.getContentPane().add(tglbtnMI);
		// Define ActionListener
	    ActionListener actionListenerMI = new ActionListener() {
	    	public void actionPerformed(ActionEvent actionEvent) {
	    		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	    		boolean selected = abstractButton.getModel().isSelected();
	    		if (selected == true){
	    			comboBoxMI.enable();
	    			comboBoxPaysMI.enable();
	    			
	    			ArrayList<String> pays = new ArrayList<String>(countryList.values());
	    			dates.put("MortaliteInfantile", datesMI[datesMI.length - 1]);
					controler.userQuery(dates, pays); 
	    		}
	    		else {
	    			comboBoxMI.disable();
	    			comboBoxMI.setSelectedIndex(0);
	    			comboBoxPaysMI.disable();
	    			comboBoxPaysMI.setSelectedIndex(0);
	    			
	    			ArrayList<String> pays = new ArrayList<String>(countryList.values());
	    			dates.put("MortaliteInfantile", -1);
	    			controler.userQuery(dates, pays);
	    		}
	        	
	    	}
	    };
	    tglbtnMI.addActionListener(actionListenerMI);
		
		JLabel lblAnne_2 = new JLabel("Année");
		lblAnne_2.setBounds(304, 155, 70, 15);
		frame.getContentPane().add(lblAnne_2);
		
		comboBoxMI.setBounds(354, 150, 62, 25);
		comboBoxMI.addItem("");
		for(int k = 0; k < datesMI.length; k++)
    		comboBoxMI.addItem(datesMI[k]);
		frame.getContentPane().add(comboBoxMI);
		comboBoxMI.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (item.equals(""))	{
						ArrayList<String> pays = new ArrayList<String>(countryList.values());
		    			dates.put("MortaliteInfantile", datesMI[datesMI.length - 1]);
						controler.userQuery(dates, pays); 
					}
					else {
						ArrayList<String> pays = new ArrayList<String>(countryList.values());				
						dates.put("MortaliteInfantile", Integer.parseInt(item.toString()));
						controler.userQuery(dates, pays);
					}
				}
			}
		});
		
		tglbtnC.setBounds(41, 236, 214, 25);
		frame.getContentPane().add(tglbtnC);
		// Define ActionListener
	    ActionListener actionListenerC = new ActionListener() {
	    	public void actionPerformed(ActionEvent actionEvent) {
	    		AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	    		boolean selected = abstractButton.getModel().isSelected();
	    		if (selected == true){
	    			comboBoxC.enable();
	    			comboBoxPaysC.enable();
	    			
	    			ArrayList<String> pays = new ArrayList<String>(countryList.values());
					dates.put("Chomage", datesC[datesC.length - 1]);
					controler.userQuery(dates, pays); 
	    		}
	    		else {
	    			comboBoxC.disable();
	    			comboBoxC.setSelectedIndex(0);
	    			comboBoxPaysC.disable();
	    			comboBoxPaysC.setSelectedIndex(0);
	    			
	    			ArrayList<String> pays = new ArrayList<String>(countryList.values());
	    			dates.put("Chomage", -1);
	    			controler.userQuery(dates, pays);
	    		}
	        	
	    	}
	    };
	    tglbtnC.addActionListener(actionListenerC);
		
		JLabel lblAnne_3 = new JLabel("Année");
		lblAnne_3.setBounds(304, 241, 70, 15);
		frame.getContentPane().add(lblAnne_3);
		
		comboBoxC.setBounds(354, 236, 62, 24);
		comboBoxC.addItem("");
		for(int k = 0; k < datesC.length; k++)
    		comboBoxC.addItem(datesC[k]);
		frame.getContentPane().add(comboBoxC);
		comboBoxC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (item.equals(""))	{
						ArrayList<String> pays = new ArrayList<String>(countryList.values());
						dates.put("Chomage", datesC[datesC.length - 1]);
						controler.userQuery(dates, pays); 
					}
					else {
						ArrayList<String> pays = new ArrayList<String>(countryList.values());				
						dates.put("Chomage", Integer.parseInt(item.toString()));
						controler.userQuery(dates, pays);
					}
				}
			}
		});
		
		JLabel lblPays_1 = new JLabel("Pays");
		lblPays_1.setBounds(504, 73, 70, 15);
		frame.getContentPane().add(lblPays_1);
		
		JLabel lblPays_2 = new JLabel("Pays");
		lblPays_2.setBounds(504, 155, 70, 15);
		frame.getContentPane().add(lblPays_2);
		
		JLabel lblPays_3 = new JLabel("Pays");
		lblPays_3.setBounds(504, 241, 70, 15);
		frame.getContentPane().add(lblPays_3);
		
		comboBoxPaysHI.setBounds(544, 68, 185, 24);
		comboBoxPaysHI.addItem("");
		SortedSet<String> paysHI = new TreeSet<String>(countryList.keySet());
		for (String string : paysHI) {
			comboBoxPaysHI.addItem(string);
		}
		frame.getContentPane().add(comboBoxPaysHI);
		comboBoxPaysHI.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (item.equals(""))	{
						ArrayList<String> pays = new ArrayList<String>(countryList.values());
						controler.userQuery(dates, pays); 
					}
					else {
						ArrayList<String> pays = new ArrayList<String>(countryList.keySet());
						pays.add(countryList.get(item.toString()));
						controler.userQuery(dates, pays); 
					}
				}
				
			}
		});
		
		comboBoxPaysMI.setBounds(544, 150, 185, 24);
		comboBoxPaysMI.addItem("");
		SortedSet<String> paysMI = new TreeSet<String>(countryList.keySet());
		for (String string : paysMI) {
			comboBoxPaysMI.addItem(string);
		}
		frame.getContentPane().add(comboBoxPaysMI);
		comboBoxPaysMI.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (item.equals(""))	{
						ArrayList<String> pays = new ArrayList<String>(countryList.values());
						controler.userQuery(dates, pays); 
					}
					else {
						ArrayList<String> pays = new ArrayList<String>(countryList.keySet());
						pays.add(countryList.get(item.toString()));
						controler.userQuery(dates, pays); 
					}
				}
				
			}
		});
		
		comboBoxPaysC.setBounds(544, 236, 185, 24);
		comboBoxPaysC.addItem("");
		SortedSet<String> paysC = new TreeSet<String>(countryList.keySet());
		for (String string : paysC) {
			comboBoxPaysC.addItem(string);
		}
		frame.getContentPane().add(comboBoxPaysC);
		comboBoxPaysC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					if (item.equals(""))	{
						ArrayList<String> pays = new ArrayList<String>(countryList.values());
						controler.userQuery(dates, pays); 
					}
					else {
						ArrayList<String> pays = new ArrayList<String>(countryList.keySet());
						pays.add(countryList.get(item.toString()));
						controler.userQuery(dates, pays); 
					}
				}
				
			}
		});	
		
		frame.setVisible(true);
	}
}
