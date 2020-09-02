package edu.eci.arsw.springdemo;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GrammarCheckerTest {
    @Test
    public void shouldCheckEnglishGrammar() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        GrammarChecker gc = ac.getBean(GrammarChecker.class);
        if (gc.getLang().equals("englishSpell")) {
            System.out.println(gc.check("la la la "));
            Assert.assertEquals("Spell checking output:Checked with english checker:la la la Plagiarism checking output: Not available yet", gc.check("la la la "));
        }

    }

    @Test
    public void shouldCheckSpanishGrammar() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        GrammarChecker gc = ac.getBean(GrammarChecker.class);
        if (gc.getLang().equals("spanishSpell")) {
            System.out.println(gc.check("la la la "));
            Assert.assertEquals("Spell checking output:revisando (la la la ) con el verificador de sintaxis del espanolPlagiarism checking output: Not available yet", gc.check("la la la "));
        }
    }
}
