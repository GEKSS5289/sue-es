package com.sue.controller;

import com.sue.service.ItemsESService;
import com.sue.utils.IMOOCJSONResult;
import com.sue.utils.PagedGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sue
 * @date 2020/8/13 11:15
 */

@RestController
@RequestMapping("items")
public class ItemController {


    @Autowired
    private ItemsESService itemsESService;

    @GetMapping("/es/search")
    public IMOOCJSONResult search(
            @RequestParam String keywords,
            @RequestParam String sort,
            @RequestParam(defaultValue = "0",required = false) Integer page,
            @RequestParam(defaultValue = "20",required = false)Integer pageSize
    ){


        if(StringUtils.isBlank(keywords)){
            return IMOOCJSONResult.errorMsg(null);
        }

        PagedGridResult gridResult = itemsESService.searchItems(keywords, sort, page, pageSize);

        return IMOOCJSONResult.ok(gridResult);

    }
}
