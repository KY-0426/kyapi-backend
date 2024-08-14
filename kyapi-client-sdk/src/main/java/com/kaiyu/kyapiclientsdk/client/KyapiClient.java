package com.kaiyu.kyapiclientsdk.client;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.kaiyu.kyapiclientsdk.model.ChatMsg;
import com.kaiyu.kyapiclientsdk.model.User;



import java.util.HashMap;
import java.util.Map;

import static com.kaiyu.kyapiclientsdk.utils.SignUtils.genSign;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 客户端
 */
public class KyapiClient {

    private static final String GATEWAY_HOST = "https://www.wenkaiyu.top:8091";
    private final String accessKey;
    private final String secretKey;


    private static final String EXTRA_BODY = "kaiyu";



    public KyapiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 随机获取一句毒鸡汤
     * @return
     */
    public String getPoisonousChickenSoup() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/poisonousChickenSoup")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机壁纸
     * @return
     */
    public String getRandomWallpaper() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/randomWallpaper")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机土味情话
     * @return
     */
    public String getLoveTalk() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/loveTalk")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 每日一句励志英语
     * @return
     */
    public String getDailyEnglish() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/en")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机笑话
     * @return
     */
    public String getRandomJoke() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/joke")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     *
     * @param name
     * @return
     */
    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST+"/api/name/get", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost( String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST+"/api/name/post", paramMap);
        System.out.println(result);
        return result;
    }



    /**
     * 构造请求头
     * @return
     */
    private Map<String, String> headerMap(String body){
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        // 一定不能直接发送
//        hashMap.put("secretKey",secretKey);
        // 生成随机数
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        // 请求体内容
        hashMap.put("body", URLEncodeUtil.encode(body, UTF_8));// 解决中文乱码
        // 当前时间戳
//        System.currentTimeMillis() 返回当前时间的毫秒数。通过除以1000 可以将毫秒数转换为秒数，已得到当前时间戳的秒级显示
//        String.valueOf()方法用于将数值转换成字符串
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 生成签名
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }




    /**
     * 通过POST请求获取用户名
     * @param user  用户对象
     * @return 从服务器获取的用户名
     */
    public String getUserNameByPost( User user) {

        String json = JSONUtil.toJsonStr(user);

        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/users")
                .addHeaders(headerMap(json)) // 添加请求头
                .body(json)// 将Json字符串设置为请求体
                .execute(); // 执行请求
        //System.out.println(httpResponse.getStatus());
        String result =  httpResponse.body();
        return result;
    }


    /**
     * 生成淘宝评论
     * @param chatMsg
     * @return
     */
    public String getTaoBaoPLMsg( ChatMsg chatMsg) {
        String json = JSONUtil.toJsonStr(chatMsg);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/ai/getTaoBaoPLMsg")
                .addHeaders(headerMap(json)) // 添加请求头
                .body(json)// 将Json字符串设置为请求体
                .execute(); // 执行请求
        String result =  httpResponse.body();
        return result;
    }
}

