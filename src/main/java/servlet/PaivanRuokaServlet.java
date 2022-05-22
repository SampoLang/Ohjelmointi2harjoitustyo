package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCPaivanruokaDao;
import database.PaivanRuokaItem;

@WebServlet("/paivanruoka")
public class PaivanRuokaServlet extends HttpServlet {
	// hakee tietokannasta ruoan annetuilla ehdoilla
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JDBCPaivanruokaDao dao = new JDBCPaivanruokaDao();
		List<PaivanRuokaItem> ruoat = dao.getAllItems();
		double valmistusaika = 0;

		String vaikeusaste = req.getParameter("vaikeusaste");
		try {
			valmistusaika = Double.parseDouble(req.getParameter("valmistusaika"));
		} catch (Exception e) {
			System.out.println("Valmistusaika null");
		}
		// Hakee annetuilla ehdoilla tietokannasta listan ruoista
		List<PaivanRuokaItem> satunnaisruoat = dao.haeEhdoilla(vaikeusaste, valmistusaika);
		Random rand = new Random();
		int random = 0;
		// Satunnaisluku riippuen satunnaisruoka-lista koosta
		System.out.println(satunnaisruoat.size());
		try {
			random = rand.nextInt(satunnaisruoat.size());
		} catch (Exception e) {
			System.out.println("Random tyhjä");
		}
		// Satunnainen ruoka listasta
		PaivanRuokaItem satunnaisruoka = satunnaisruoat.get(random);
		if (satunnaisruoat.size() == 0) {
			req.setAttribute("satunnaisruoka",
					"Valitsemillasi ehdoilla ei löytynyt ruokaa, Kokeile toisilla ehdoilla!");
		} else {
			req.setAttribute("satunnaisruoka", satunnaisruoka.getNimi());
			req.setAttribute("vaikeusaste", satunnaisruoka.getVaikeusaste());
			req.setAttribute("valmistusaika", satunnaisruoka.getValmistusaika());
			req.setAttribute("raakaAineet", satunnaisruoka.getRaakaAineet());
			req.setAttribute("kuvalinkki", satunnaisruoka.getKuvalinkki());

		}
		req.getRequestDispatcher("/WEB-INF/PaivanRuoka.jsp").forward(req, resp);
	}

}
