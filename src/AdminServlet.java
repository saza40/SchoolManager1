import se.devschool.SchoolAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminServlet  extends HttpServlet {
    private String title = "Scanning";
    SchoolAdmin schoolAdmin = new SchoolAdmin();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pos = request.getParameter("pos");

        String message = "";
        if (pos == null )
            message = loginScr();
        else {
            switch (pos) {
                case "l":
                    message =  login(request.getParameter("username"),request.getParameter("password"));
                   break;
            }
        }
//        String message = schoolAdmin.registerArrivalDeparture(barcode);
        // Set response content type

        response.setContentType("text/html");
//        String message = "Welcome "+barcode;
        PrintWriter out = response.getWriter();
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                message+
                "</body>" +
                "</html>"
        );
    }

    private String loginScr() {
        String returnStr;
        returnStr  = "<form action = \"AdminServlet\" method = \"GET\">\n";
        returnStr += "    <input type = \"hidden\" name = \"pos\" value = \"l\">\n";
        returnStr += "    Username: <input type = \"text\" name = \"username\">\n";
        returnStr += "    <br />";
        returnStr += "    Password: <input type = \"password\" name = \"password\">\n";
        returnStr += "    <br />";
        returnStr += "    <input type = \"submit\" value = \"Submit\" />\n";
        returnStr += "</form>";
        title = "Login";
        return returnStr;
    }

    private String login(String username, String password) {
        String returnStr;
        String uname = schoolAdmin.checkLogin(username,password);
        if (uname.isEmpty()) {
            returnStr = loginScr();
        } else {
            returnStr  = "<form action = \"AdminServlet\" method = \"GET\">\n";
            returnStr += "    <input type = \"hidden\" name = \"pos\" value = \"m\">\n";
            returnStr += "    <input type = \"hidden\" name = \"username\" value = \""+uname+"\">\n";
            returnStr += "    <h1>Hi "+uname+" you're in!</h1>\n";
            returnStr += "    <br />";
            returnStr += "    <input type = \"submit\" value = \"Submit\" />\n";
            returnStr += "</form>";
            title = "Loged in";
        }
        return returnStr;

    }

}
