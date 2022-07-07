package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.aop;

import java.lang.annotation.*;

//creating custom annotation
@Documented
@Target(ElementType.METHOD)   //metodlarni ustida ishlaratiladi
@Retention(RetentionPolicy.RUNTIME)    //Annotatsiya runtime paytida ishlaydi
public @interface CheckDeletedComment {
    //@interface define that the CheckPermission is the new annotation type

    //annotatsiyaga kiruvchi qiymat
    String value();
    String commentId();
}
