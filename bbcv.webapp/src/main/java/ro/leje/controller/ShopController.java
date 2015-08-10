package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

/**
 * @author Danut Chindris
 * @since August 10, 2015
 */
@Controller
public class ShopController {

    @RequestMapping(value = MappingConstants.SHOP)
    public String showShop() {
        return ViewConstants.SHOP;
    }
}
