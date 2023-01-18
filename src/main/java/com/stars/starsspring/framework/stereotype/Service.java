package com.stars.starsspring.framework.stereotype;

/**
 * 服务——注解
 *
 * @author stars
 */
@Component
public @interface Service {

    String value() default "";
}
