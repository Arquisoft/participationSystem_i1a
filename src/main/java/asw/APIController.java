package asw;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping("/REST/user")
    public UserInfo user() {
        return new UserInfo("pepe",0);
    }

}