package com.kaiyu.kyapiclientsdk;

import com.kaiyu.kyapiclientsdk.client.KyapiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kyapi.client")
@Data
@ComponentScan
public class kyapiClientConfig {

    private String accessKey;

    private  String secretKey;

    @Bean
    public KyapiClient kyapiClient(){
        return new KyapiClient(accessKey,secretKey);

    }
}
