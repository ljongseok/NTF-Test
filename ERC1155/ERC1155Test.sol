// contracts/GameItems.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC1155/ERC1155.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract PopFlower1155 is ERC1155, Ownable {
    uint256 public constant PPF1 = 0;
    uint256 public constant PPF2 = 1;
    uint256 public constant PPF3 = 2;

    constructor() ERC1155("https://raw.githubusercontent.com/ljongseok/NTF-Test/master/ERC1155/metadata/PPF-{id}.json") {
        _mint(msg.sender, PPF1, 2, "");
        _mint(msg.sender, PPF2, 3, "");
        _mint(msg.sender, PPF3, 1, "");
    }
}