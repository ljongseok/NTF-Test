package com.wadiz.blockchain.nft.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandResponse<T> {
    private T data;
}
