package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import controler.MapControler;
import mediator.Mediator;
import model.MapModel;

public class JMap {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/projetisiGouverneurGiffon";
		String username = "root";
		String password = "";
		Connection connection = DriverManager.getConnection(url, username, password);

		String[] tableNames = {"Chomage","MortaliteInfantile", "HomicidesIntentionnels"};
		Mediator mediator = new Mediator(connection, tableNames);
		MapModel model = new MapModel(connection, mediator);
		MapControler controler = new MapControler(model);
		controler.displayViews();
		controler.closeViews();
	}
}
