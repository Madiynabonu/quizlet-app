package com.nooglers.dto;

import jakarta.servlet.http.HttpServletRequest;

public record SendMessageDto( String message1 , String message2 , String message3 , String url ) {
}
