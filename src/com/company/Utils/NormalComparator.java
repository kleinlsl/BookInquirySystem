package com.company.Utils;

import java.util.Comparator;

/*
 *Collator 类执行区分语言环境的 String 比较。使用此类可为自然语言文本构建搜索和排序例程。
 *Collator 是一个抽象基类。其子类实现具体的整理策略。
 *Java 平台目前提供了 RuleBasedCollator 子类，它适用于很多种语言。
 *还可以创建其他子类，以处理更多的专门需要。
 *与其他区分语言环境的类一样，可以使用静态工厂方法 getInstance 来为给定的语言环境获得适当的 Collator 对象。
 *如果需要理解特定整理策略的细节或者需要修改策略，只需查看 Collator 的子类即可。
 *下面的示例显示了如何使用针对默认语言环境的 Collator 比较两个字符串。
 *
 *
 */
import java.text.RuleBasedCollator;
import java.text.Collator;
import java.util.Locale;
public class NormalComparator implements Comparator<Object> {
    /*
     *RuleBasedCollator 为 Collator 的子类
     */

    RuleBasedCollator collator = (RuleBasedCollator)Collator.getInstance(Locale.CHINA);//public static final Locale CHINA 指定中国的语言环境

    /*
     *比较用来排序的两个参数。根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。
     */
    public int compare(Object o1, Object o2) {
        return collator.compare(o1.toString(), o2.toString());

    }
}


