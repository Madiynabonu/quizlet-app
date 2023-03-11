package com.nooglers.utils;

import com.nooglers.dto.SendMessageDto;
import jakarta.servlet.http.HttpServletRequest;

public class MessageUtil {
    public static void setMessage(HttpServletRequest req , SendMessageDto dto) {

        req.setAttribute("message1" , dto.message1());
        req.setAttribute("message2" , dto.message2());
        req.setAttribute("message3" , dto.message3());
        req.setAttribute("url" , dto.url());
    }
}
