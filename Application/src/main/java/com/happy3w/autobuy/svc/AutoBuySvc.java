package com.happy3w.autobuy.svc;

import com.happy3w.autobuy.util.config.DataConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ysgao on 5/15/16.
 */
@Component
public class AutoBuySvc {
    private final Logger logger = LoggerFactory.getLogger(AutoBuySvc.class);

    @Autowired
    private DataConfig dataConfig;

    private AutoBuyProcess autoBuyProcess;
    private Thread autobuyThread;

    public void buy() {
        if (autobuyThread == null || !autobuyThread.isAlive()) {
            autoBuyProcess = new AutoBuyProcess(dataConfig.getYyfaxAccount(), dataConfig.getYyfaxPassword());
            autobuyThread = new Thread(autoBuyProcess, "AutoBuyThread") {
                @Override
                public void run() {
                    try {
                        autoBuyProcess.run();
                    } catch (Throwable t) {
                        logger.error(t.getMessage(), t);
                    } finally {
                        autobuyThread = null;
                    }
                }
            };
            autobuyThread.start();
        }
    }

}
