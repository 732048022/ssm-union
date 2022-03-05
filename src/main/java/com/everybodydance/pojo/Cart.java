package com.everybodydance.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class Cart {
    private Book b;
    private int sum;
    private int count;

    public void setSum(){
        this.sum = (int) (b.getPrice()*count);
    }
}
