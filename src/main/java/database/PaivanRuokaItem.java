package database;

public class PaivanRuokaItem {

	private long ruoka_id;
	private String nimi;
	private String vaikeusaste;
	private String raakaAineet;
	private double valmistusaika;
	private String kuvalinkki;

	public PaivanRuokaItem(long ruoka_id, String nimi, String vaikeusaste, String raakaAineet, double valmistusaika,
			String kuvalinkki) {
		this.ruoka_id = ruoka_id;
		this.nimi = nimi;
		this.vaikeusaste = vaikeusaste;
		this.raakaAineet = raakaAineet;
		this.valmistusaika = valmistusaika;
		this.kuvalinkki = kuvalinkki;
	}

	public PaivanRuokaItem() {

	}

	public long getRuoka_id() {
		return ruoka_id;
	}

	public void setRuoka_id(long ruoka_id) {
		this.ruoka_id = ruoka_id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getVaikeusaste() {
		return vaikeusaste;
	}

	public void setVaikeusaste(String vaikeustaso) {
		this.vaikeusaste = vaikeustaso;
	}

	public String getRaakaAineet() {
		return raakaAineet;
	}

	public void setRaakaAineet(String raakaAineet) {
		this.raakaAineet = raakaAineet;
	}

	public double getValmistusaika() {
		return valmistusaika;
	}

	public void setValmistusaika(double valmistusaika) {
		this.valmistusaika = valmistusaika;
	}

	/**
	 * Empty constructor only used by Gson when converting JSON Strings to Java
	 * objects. Set to private to prevent creating uninitialized objects.
	 */
	@SuppressWarnings("unused")

	@Override
	public boolean equals(Object other) {
		return other instanceof PaivanRuokaItem && ((PaivanRuokaItem) other).ruoka_id == this.ruoka_id;
	}

	public String getKuvalinkki() {
		return kuvalinkki;
	}

	public void setKuvalinkki(String kuvalinkki) {
		this.kuvalinkki = kuvalinkki;
	}
}
