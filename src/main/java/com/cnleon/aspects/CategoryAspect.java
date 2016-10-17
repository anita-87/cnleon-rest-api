package com.cnleon.aspects;

import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import com.cnleon.enumerates.Gender;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by anita on 15/10/16.
 */

@Aspect
@Component
public class CategoryAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${swimmers.abs.masc.low}")
    private Integer absMascLow;

    @Value("${swimmers.abs.fem.low}")
    private Integer absFemLow;

    @Value("${swimmers.absjov.masc.low}")
    private Integer absJovMascLow;

    @Value("${swimmers.absjov.masc.high}")
    private Integer absJovMascHigh;

    @Value("${swimmers.absjov.fem.low}")
    private Integer absJovFemLow;

    @Value("${swimmers.absjov.fem.high}")
    private Integer absJovFemHigh;

    @Value("${swimmers.jun.masc.low}")
    private Integer junMascLow;

    @Value("${swimmers.jun.masc.high}")
    private Integer junMascHigh;

    @Value("${swimmers.jun.fem.low}")
    private Integer junFemLow;

    @Value("${swimmers.jun.fem.high}")
    private Integer junFemHigh;

    @Value("${swimmers.inf.masc.low}")
    private Integer infMascLow;

    @Value("${swimmers.inf.masc.high}")
    private Integer infMascHigh;

    @Value("${swimmers.inf.fem.low}")
    private Integer infFemLow;

    @Value("${swimmers.inf.fem.high}")
    private Integer infFemHigh;

    @Value("${swimmers.alv.masc.low}")
    private Integer alvMascLow;

    @Value("${swimmers.alv.masc.high}")
    private Integer alvMascHigh;

    @Value("${swimmers.alv.fem.low}")
    private Integer alvFemLow;

    @Value("${swimmers.alv.fem.high}")
    private Integer alvFemHigh;

    @Value("${swimmers.benj.masc.low}")
    private Integer benjMascLow;

    @Value("${swimmers.benj.masc.high}")
    private Integer benjMascHigh;

    @Value("${swimmers.benj.fem.low}")
    private Integer benjFemLow;

    @Value("${swimmers.benj.fem.high}")
    private Integer benjFemHigh;

    @Value("${swimmers.prebbenj.masc.high}")
    private Integer prebenjMascHigh;

    @Value("${swimmers.prebbenj.fem.high}")
    private Integer prebenjFemHigh;

    @AfterReturning(pointcut = "execution(* com.cnleon.repositories.SwimmerRepository.findById(..))",
    returning = "swimmer")
    public void setSwimmerCategory(Object swimmer){
        logger.info("Swimmer id is: " + ((Swimmer) swimmer).getId());
        if (((Swimmer) swimmer).getIsMaster() == true){
            ((Swimmer) swimmer).setCategory(Category.MASTER);
        } else {
            if (((Swimmer) swimmer).getGender().equals(Gender.MALE))
                ((Swimmer) swimmer).setCategory(this.calculateMaleCategory(((Swimmer) swimmer).getBirthDate()));
            else
                ((Swimmer) swimmer).setCategory(this.calculateFemaleCategory(((Swimmer) swimmer).getBirthDate()));
        }
    }

    private Category calculateMaleCategory(Date birthDate){
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        logger.debug("Calculating male category for year " + date.getYear());

        if (date.getYear() <= absMascLow)
            return Category.ABSOLUTO;
        if (date.getYear() == absJovMascLow || date.getYear() == absJovMascHigh )
            return Category.ABSJOVEN;
        if (date.getYear() == junMascLow || date.getYear() == junMascHigh )
            return Category.JUNIOR;
        if (date.getYear() == infMascLow || date.getYear() == infMascHigh )
            return Category.INFANTIL;
        if (date.getYear() == alvMascLow || date.getYear() == alvMascHigh )
            return Category.ALEVIN;
        if (date.getYear() == benjMascLow || date.getYear() == benjMascHigh )
            return Category.BENJAMIN;
        if (date.getYear() >= prebenjMascHigh)
            return Category.PREBENJAMIN;
        return null;
    }

    private Category calculateFemaleCategory(Date birthDate){
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        logger.debug("Calculating female category for year " + date.getYear());

        if (date.getYear() <= absFemLow )
            return Category.ABSOLUTO;
        if (date.getYear() == absJovFemLow || date.getYear() == absJovFemHigh )
            return Category.ABSJOVEN;
        if (date.getYear() == junFemLow || date.getYear() == junFemHigh )
            return Category.JUNIOR;
        if (date.getYear() == infFemLow || date.getYear() == infFemHigh )
            return Category.INFANTIL;
        if (date.getYear() == alvFemLow || date.getYear() == alvFemHigh )
            return Category.ALEVIN;
        if (date.getYear() == benjFemLow || date.getYear() == benjFemHigh )
            return Category.BENJAMIN;
        if (date.getYear() >= prebenjFemHigh )
            return Category.PREBENJAMIN;
        return null;
    }

}
