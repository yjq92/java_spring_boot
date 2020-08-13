package com.hqyj.javaSpringBoot.modules.test.service.Impl;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.entity.Card;
import com.hqyj.javaSpringBoot.modules.test.repository.CardRepository;
import com.hqyj.javaSpringBoot.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
    @Override
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(Result.ResultStaus.SUCCESS.status,"insert success.",card);
    }
}
