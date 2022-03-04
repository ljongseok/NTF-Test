package com.wadiz.blockchain.nft.schedule;

import com.wadiz.blockchain.nft.service.Web3Service;
import com.wadiz.blockchain.nft.utils.Web3Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlockReader {
    private final Logger logger = LoggerFactory.getLogger(BlockReader.class);
    private final Web3Service service;

    public BlockReader(Web3Service service) {
        this.service = service;
    }

    //    @Scheduled(fixedDelayString = "${schedule.block.ten-seconds}")
//    @Scheduled(fixedRateString = "${schedule.block.ten-seconds}")
    public void readerProc(){
        String threadName = Thread.currentThread().getName();
        logger.info("==> Reader Process Start - " + threadName);
//        test();
        Web3Utils.toWei(17);
        Web3Utils.toEth("3100000000000000000");

        logger.info("==> Reader Process End - " + threadName);
    }

    public void test(){
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*@Scheduled(fixedDelay = 3000, initialDelay = 1000)
    public void readerProc2(){
        String threadName = Thread.currentThread().getName();

        logger.info("==> Reader2 Process Start - " + threadName);
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("==> Reader2 Process End - " + threadName);
    }*/

    /*@Async
    public void test(Date d){
        try {
            logger.info("==> Reader Process Sleep - " + + d.getTime());
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
