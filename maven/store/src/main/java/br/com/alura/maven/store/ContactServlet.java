package br.com.alura.maven.store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var writer = resp.getWriter();
        writer.println("<html><h2>Contact us at 6969-GO-FUCK-YOURSELF!</h2></html>");
//        writer.println(
//                """
//                <html>
//                    <h2>
//                        Contact us at 6969-GO-FUCK-YOURSELF!
//                    </h2>
//                </html>
//                """
//        );
        writer.close();
    }
}
