package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCPaivanruokaDao;
import database.PaivanRuokaItem;

@WebServlet("")
public class IndexServletti extends HttpServlet {
	// PLACEHOLDER GET METODI, ETTÄ POST TOIMII KUNNOLLA
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String aikaString = LocalTime.now().toString();

		// lähetetään aika merkkijono JSP-sivulle attribuuttina
		req.setAttribute("aikaNyt", aikaString);

		// lähetä request edelleen index.jsp sivulle
		req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}

	// POSTMETODI JOKA LISÄÄ RUOAN TIETOKANTAAN JA ILMOITTAA ONNISTUNEESTA
	// LISÄYKSESTÄ
	// OHJAA TAKAISIN LOMAKESIVULLE(ETUSIVU)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		JDBCPaivanruokaDao dao = new JDBCPaivanruokaDao();
		String ruokaNimi = req.getParameter("lisays_nimi");
		String vaikeusaste = req.getParameter("lisays_vaikeusaste");
		Double valmistusaika = Double.parseDouble(req.getParameter("lisays_valmistuaika"));
		String raakaAineet = req.getParameter("lisays_raakaaineet");
		String kuvaLinkki = req.getParameter("lisays_kuvalinkki");
		PaivanRuokaItem ruoka = new PaivanRuokaItem(0, ruokaNimi, vaikeusaste, raakaAineet, valmistusaika, kuvaLinkki);
		Boolean lisays = dao.addItem(ruoka);
		if (lisays) {
			System.out.println("lisäys onnistuis");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Lisäys onnistui!');");
			out.println("location='/';");
			out.println("</script>");
		}
		out.close();
		resp.sendRedirect("/");

	}
}
