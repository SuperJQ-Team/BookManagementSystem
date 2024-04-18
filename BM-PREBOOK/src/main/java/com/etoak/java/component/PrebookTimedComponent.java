package com.etoak.java.component;

import com.etoak.java.entity.Prebook;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.service.impl.PrebookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrebookTimedComponent {
    @Autowired
    PrebookServiceImpl prebookService;
    @Autowired
    IBookServiceFeign bookServiceFeign;

    @Scheduled(cron="0 0 * * * * *")
    public void timedSendEmail() {
        List<Prebook> prebooks = prebookService.list();


    }
}
