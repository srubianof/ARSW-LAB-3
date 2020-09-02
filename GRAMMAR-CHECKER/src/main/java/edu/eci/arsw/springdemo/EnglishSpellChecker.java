package edu.eci.arsw.springdemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("englishSpell")
public class EnglishSpellChecker implements SpellChecker {

    @Override
    public String checkSpell(String text) {

    	return "Checked with english checker:" + text;
    }


}
