package com.happy3w.autobuy.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ysgao on 5/14/16.
 */
@RestController
@RequestMapping("/svc/autobuy")
public class AutoBuyCtrl {
    private final Logger logger = LoggerFactory.getLogger(AutoBuyCtrl.class);

    @RequestMapping(method = RequestMethod.GET)
    public String test() {
        return "Hello";
    }

}
