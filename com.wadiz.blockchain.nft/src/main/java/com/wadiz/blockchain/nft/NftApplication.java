package com.wadiz.blockchain.nft;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class NftApplication {

    private static final Map<String, Object> DEFAULT_PROPS = new HashMap<>();

    static {
        DEFAULT_PROPS.put("spring.application.name", "nft");
        DEFAULT_PROPS.put("spring.config.name", "${spring.application.name},${spring.application.name}-local");
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder().properties(DEFAULT_PROPS).sources(NftApplication.class).run(args);
    }

}
