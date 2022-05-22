package database;

import java.util.List;

public interface PaivanRuokaItemDao {

	public List<PaivanRuokaItem> getAllItems();

	public PaivanRuokaItem valitseRuoka(long id);

	public boolean addItem(PaivanRuokaItem newItem);

	public boolean removeItem(PaivanRuokaItem item);

	public List<PaivanRuokaItem> haeEhdoilla(String vaikeusaste, double valmistusaika);
}