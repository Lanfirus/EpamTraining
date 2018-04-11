package ua.training.controller.command;

import ua.training.model.Sweety;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NavigateToCustomOrderPage implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        putOrderComponentsToContext(session);

        return "/custom_order.jsp";
    }

    private void putOrderComponentsToContext(HttpSession session){
        if (session.getAttribute("sweeties") == null){
            session.setAttribute("sweeties", prepareOrderComponents());
        }
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
}
