package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.exception.ForbiddenException;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.CommentRepository;

import java.util.Optional;

//creating custom annotation
@Component
@Aspect
public class CheckDeletedCommentExecutor {

    @Autowired
    private CommentRepository commentRepository;
    //annotatsiya chaqirilganda ishga tushuvchi metod
    //@annotation ichidagi value va method parametri bir xil bo'lishi lozim
    @Before(value = "@annotation(checkPermission)")
    public void checkPermissionExecutor(CheckDeletedComment checkPermission) throws ForbiddenException {

        String auth = checkPermission.value();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (auth.equals("DELETE_MY_COMMENT")&&user.getRole().getName().equals("user")){
            Optional<User> byCreatedBy = commentRepository.findByCreatedBy(user);
            if (!(byCreatedBy.isPresent()&&byCreatedBy.get().getId()== user.getId())){
                throw new ForbiddenException(auth, "not permitted");
            }
        }
    }
}
