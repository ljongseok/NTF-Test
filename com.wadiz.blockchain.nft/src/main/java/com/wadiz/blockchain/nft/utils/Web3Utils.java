package com.wadiz.blockchain.nft.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class Web3Utils {
    private static final Logger logger = LoggerFactory.getLogger(Web3Utils.class);

    //  eth -> wei
    public static BigDecimal toWei(int eth){
        return toWei(String.valueOf(eth));
    }

    //  eth -> wei
    public static BigDecimal toWei(String eth){
        return toWei(new BigDecimal(eth));
    }

    //  eth -> wei
    public static BigDecimal toWei(BigDecimal eth){
        BigDecimal wei = Convert.toWei(eth, Convert.Unit.ETHER);
        logger.info(eth + " eth is " + wei + " wei [" + wei.toString().length() + "]");
        return wei;
    }

    //  wei -> eth
    public static BigDecimal toEth(String wei){
        return toEth(new BigDecimal(wei));
    }

    //  wei -> eth
    public static BigDecimal toEth(BigDecimal wei){
        BigDecimal eth = Convert.fromWei(wei, Convert.Unit.ETHER);
        logger.info(wei + " wei [" + wei.toString().length() + "] is " + eth + " eth");
        return eth;
    }
}
