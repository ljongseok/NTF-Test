package com.wadiz.blockchain.nft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3Config {

    @Value("${web3j.rpc-address}")
    private String rpc;

    @Bean(name = "wadiz-web3j")
    public Web3j web3j() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(30*1000, TimeUnit.MILLISECONDS);
//        builder.writeTimeout(30*1000, TimeUnit.MILLISECONDS);
//        builder.readTimeout(30*1000, TimeUnit.MILLISECONDS);
//        OkHttpClient httpClient = builder.build();
//        Web3j web3j = Web3j.build(new HttpService(rpc,httpClient,false));
        System.out.println("rpc: " + rpc);
        Web3j web3j = Web3j.build(new HttpService(rpc));
        return web3j;
    }

}
