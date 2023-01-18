package com.stars.starsspring.framework.stereotype;

/**
 * 数据访问——注解
 *
 * @author stars
 */
@Component
public @interface Repository {

    String value() default "";
}
