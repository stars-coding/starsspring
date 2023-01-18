package com.stars.starsspring.framework.stereotype;

/**
 * 控制器——注解
 *
 * @author stars
 */
@Component
public @interface Controller {

    String value() default "";
}
