package me.sjihh.spaservice.Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.sjihh.spaservice.Database.SaleOffLoader;

@WebServlet("/PromoCode")
public class PromoCodeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String promoCode = request.getParameter("promo");
        int discount = getDiscountByPromoCode(promoCode);

        // You can do further processing or send the discount information back to the client
        response.getWriter().write(String.valueOf(discount));
    }

    private int getDiscountByPromoCode(String promoCode) {
        List<SaleOffLoader> saleOffLoaders = SaleOffLoader.loadSaleOffs();

        for (SaleOffLoader saleOffLoader : saleOffLoaders) {
            if (promoCode.equals(saleOffLoader.getSaleOff_code())) {
                return saleOffLoader.getSaleOff_percent();
            }
        }

        // If the promo code is not found, return 0 or any default value
        return 0;
    }
}
