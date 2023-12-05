package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Database.LevelLoader;
import me.sjihh.spaservice.Database.RoomLoader;
import me.sjihh.spaservice.Database.SaleOffLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "UpdatePrice", value = "/UpdatePrice")
public class UpdatePriceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double total = 0;
        if (request.getParameterMap().size() == 0) {
            double servicePrice = (double) request.getSession().getAttribute("servicePrice");

            Customer user = ((Customer) request.getSession().getAttribute("user"));
            int lvl = LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent();

            //System.out.println(LocalDateTime.now());

            request.getSession().setAttribute("room", -1);
            request.getSession().setAttribute("promo", "");
            request.getSession().setAttribute("time", LocalDateTime.now().toString());


            total = (servicePrice)
                    * (100 - lvl) / 100;
        } else {
            int roomID = -1;
            if (request.getParameter("room") != null) {
                roomID = Integer.parseInt(request.getParameter("room"));
            }
            String promo = request.getParameter("promo");

            if (promo == null) promo = ""; else
            if (promo.equals("null")) promo = "";

            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(request.getParameter("time"));
            } catch (Exception e) {
                dateTime = null;
            }
            if (request.getParameter("time") == null) {
                dateTime = LocalDateTime.now();
                System.out.println("AAAA");
            }
            System.out.println("BBBB");


            System.out.println(promo);
            System.out.println(roomID);
            System.out.println(dateTime);

            request.getSession().setAttribute("room", roomID);
            request.getSession().setAttribute("promo", promo);
            request.getSession().setAttribute("time", dateTime.toString());

            double servicePrice = (double) request.getSession().getAttribute("servicePrice");

            Customer user = ((Customer) request.getSession().getAttribute("user"));
            int lvl = LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent();
            if (roomID > 0)
                total += RoomLoader.loadRooms().get(roomID).getRoom_price();
            total += servicePrice;

            int sale = lvl + getDiscountByPromoCode(promo);

            System.out.println(100 - sale);
            System.out.println(total);
            total = total * (100 - sale) / 100;

        }

        int tt = (int)total;

        request.getSession().setAttribute("finalPrice", tt);

        getServletContext()
                .getRequestDispatcher("/cart.jsp")
                .forward(request, response);

    }

    private int getDiscountByPromoCode(String promoCode) {
        if (promoCode == null) {
            return 0;
        }
        List<SaleOffLoader> saleOffLoaders = SaleOffLoader.loadSaleOffs();

        for (SaleOffLoader saleOffLoader : saleOffLoaders) {
            if (promoCode.equals(saleOffLoader.getSaleOff_code())) {
                return saleOffLoader.getSaleOff_percent();
            }
        }

        return 0;
    }
}
