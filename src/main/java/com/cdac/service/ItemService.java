package com.cdac.service;

import java.util.List;

import com.cdac.dto.ItemReqDto;
import com.cdac.dto.ItemRespDto;

public interface ItemService {

	ItemRespDto createItem(ItemReqDto dto);

	ItemRespDto getItemById(Long id);

	List<ItemRespDto> getItemsByBillId(Long billId);

	ItemRespDto updateItem(Long id, ItemReqDto dto);

	void deleteItem(Long id);

}
