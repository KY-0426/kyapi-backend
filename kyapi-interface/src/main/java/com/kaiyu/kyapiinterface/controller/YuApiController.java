package com.kaiyu.kyapiinterface.controller;

import com.kaiyu.kyapiclientsdk.model.ChatMsg;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/ai")
public class YuApiController {

    /**
     * 鱼聪明服务
     */
    @Resource
    private YuCongMingClient client;


    /**
     * 6.淘宝评价生成器
     * @param msg 消息
     * @return 生成结果
     */
    @RequestMapping("/getTaoBaoPLMsg")
    public String getTaoBaoPLMsg(@RequestBody ChatMsg msg, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println(request.getHeader("kaiyu"));
        String msgName = msg.getMsgName();
        DevChatRequest devChatRequest = new DevChatRequest();
        // 淘宝评价生成器
        devChatRequest.setModelId(1665918176010977282L);

        devChatRequest.setMessage(msgName);
//        获取响应结果
        BaseResponse<DevChatResponse> response = client.doChat(devChatRequest);
        return response.getData().getContent();

    }

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request){
        System.out.println(request.getHeader("kaiyu"));
        DevChatRequest devChatRequest = new DevChatRequest();
        // 淘宝评价生成器
        devChatRequest.setModelId(1665918176010977282L);
        devChatRequest.setMessage(name);
//        获取响应结果
        BaseResponse<DevChatResponse> response = client.doChat(devChatRequest);
        return response.getData().getContent();
    }

}
