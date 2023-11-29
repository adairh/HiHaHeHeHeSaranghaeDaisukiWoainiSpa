package me.sjihh.spaservice.Servlet;

import me.sjihh.spaservice.Authentication.Customer;
import me.sjihh.spaservice.Database.LevelLoader;
import me.sjihh.spaservice.Database.RoomLoader;
import me.sjihh.spaservice.Database.SaleOffLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdatePrice", value = "/UpdatePrice")
public class UpdatePriceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("room"));
        String promo = request.getParameter("promo");
        request.getSession().setAttribute("room", roomID);
        request.getSession().setAttribute("promo", promo);

        double servicePrice = (double) request.getSession().getAttribute("servicePrice");

        Customer user = ((Customer)request.getSession().getAttribute("user"));
        int lvl = LevelLoader.getLevelByID(user.getLevel_id()).getSale_percent();


        double total = (servicePrice+ RoomLoader.loadRooms().get(roomID).getRoom_price())
                * (100 - lvl - getDiscountByPromoCode(promo))/100;


        request.getSession().setAttribute("finalPrice", total);

        getServletContext()
                .getRequestDispatcher("/cart.jsp#cart")
                .forward(request, response);

    }

    private int getDiscountByPromoCode(String promoCode) {
        List<SaleOffLoader> saleOffLoaders = SaleOffLoader.loadSaleOffs();

        for (SaleOffLoader saleOffLoader : saleOffLoaders) {
            if (promoCode.equals(saleOffLoader.getSaleOff_code())) {
                return saleOffLoader.getSaleOff_percent();
            }
        }

        return 0;
    }
}
