<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paivan Ruoka</title>
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>
<body>
<h1>Päivän ruoka</h1>
    <div>
        <form name = "ruokaEtsiminen"method = "get" action="paivanruoka" >
        <fieldset>
        <legend>Ongelmiä päättää päivän ruoka?<br> Ohjelma valitsee sinulle sopivan ruoan</legend>
            <label for= "vaikeustaso">Valitse valmistusvaikeus:</label>
              <select id="vaikeusaste" name="vaikeusaste" required>
   		 		<option value="helppo">Helppo</option>
    			<option value="keskivaikea">Keskivaikea</option>
   		 		<option value="vaikea">Vaikea</option>
   		 		</select>
            <label>Valmistusaika max(h): <input type="number" name="valmistusaika" min="0.1" max="99" step="0.1" required></label>
            <input type="submit" value="Lähetä">
            </fieldset>
        </form>
        <form class = "lisaaRuoka" method="POST">
        <fieldset>
        <legend>Lisää ruoka listaan</legend>
        <label>Nimi: <input type = "text" name = "lisays_nimi" required></label>
        <label for= "helppous">Valitse valmistusvaikeus:</label>
              <select id="vaikeusaste" name="lisays_vaikeusaste" required>
   		 		<option value="helppo">Helppo</option>
    			<option value="keskivaikea">Keskivaikea</option>
   		 		<option value="vaikea">Vaikea</option>
   		 		</select>
        <label>Raaka-aineet: <input type = "text" name = "lisays_raakaaineet" required></label>
        <label>valmistusaika(h): <input type = "number" name = "lisays_valmistuaika" min = "0.1" max = "99" step = "0.1" required></label>
        <label>kuvalinkki: <input type = "text" name = "lisays_kuvalinkki" required></label>
        <input type = "submit" value = "Lisää ruoka">
        </fieldset>
        </form>
    </div>
    <p id = onnistui></p>

</body>
</html>