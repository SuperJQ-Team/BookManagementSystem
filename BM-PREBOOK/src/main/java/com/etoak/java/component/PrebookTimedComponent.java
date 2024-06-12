package com.etoak.java.component;

import com.etoak.java.entity.Prebook;
import com.etoak.java.feign.IBookServiceFeign;
import com.etoak.java.service.impl.PrebookServiceImpl;
import com.etoak.java.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PrebookTimedComponent {
    @Autowired
    PrebookServiceImpl prebookService;
    @Autowired
    IBookServiceFeign bookServiceFeign;
    @Autowired
    EmailComponent emailComponent;

    @Scheduled(cron="0 0 * * * *")
    public void timedSendEmail() {
        List<Prebook> prebooks = prebookService.list();

        ArrayList<String> prebookNames = new ArrayList<>();
        for(Prebook prebook: prebooks){
            prebookNames.add(prebook.getBookName());
        }

        System.out.println(prebookNames);
        ResultVO<Map<String, Integer>> bookResultObj = bookServiceFeign.getBooksCount(prebookNames);
        Map<String, Integer> bookCounts = bookResultObj.getData();

        System.out.println(bookCounts);

        Map<String, Set<String>> emailBooks = new HashMap<>();
        Map<String, String> emailName = new HashMap<>();

        for (Prebook prebook : prebooks) {
            /*由统计某个邮箱地址的书*/
            String email = prebook.getEmail();
            String bookName = prebook.getBookName();
            String personName = prebook.getPersonName();

            if(bookCounts.get(bookName) > 0){
                if (!emailBooks.containsKey(email)) {
                    emailBooks.put(email, new HashSet<>());
                    emailName.put(email, personName);
                }
                emailBooks.get(email).add(bookName);
            }
        }

        /*TODO 发送邮件 */
        for(String email: emailBooks.keySet()){
            Set<String> books = emailBooks.get(email);
            String name = emailName.get(email);
            StringBuilder content = new StringBuilder();
            if("".equals(name)){
                content.append(String.format("尊敬的 %s 先生/女士！\n", name));
            }else{
                content.append("尊敬的匿名用户！");
            }

            content.append("您所预约的 ");

            for(String book: books){
                content.append(String.format("《%s》 ", book));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

            content.append("已经在库，请及时进行借阅。\n");
            content.append(sdf.format(new Date()));

            emailComponent
                    .setEmail(email)
                    .setHead("您预定的书已经在库")
                    .setContent(content.toString())
                    .send();
        }

    }

    public void send(){
        System.out.println(emailComponent.port);
        emailComponent
                .setEmail("1508426617@qq.com")
                .setHead("这是一个标题")
                .setContent("这是一些内容OWO")
                .send();
    }
}
