package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.ItemDao;
import com.cdac.dto.ItemReqDto;
import com.cdac.dto.ItemRespDto;
import com.cdac.entities.Item;
import com.cdac.exceptions.ItemNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ItemRespDto createItem(ItemReqDto dto) {
		Item item = modelMapper.map(dto, Item.class);
		Item saved = itemDao.save(item);
		return modelMapper.map(saved, ItemRespDto.class);
	}

	@Override
	public ItemRespDto getItemById(Long id) {
		Item item = itemDao.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found"));
		return modelMapper.map(item, ItemRespDto.class);
	}
	
	@Override
	public List<ItemRespDto> getItemsByBillId(Long billId) {
		List<Item> items = itemDao.findByBill_Id(billId);
		return items.stream().map(item -> {
			ItemRespDto resp = modelMapper.map(item, ItemRespDto.class);
			resp.setId(billId);
			return resp;
		}).collect(Collectors.toList());
	}

	@Override
	public ItemRespDto updateItem(Long id, ItemReqDto dto) {
		Item item = itemDao.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found"));
		item.setItemName(dto.getItemName());
		item.setQuantity(dto.getQuantity());
		Item updated = itemDao.save(item);
		return modelMapper.map(updated, ItemRespDto.class);
	}

	@Override
	public void deleteItem(Long id) {
		if (!itemDao.existsById(id)) {
			throw new ItemNotFoundException("Item not found");
		}
		itemDao.deleteById(id);
	}

	

}
