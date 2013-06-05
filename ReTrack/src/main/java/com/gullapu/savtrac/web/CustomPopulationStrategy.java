package com.gullapu.savtrac.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.sourceforge.stripes.tag.DefaultPopulationStrategy;
import net.sourceforge.stripes.tag.PopulationStrategy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPopulationStrategy {
	Class<? extends PopulationStrategy> value() default DefaultPopulationStrategy.class;
}
