package com.sue.service;


import com.sue.utils.PagedGridResult;

public interface ItemsESService {
	public PagedGridResult searchItems(
			String keywords,
			String sort,
			Integer page,
			Integer pageSize
			);
}
