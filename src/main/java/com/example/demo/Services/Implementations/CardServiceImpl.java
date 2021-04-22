package com.example.demo.Services.Implementations;

import com.example.demo.Payment.Card;
import com.example.demo.Payment.CardChecker;
import com.example.demo.Services.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    private final CardChecker cardChecker;

    public CardServiceImpl(CardChecker cardChecker) {
        this.cardChecker = cardChecker;
    }

    @Override
    public int preCheckCard(Card card) {
        int res = CardChecker.checkCard(card);
        return res;
    }
}
