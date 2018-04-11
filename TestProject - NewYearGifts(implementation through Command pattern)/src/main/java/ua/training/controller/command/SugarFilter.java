package ua.training.controller.command;

import ua.training.model.Sweety;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SugarFilter implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<Sweety> sweeties = filterOrderComponentsBySugarValue(request, session);
        session.setAttribute("sweeties", sweeties);
        storeAllRequestAttributesToSession(request, session);

        return "/custom_order.jsp";
    }

    private List<Sweety> prepareOrderComponents(){
        final List<Sweety> orderComponents = new ArrayList<>();
        orderComponents.add(Sweety.CARAMEL_CANDY);
        orderComponents.add(Sweety.CHOCOLATE_CANDY);
        orderComponents.add(Sweety.JELLY_CANDY);
        orderComponents.add(Sweety.LOLLIPOP_CANDY);
        orderComponents.add(Sweety.WAFFLE);
        orderComponents.add(Sweety.MARSHMALLOW);
        return orderComponents;
    }

    private List<Sweety> filterOrderComponentsBySugarValue(HttpServletRequest request, HttpSession session){
        Integer sugarFrom = Integer.parseInt(request.getParameter("sugarFrom"));
        Integer sugarTo = Integer.parseInt(request.getParameter("sugarTo"));

        session.setAttribute("sugarFrom", sugarFrom);
        session.setAttribute("sugarTo", sugarTo);

        List<Sweety> sweeties = prepareOrderComponents();
        sweeties = sweeties.stream().filter(x -> x.getSugarValue() >= sugarFrom && x.getSugarValue() <= sugarTo)
                .collect(Collectors.toList());
        return sweeties;
    }

    private void storeAllRequestAttributesToSession(HttpServletRequest request, HttpSession session){
        session.setAttribute("boxType", request.getParameter("boxType"));
        Integer caramelQty = Integer.parseInt(request.getParameter("caramel_qty"));
        session.setAttribute("caramelQty", caramelQty);
        Integer chocolateQty = Integer.parseInt(request.getParameter("chocolate_qty"));
        session.setAttribute("chocolateQty", chocolateQty);
        Integer jellyQty = Integer.parseInt(request.getParameter("jelly_qty"));
        session.setAttribute("jellyQty", jellyQty);
        Integer lollipopQty = Integer.parseInt(request.getParameter("lollipop_qty"));
        session.setAttribute("lollipopQty", lollipopQty);
        Integer waffleQty = Integer.parseInt(request.getParameter("waffle_qty"));
        session.setAttribute("waffleQty", waffleQty);
        Integer marshmallowQty = Integer.parseInt(request.getParameter("marshmallow_qty"));
        session.setAttribute("marshmallowQty", marshmallowQty);
        Integer customOrderQty = Integer.parseInt(request.getParameter("custom_order_qty"));
        session.setAttribute("customOrderQty", customOrderQty);
    }
}
