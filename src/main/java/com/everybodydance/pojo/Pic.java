package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pic {
    private String id;
    private String savepath;
    private String realname;
    private String bookid;
    private Timestamp uploadtime;
    private Integer cover;

}
