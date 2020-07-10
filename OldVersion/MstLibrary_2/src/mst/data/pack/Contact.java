/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.pack;
import java.lang.annotation.*;

/**
 *
 * @author Mustafa SACLI
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Contact {
    String value() default "mustafasacli70@gmail.com";
}
