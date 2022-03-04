// contracts/GameItems.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC1155/ERC1155.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/utils/Strings.sol";

contract PopFlower1155 is ERC1155, Ownable {
    uint256 public constant PPF1 = 0;
    uint256 public constant PPF2 = 1;
    uint256 public constant PPF3 = 2;

    string public name;
    string public symbol;

    mapping (uint256 => bool) public createdTokenId;

    constructor() ERC1155("https://raw.githubusercontent.com/ljongseok/NTF-Test/master/ERC1155/metadata/PPF-{id}.json") {
        name = "PopFlower 1155";
        symbol = "PPF";

        _mint(msg.sender, PPF1, 10, "More Data 1");
        _mint(msg.sender, PPF2, 20, "More Data 2");
        _mint(msg.sender, PPF3, 30, "More Data 3");

        createdTokenId[PPF1] = true;
        createdTokenId[PPF2] = true;
        createdTokenId[PPF3] = true;
    }

    function create(address to, uint256 amount, uint256 tokenId) public onlyOwner returns (bool)
    {
        bool isExists = existsTokenId(tokenId);
        require(isExists, "ERC1155: already exists token id");

        if(isExists){
            return false;
        }
        
        _mint(to, tokenId, amount, "");
        createdTokenId[tokenId] = true;
        return true;
    }
     
    function existsTokenId(uint256 tokenId) public view returns (bool)
    {
        return createdTokenId[tokenId];
    }

    function uri(uint256 tokenId) override public view returns (string memory){
        //  Return Uri -> https://raw.gi....../PPF-{tokenId}.json
        return (
            string(abi.encodePacked(
                "https://raw.githubusercontent.com/ljongseok/NTF-Test/master/ERC1155/metadata/PPF-",
                Strings.toString(tokenId),
                ".json"
            ))
        );
    }

}