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
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 6)
    private String password;
}
