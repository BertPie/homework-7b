package pie.bert.servlets;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/fibo")
public class FiboShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        String n = req.getParameter("n");
        if(StringUtils.isEmpty(n)){
            writer.print("Nie podano fibo!");
            return;
        }
        if(!StringUtils.isNumeric(n)){
            writer.print("Podane fibo nie jest numerem!");
            return;
        }

        Long number = Long.parseLong(n);
        if(number<0){
            writer.print("Nie ma fibowania pod zerem!");
            return;
        }
        List<Long> fiboList = getFiboList(number);

        writer.print("<html><body>");
        writer.print("Twoje fibo to: " + fiboList.get(fiboList.size() - 1));

        writer.print("<table><tr>");
        for (Long l: fiboList) {
            writer.print("<td>" + l + "</td>");
        }
        writer.print("</tr></table></body></html>");
    }

    private List<Long> getFiboList(Long n){

        List<Long> fiboList = new ArrayList<>();
        Long fibo0 = 0L;
        fiboList.add(fibo0);

        if(n.equals(0L)){
            return fiboList;
        }

        Long fibo1 = 1L;
        fiboList.add(fibo1);

        for(Long i = 1L; i < n; i++){
            fibo1 += fibo0;
            fibo0 = fibo1 - fibo0;
            fiboList.add(fibo1);
        }

        return fiboList;
    }
}