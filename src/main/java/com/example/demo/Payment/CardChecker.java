package com.example.demo.Payment;

import org.springframework.stereotype.Component;

@Component
public class CardChecker {
    public static int checkCard(Card card){
        String num = card.getNumber();
        boolean isSixteen = num.length() == 16;
        if(!isSixteen) return 1;
        for (int i = 0; i < num.length(); i++) {
            if(num.charAt(i) <=57 && num.charAt(i)>=48){
                continue;
            }
            return 1;
        }
        return 0;
    }
}
