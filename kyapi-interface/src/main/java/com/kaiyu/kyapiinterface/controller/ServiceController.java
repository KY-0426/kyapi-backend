package com.kaiyu.kyapiinterface.controller;

import cn.hutool.json.JSONUtil;
import icu.qimuu.qiapisdk.exception.ApiException;
import icu.qimuu.qiapisdk.model.params.RandomWallpaperParams;
import icu.qimuu.qiapisdk.model.response.RandomWallpaperResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kaiyu.kyapiinterface.utils.RequestUtils.buildUrl;
import static com.kaiyu.kyapiinterface.utils.RequestUtils.get;

/**
 * 接口服务：包括获取名称接口和第三方接口
 */
@RestController
@RequestMapping("/")
public class ServiceController {

    // TODO 接口校验请求头（是否包含 API 网关染色的请求头）

    /**
     * 2. 随机毒鸡汤
     *
     * @return
     */
    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup() {
        return get("https://api.vvhan.com/api/moyu");// 真实的第三方接口地址
    }

    /**
     * 3. 随机壁纸
     *
     * @param randomWallpaperParams
     * @return
     * @throws ApiException
     */
    @GetMapping("/randomWallpaper")
    public RandomWallpaperResponse randomWallpaper(RandomWallpaperParams randomWallpaperParams) throws ApiException {
        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
//        String baseUrl = "https://api.vvhan.com/api/view?type=json";
        String url = buildUrl(baseUrl, randomWallpaperParams);
        if (StringUtils.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
            url = url + "?format=json";
        } else {
            url = url + "&format=json";
        }
        return JSONUtil.toBean(get(baseUrl), RandomWallpaperResponse.class);
    }

    /**
     * 4. 随机土味情话
     *
     * @return
     */
    @GetMapping("/loveTalk")
    public String randomLoveTalk() {
        return get("https://api.vvhan.com/api/love");
    }

    /**
     * 5. 每日一句励志英语
     *
     * @return
     */
    @GetMapping("/en")
    public String dailyEnglish() {
        return get("https://api.vvhan.com/api/en");
    }

    /**
     * 7. 随机笑话
     *
     * @return
     */
    @GetMapping("/joke")
    public String randomJoke() {
        return get("https://api.vvhan.com/api/joke");
    }


}