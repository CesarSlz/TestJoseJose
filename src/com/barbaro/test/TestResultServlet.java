package com.barbaro.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestResultServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		final String[] canciones = {
				"Gavilan o paloma",
				"El triste",
				"Almohada",
				"Payaso",
				"Vamos a darnos tiempo"
		};
		
		final String[] imagenes = {
			"https://i.pinimg.com/originals/11/31/d9/1131d9eff43d62fb0e3def9b2a0f2b4c.gif",
			"https://k60.kn3.net/taringa/2/8/1/7/1/0/vagonettas/9DB.gif",
			"https://thumbs.gfycat.com/DecentSimilarAoudad-size_restricted.gif",
			"https://media2.giphy.com/media/bYjigaFaAzZEQ/source.gif",
			"https://media3.giphy.com/media/9u514UZd57mRhnBCEk/giphy.gif"
		};
		
		Map<String, Integer[]> puntosCancion = new HashMap<>();
		
		// 0 = Felicidad
		// 1 = Tristeza
		// 2 = Miedo
		// 3 = Enamoramiento
		// 4 = Despecho
		
		// Asiganarle a una pregunta un sentimiento
		// Por fila un valor mayor en una columna
		puntosCancion.put("pregunta1", new Integer[] {10, 0, 80, 0, 10});
		puntosCancion.put("pregunta2", new Integer[] {40, 20, 0, 30, 10});
		puntosCancion.put("pregunta3", new Integer[] {0, 50, 0, 40, 0});
		puntosCancion.put("pregunta4", new Integer[] {10, 20, 30, 40, 0});
		puntosCancion.put("pregunta5", new Integer[] {0, 10, 0, 40, 50});
		
		int[] resultados = new int[5];
		
		String[] respuestas = new String[5];
		int total = 0;
		int numCancion = 0;
		
		response.setContentType("text/html");
		PrintWriter salida = response.getWriter();
		
		for(int i = 0; i < respuestas.length; i++) {
			respuestas[i] = request.getParameter("respuesta" + (i+1));
		}
		
		for(int i = 0; i < respuestas.length; i++) {
			
			Integer[] puntos = puntosCancion.get("pregunta" + (i+1));
			
			for(int j = 0; j < puntos.length; j++) {
				
				int respuesta = Integer.parseInt(respuestas[i]);
				resultados[j] += (puntos[j] * respuesta);
			}
			
			//respuestas[i] = request.getParameter("respuesta" + (i+1));
			//total += Integer.parseInt(respuestas[i]);
		}
		
		int mayor = 0;
		for(int i = 0; i< resultados.length; i++) {
			if(resultados[i] > mayor) {
				mayor = resultados[i];
				numCancion = i;
			}
		}
		
		//numCancion = total % canciones.length;
		
		salida.println("<!DOCTYPE html>");
		salida.println("<html>");
		salida.println("<head>");
		salida.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"..css/styles.css\">");
		salida.println("</head>");
		salida.println("<body>");
		salida.println("<h2>La canción es: " + canciones[numCancion] + "</h2>");
		salida.println("<img src=\"" + imagenes[numCancion] + "\">");
		salida.println("</body>");
		salida.println("</html>");
	}
}
