package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCPaivanruokaDao implements PaivanRuokaItemDao {
	private final String URL = "jdbc:sqlite:C:\\tietokanta\\ruokalista.db";

	@Override
	public List<PaivanRuokaItem> getAllItems() {
		List<PaivanRuokaItem> items = new ArrayList<PaivanRuokaItem>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(URL);
			String sql = "SELECT * FROM Ruoka";
			PreparedStatement listAll = connection.prepareStatement(sql);
			ResultSet results = listAll.executeQuery();
			while (results.next()) {
				long ruokaId = results.getInt("ruoka_id");
				String ruokaNimi = results.getString("nimi");
				String vaikeusaste = results.getString("vaikeusaste");
				String raakaAineet = results.getString("raaka_aineet");
				double valmistusaika = results.getDouble("valmistusaika_h");
				String kuvalinkki = results.getString("kuvalinkki");
				PaivanRuokaItem ruoka = new PaivanRuokaItem(ruokaId, ruokaNimi, vaikeusaste, raakaAineet, valmistusaika,
						kuvalinkki);
				items.add(ruoka);
			}
			results.close();
			listAll.close();
			connection.close();

		} catch (Exception e) {

			System.out.println("error!");
		}

		return items;
	}

	@Override
	public PaivanRuokaItem valitseRuoka(long id) {
		try {
			Connection connection = DriverManager.getConnection(URL);
			PreparedStatement selectItem = connection.prepareStatement("SELECT * FROM PaivanRuokaItem WHERE id = ?");
			selectItem.setLong(1, id);
			long ruoka_id = id;
			ResultSet results = selectItem.executeQuery();
			String ruokaNimi = results.getString("nimi");
			String vaikeusaste = results.getString("vaikeusaste");
			String raakaAineet = results.getString("raaka_aineet");
			double valmistusaika = results.getDouble("valmistusaika_h");
			String kuvalinkki = results.getString("kuvalinkki");
			PaivanRuokaItem ruoka = new PaivanRuokaItem(ruoka_id, ruokaNimi, vaikeusaste, raakaAineet, valmistusaika,
					kuvalinkki);
			connection.close();
			selectItem.close();
			return ruoka;
		} catch (Exception e) {
			System.out.println("Could not find item with id: " + id);
			return null;
		}

	}

	@Override
	public boolean addItem(PaivanRuokaItem uusiRuoka) {
		try {
			Connection connection = DriverManager.getConnection(URL);
			String sql = "INSERT INTO Ruoka (nimi, vaikeusaste,raaka_aineet,valmistusaika_h,kuvalinkki)VALUES (?,?,?,?,?);";
			PreparedStatement insertQuery = connection.prepareStatement(sql);
			insertQuery.setString(1, uusiRuoka.getNimi());
			insertQuery.setString(2, uusiRuoka.getVaikeusaste());
			insertQuery.setString(3, uusiRuoka.getRaakaAineet());
			insertQuery.setDouble(4, uusiRuoka.getValmistusaika());
			insertQuery.setString(5, uusiRuoka.getKuvalinkki());
			insertQuery.executeUpdate();
			insertQuery.close();
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println("Virhe");
			return false;
		}
	}

	@Override
	public boolean removeItem(PaivanRuokaItem item) {
		try {
			Connection connection = DriverManager.getConnection(URL);
			PreparedStatement checkIfExists = connection
					.prepareStatement("SELECT * FROM PaivanRuokaItem WHERE title = ?");
			checkIfExists.setString(1, item.getNimi());
			ResultSet success = checkIfExists.executeQuery();
			if (success.next()) {
				PreparedStatement deleteQuery = connection
						.prepareStatement("DELETE FROM PaivanRuokaItem WHERE title = ?");
				deleteQuery.setString(1, item.getNimi());
				deleteQuery.executeUpdate();
				connection.close();
				deleteQuery.close();
				checkIfExists.close();
				return true;
			} else {
				connection.close();
				checkIfExists.close();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<PaivanRuokaItem> haeEhdoilla(String vaikeusaste, double valmistusaika) {
		List<PaivanRuokaItem> ruoat = new ArrayList<PaivanRuokaItem>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(URL);
			System.out.println("Yhteys kunnossa");
			PreparedStatement listSelected = connection
					.prepareStatement("SELECT * FROM Ruoka WHERE vaikeusaste = ? AND valmistusaika_h <= ?");
			listSelected.setString(1, vaikeusaste);
			listSelected.setDouble(2, valmistusaika);
			System.out.println("Hakulause toimii: " + listSelected);
			ResultSet results = listSelected.executeQuery();
			while (results.next()) {
				long ruokaId = results.getInt("ruoka_id");
				String ruokaNimi = results.getString("nimi");
				String raakaAineet = results.getString("raaka_aineet");
				String kuvalinkki = results.getString("kuvalinkki");

				PaivanRuokaItem ruoka = new PaivanRuokaItem(ruokaId, ruokaNimi, vaikeusaste, raakaAineet, valmistusaika,
						kuvalinkki);
				ruoat.add(ruoka);
			}
			results.close();
			listSelected.close();
			connection.close();

		} catch (Exception e) {

			System.out.println("error!");
		}
		return ruoat;
	}

}
