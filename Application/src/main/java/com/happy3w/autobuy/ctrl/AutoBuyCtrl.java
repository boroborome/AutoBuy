package com.happy3w.autobuy.ctrl;

import com.happy3w.autobuy.svc.AutoBuySvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ysgao on 5/14/16.
 */
@RestController
@RequestMapping("/svc/autobuy")
public class AutoBuyCtrl {

    @Autowired
    private AutoBuySvc autoBuySvc;

    @ResponseBody
    @RequestMapping(value = "start", method = RequestMethod.POST)
    public String start() {
    	autoBuySvc.start();
        return "Hello";
    }

}
