package com.cnleon.aspects;

import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import com.cnleon.enumerates.Gender;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Aspect to handle the categories for the swimmers.
 * Categories are not stored in the DB, the are calculated every time one of the methods
 * indicated below (in the pointcuts) are called in any other part of the code.
 *
 * Created by anita on 15/10/16.
 */

@Aspect
@Component
public class CategoryAspect {

    /**
     * Logger instance for the service.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //List of all the properties for the years of all the categories
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

    /**
     * Method to calculate the category for a single swimmer object.
     * The aspect is called automatically every time the method "findById" is called.
     * {@link com.cnleon.repositories.SwimmerRepository#findById(String)}
     * @param swimmer - the swimmer to calculate the category.
     */
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

    /**
     * Method to calculate the category for a list of swimmers.
     * Iterates through the list of swimmers and the call the method "setSwimmerCategory"
     * @see com.cnleon.aspects.CategoryAspect#setSwimmerCategory(Object)
     * The aspect is called automatically every time the method "findAll" from MongoRepository finishes.
     * {@link MongoRepository#findAll()}
     * @param swimmers - the list of swimmers obtained from the DB.
     */
    @AfterReturning(pointcut = "execution(* org.springframework.data.mongodb.repository.MongoRepository.findAll(..)) " +
            "|| execution(* org.springframework.data.mongodb.core.MongoTemplate.findAll(..))",
    returning = "swimmers")
    public void setSwimmersCategoryFromList(Object swimmers){
        //This code is to force that this method is only called for calls to MongoRepository.findAll
        //and MongoTemplate.findAll that return a Swimmer type.
        //TODO: Review if there is a better way to do this!
        if (swimmers instanceof List) {
            ArrayList<Object> genericList = (ArrayList<Object>) swimmers;
            if (genericList.size() > 0) {
                Object firstElement = genericList.get(0);
                if (firstElement instanceof  Swimmer){
                    Iterator<Swimmer> swimmerIterator = ((List<Swimmer>) swimmers).iterator();
                    while (swimmerIterator.hasNext()){
                        Swimmer swimmer = swimmerIterator.next();
                        logger.debug("Calculating category for swimmer " + swimmer.getId());
                        this.setSwimmerCategory(swimmer);
                    }
                }
            }
        }
    }

    /**
     * Private method to calculate the category for male swimmers.
     * @param birthDate - the birth date of the male swimmer.
     * @return the category enum of the swimmer or null if the swimmer does not match any category
     */
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

    /**
     * Private method to calculate the category for female swimmers.
     * @param birthDate - the birth date of the female swimmer.
     * @return the category enum of the swimmer or null if the swimmer does not match any category
     */
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
