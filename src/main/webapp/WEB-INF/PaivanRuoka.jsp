<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
<title>Satunnainen ruoka</title>
</head>
<body style="background-image: url(https://hips.hearstapps.com/edc.h-cdn.co/assets/16/17/cabinets_after2.jpg);background-size:cover;">
<a href = "/">
<img src ="https://researchmaniacs.com/HTML-Code/Pictograms/Pictograms-Images/Back-With-Leftwards-Arrow-Above.png" width="50" height="50">
</a>
<h1 style = "background-color: #F0F8FF;"> Satunnainen ruoka ehdoilla: ${vaikeusaste}, alle ${valmistusaika}(h)
							 <br>${ satunnaisruoka }</h1>
<p style = "background-color: #F0F8FF;"> Raaka-Aineet: ${ raakaAineet }</p>
<img  style= "border-radius: 10px;" src = ${ kuvalinkki }>
</body>
</html>