package com.everybodydance.commons.utils;
import com.everybodydance.commons.ETEnums;
import com.everybodydance.commons.response.ETResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data@AllArgsConstructor@NoArgsConstructor
public class SetResponse {
    @Autowired
    private ETResponse etResponse;
    public ETResponse setResponse(Object obj,String code){
        if("200".equals(code)){
            etResponse.setCode(ETEnums.SUCCESS.getCode());
            etResponse.setMsg(ETEnums.SUCCESS.getMsg());
            etResponse.setData(obj);
            return etResponse;
        }
        else if("444".equals(code)){
            etResponse.setCode(ETEnums.ERROR.getCode());
            etResponse.setMsg(ETEnums.ERROR.getMsg());
            etResponse.setData("");
            return etResponse;
        }else if("666".equals(code)){
            etResponse.setCode(ETEnums.LOGIN_SUCCESS.getCode());
            etResponse.setMsg(ETEnums.LOGIN_SUCCESS.getMsg());
            etResponse.setData("");
            return etResponse;
        }else if ("445".equals(code)){
            etResponse.setCode(ETEnums.LOGIN_ERROR.getCode());
            etResponse.setMsg(ETEnums.LOGIN_ERROR.getMsg());
            etResponse.setData("用户名或密码错误");
            return etResponse;
        }else if("446".equals(code)){
            etResponse.setCode(ETEnums.REDIS_ERROR.getCode());
            etResponse.setMsg(ETEnums.REDIS_ERROR.getMsg());
            etResponse.setData("Redis错误");
            return etResponse;
        }else if("447".equals(code)){
            etResponse.setCode(ETEnums.CHECKCODE_ERROR.getCode());
            etResponse.setMsg(ETEnums.CHECKCODE_ERROR.getMsg());
            etResponse.setData("验证码输入错误");
            return etResponse;
        }else if("449".equals(code)){
            etResponse.setCode(ETEnums.LOGIN_REPEAT.getCode());
            etResponse.setMsg(ETEnums.LOGIN_REPEAT.getMsg());
            etResponse.setData("用户名重复");
            return etResponse;
        }else {
            return null;
        }
    }

}
