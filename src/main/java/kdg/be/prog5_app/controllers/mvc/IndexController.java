package kdg.be.prog5_app.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("unauthenticated")
    public String getUnauthenticatedPage(){
        return "unauthenticated";
    }


}
