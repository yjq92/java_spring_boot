package com.hqyj.javaSpringBoot.modules.test.service;

import com.hqyj.javaSpringBoot.modules.common.vo.Result;
import com.hqyj.javaSpringBoot.modules.test.entity.Card;

public interface CardService {
    Result<Card> insertCard(Card card);
}
