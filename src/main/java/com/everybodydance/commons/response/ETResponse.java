package com.everybodydance.commons.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data@NoArgsConstructor@AllArgsConstructor
public class ETResponse {
    private int code;
    private String msg;
    private Object data;
}
