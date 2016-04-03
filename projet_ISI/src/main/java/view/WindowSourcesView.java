package view;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.geotools.referencing.operation.projection.NewZealandMapGrid;

import model.MapChangedEvent;

import controler.MapControler;


@SuppressWarnings("serial")
public class WindowSourcesView {
	
	private JFrame frame;
	private MapControler controler;
	private Hashtable<String, Integer> dates = new Hashtable<String, Integer>();
	private Hashtable<String, String> countryList;
	
	public WindowSourcesView(final MapControler controler, final Hashtable<String, String> countryList) {
		this.countryList = countryList;
		this.controler = controler;
		
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
    	int[] datesMI = new int[52];
    	int[] datesC = new int[21];
    	
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
	    			//TODO action sur le bouton Homicides Intentionnels
	    		}
	    		else {
	    			comboBoxHI.disable();
	    			comboBoxHI.setSelectedIndex(0);
	    			comboBoxPaysHI.disable();
	    			//comboBoxPaysHI.setSelectedIndex(0);
	    			//TODO on relache la table qui affecte les Homicides Intentionnels
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
					ArrayList<String> pays = new ArrayList<String>();
					pays.add("FRA");
					// TODO Ajout des pays a countryList
					
					dates.put("HomicidesIntentionnels", Integer.parseInt(item.toString()));
					dates.put("Chomage", -1);
					dates.put("MortaliteInfantile", -1);
					controler.userQuery(dates, pays);
					
					System.out.println(item);
					//TODO Ajouter la valeur de item a la hashtable contenant la date des Homicides Intentionnels
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
	    			//TODO action sur le bouton Mortalité Infantile
	    		}
	    		else {
	    			comboBoxMI.disable();
	    			comboBoxMI.setSelectedIndex(0);
	    			comboBoxPaysMI.disable();
	    			//comboBoxPaysMI.setSelectedIndex(0);
	    			//TODO on relache la table qui affecte les Mortalité Infantile
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
					System.out.println(item);
					//TODO Ajouter la valeur de item a la hashtable contenant la date des Mortalité Infantile
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
	    			//TODO action sur le bouton Mortalité Infantile
	    		}
	    		else {
	    			comboBoxC.disable();
	    			comboBoxC.setSelectedIndex(0);
	    			comboBoxPaysC.disable();
	    			//comboBoxPaysC.setSelectedIndex(0);
	    			//TODO on relache la table qui affecte les Mortalité Infantile
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
					System.out.println(item);
					//TODO Ajouter la valeur de item a la hashtable contenant la date des Chômage
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
		frame.getContentPane().add(comboBoxPaysHI);
		comboBoxPaysHI.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(item);
					//TODO Ajouter la valeur de item a la hashtable contenant le pays des Homicides Intentionnels
				}
				
			}
		});
		
		comboBoxPaysMI.setBounds(544, 150, 185, 24);
		comboBoxPaysMI.addItem("");
		frame.getContentPane().add(comboBoxPaysMI);
		comboBoxPaysMI.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(item);
					//TODO Ajouter la valeur de item a la hashtable contenant le pays des Mortalité Infantile
				}
				
			}
		});
		
		comboBoxPaysC.setBounds(544, 236, 185, 24);
		comboBoxPaysC.addItem("");
		frame.getContentPane().add(comboBoxPaysC);
		comboBoxPaysC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
			Object item = event.getItem();
				if (event.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(item);
					//TODO Ajouter la valeur de item a la hashtable contenant le pays des Chômage
				}
				
			}
		});	
		
		frame.setVisible(true);
	}
}
