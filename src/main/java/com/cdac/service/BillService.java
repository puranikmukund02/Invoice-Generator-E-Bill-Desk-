package com.cdac.service;

import java.util.List;

import com.cdac.dto.BillReqDto;
import com.cdac.dto.BillRespDto;

public interface BillService {

	BillRespDto createBillByUserId(BillReqDto dto, Long userId);

	BillRespDto getBillById(Long id);

	List<BillRespDto> getAllBills();

	List<BillRespDto> getBillsByUserId(Long userId);

	void deleteBill(Long id);

}
