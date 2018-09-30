package cn.tengshe789.vo;

import cn.tengshe789.validator.IsMobile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/*接受参数*/
@Getter
@Setter
@ToString
public class LoginVo {
    //手机号码，1开头，后面有11位
    @NotNull
    @IsMobile
    private String mobile;

    //密码最小6位
    @NotNull
    @Length(min = 6)
    private String password;
}
