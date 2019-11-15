package ConCord.MainController;

import ConCord.Models.JsonRequest;
import ConCord.Models.JsonResponse;
import ConCord.Service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class MainController {

    private MyService myService;

    @Autowired
    public MainController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping(
            value = "/encryption",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse test(@RequestBody JsonRequest jsonRequest){
        return myService.encryption(jsonRequest);

    }


}
