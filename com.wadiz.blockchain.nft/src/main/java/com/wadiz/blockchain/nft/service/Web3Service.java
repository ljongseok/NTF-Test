package com.wadiz.blockchain.nft.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.contracts.eip721.generated.ERC721;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
public class Web3Service {
    private final Logger logger = LoggerFactory.getLogger(Web3Service.class);
    private final Web3j web3j;

    public Web3Service(@Qualifier(value = "wadiz-web3j") Web3j web3j) {
        this.web3j = web3j;
    }

    public String getEthClientVersionSync() throws Exception {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        return web3ClientVersion.getWeb3ClientVersion();
    }

    public EthBlock.Block getBlock(long blockNum) throws IOException {
        return getBlock(BigInteger.valueOf(blockNum));
    }

    public EthBlock.Block getBlock(BigInteger blockNum) throws IOException {
        return getBlock(DefaultBlockParameter.valueOf(blockNum));
    }

    public EthBlock.Block getBlock(DefaultBlockParameter blockNum) throws IOException {
        EthBlock latestEthBlock = web3j.ethGetBlockByNumber(blockNum, false).send();
        return latestEthBlock.getBlock();
    }

    public BigInteger getLatestBlock() throws IOException {
        return getBlock(DefaultBlockParameterName.LATEST).getNumber();
    }

    public void getTransactionList(BigInteger blockNum){

    }

    public void getTransactionListByContract(long blockNum) throws IOException {
        getTransactionListByContract(BigInteger.valueOf(blockNum));
    }

    public void getTransactionListByContract(BigInteger blockNum) throws IOException{
        EthBlock.Block block = getBlock(blockNum);

        List<EthBlock.TransactionResult> transactionList = block.getTransactions();

        int maxCount = 3;
        int i=0;
        for(EthBlock.TransactionResult t: transactionList){

            logger.info("" + t.get() + ", type: " + t.get().getClass().getSimpleName());
            String txHash = String.valueOf(t.get());
            getTransactionInfo(txHash, blockNum);

            if(++i > maxCount-1){
                break;
            }

        }

    }

    public void getTransactionInfo(String txHash, BigInteger blockNum) throws IOException {
        Optional<Transaction> tx = web3j.ethGetTransactionByHash(txHash).send().getTransaction();
        if(tx.isPresent()){
            Transaction transaction = tx.get();

            String from = transaction.getFrom();
            String to = transaction.getTo();
            BigInteger v = transaction.getValue();

            String creates = transaction.getCreates();

            logger.info(blockNum + "]" + from + " -> " + to + " : " + v  + " -: " + creates);
        }
    }

    public void getTransactionInfo(String txHash) throws IOException {
        Optional<Transaction> tx = web3j.ethGetTransactionByHash(txHash).send().getTransaction();
        if(tx.isPresent()){
            Transaction transaction = tx.get();

            String from = transaction.getFrom();
            String to = transaction.getTo();
            BigInteger v = transaction.getValue();
            String creates = transaction.getCreates();
            String txType = transaction.getType();

            logger.info(txType + "] " + from + " -> " + to + " : " + v  + " -: " + creates);
        }
    }

    public void test(String address) throws Exception {
        // Prepare a wallet
        String pk = "0x9Be17fBcaAf1051204bA7ca4865B6d5f96Aa7D66";
        Credentials credentials = Credentials.create(pk);

        // Load the contract
        String contractAddress = "0xe7557a1E7B44b67D0dEa3AEEFF4307B88E5C38D0";

        ERC721 token = ERC721.load(contractAddress, web3j, credentials, new DefaultGasProvider());
        token.ownerOf(new BigInteger("1")); // 누가 가지고있냐..

//        token.safeTransferFrom(from, to, id, value);    //  value = 1, erc721 contract..
//        token.safeTransferFrom(from, to id, metadata, value);       //  with 1155

        //  contract -> erc721, erc1155 규약 ->

        BigInteger bal = token.balanceOf(pk).send();
        logger.info("bal: " + bal);

        //        String owner = token.ownerOf(new BigInteger("0")).send();
//        logger.info("owner: " + owner);
//        String name = token.name().send();
//        BigInteger decimal = token.decimals().send();
//
//        System.out.println("symbol: " + symbol);
//        System.out.println("name: " + name);
//        System.out.println("decimal: " + decimal.intValueExact());
    }


    //  TODO erc20
    //      https://greg.jeanmart.me/2019/08/20/manage-erc20-tokens-in-java-with-web3j/
    //  TODO web3j other libraries
    //      https://mvnrepository.com/artifact/org.web3j


}
