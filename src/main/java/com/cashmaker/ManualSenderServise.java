package com.cashmaker;

import com.cashmaker.bot.SenderApi;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by asti on 02.03.2018.
 */
@Controller
public class ManualSenderServise {

    @RequestMapping("/send")
    public String sendMes(@RequestParam("to") long id,
                          @RequestParam("text") String text) {
        System.out.println(String.format("Получил задачу отправить [%s] в чат {%s]", text, id));
        SendMessage request = new SendMessage(id, text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true)
//                .replyToMessageId(1)
//                .replyMarkup(new ForceReply())
                ;

// sync
        SendResponse sendResponse = SenderApi.getSender().execute(request);
        boolean ok = sendResponse.isOk();
        Message message = sendResponse.message();
        return "ok!";
    }
}
