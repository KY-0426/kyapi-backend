package com.kaiyu.project.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.kaiyu.project.common.BaseResponse;
import com.kaiyu.project.model.dto.WxUserInfo;
import com.kaiyu.project.service.WxUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/wx/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WxUserInfoController {
    private final WxMaService wxMaService;

    private final WxUserInfoService wxUserInfoService;

    /**
     * 登陆接口
     */
    @GetMapping("/wxLogin")
    public HashMap<String,Object> wxLogin(@RequestParam("code") String code, HttpServletRequest request) {
        HashMap<String,Object>  re = new HashMap<>();
        re.put("data", wxUserInfoService.wxLogin(code,request));
        return re;
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @PostMapping("/getUserInfo")
    public BaseResponse<WxMaUserInfo> getUserInfo(@RequestBody WxUserInfo userInfo) {
        return wxUserInfoService.getUserInfo(userInfo);
    }

    @GetMapping("/wxGetPhoneNumber")
    public WxMaPhoneNumberInfo wxGetPhoneNumber(String code) throws WxErrorException {
        return wxMaService.getUserService().getPhoneNoInfo(code);
    }

}
