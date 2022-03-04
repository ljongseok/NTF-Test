package com.wadiz.blockchain.nft.controller;

import com.wadiz.blockchain.nft.model.CommandResponse;
import com.wadiz.blockchain.nft.service.Web3Service;
import com.wadiz.blockchain.nft.utils.Web3Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/blockchain")
public class NFTController {
    private final Logger logger = LoggerFactory.getLogger(NFTController.class);

    private final Web3Service web3Service;

    public NFTController(Web3Service web3Service) {
        this.web3Service = web3Service;
    }

    public <T> ResponseEntity<CommandResponse<T>> response(T data) {
        CommandResponse response = CommandResponse.builder().data(data).build();
        return ResponseEntity.ok(response);
    }

    public <T> ResponseEntity<T> responseObject(T data){
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/block")
    public ResponseEntity<?> getCurrentBlock(){
//        return responseObject("block.." + new Date());
        String msg = "";

        try {
            msg = web3Service.getEthClientVersionSync();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return responseObject(msg);
    }

    @PostMapping(value = "/block/{blockNo}")
    public ResponseEntity<?> getBlock(@PathVariable Long blockNo){
        String msg = "TEST";

        try {
//            BigInteger no = web3Service.getBlock(blockNo).getNumber();
            web3Service.getTransactionListByContract(blockNo);
//            msg = "request: " + blockNo + ", response: " + no;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return responseObject(msg);
    }

    @PostMapping(value = "/block/transaction")
    public ResponseEntity<?> getTransaction(@RequestBody(required = false) Map<String, Object> payload){
        String msg = "TEST";

        try {
            String txHash = (String) payload.getOrDefault("txHash", null);
            if(txHash == null)
                return responseObject(msg);

            web3Service.getTransactionInfo(txHash);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return responseObject(msg);
    }

    @PostMapping(value = "/erc721/test")
    public ResponseEntity<?> getERC721(@RequestBody(required = false) Map<String, Object> payload){
        String msg = "TEST";

        try {
            String address = (String) payload.getOrDefault("address", null);
            web3Service.test(address);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return responseObject(msg);
    }

    @PostMapping(value = "/block/latest")
    public ResponseEntity<?> getLatestBlock(){
        String msg = "";

        try {
            BigInteger no = web3Service.getLatestBlock();
            msg = "response: " + no;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return responseObject(msg);
    }

    @PostMapping(value = "/convert/wei")
    public ResponseEntity<?> convertToWei(@RequestBody(required = false) Map<String, Object> payload){
        logger.info(payload.toString());
        String v = (String) payload.getOrDefault("value", null);
        BigDecimal cv = Web3Utils.toWei(v);

        String msg = v + " eth to " + cv + " wei";

        return responseObject(msg);
    }

    @PostMapping(value = "/convert/eth")
    public ResponseEntity<?> convertToETH(@RequestBody(required = false) Map<String, Object> payload){
        logger.info(payload.toString());
        String v = (String) payload.getOrDefault("value", null);
        BigDecimal cv = Web3Utils.toEth(v);

        String msg = v + " wei to " + cv + " eth";

        return responseObject(msg);
    }
}
