package com.cdac.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.BillDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.BillReqDto;
import com.cdac.dto.BillRespDto;
import com.cdac.entities.Bill;
import com.cdac.entities.User;
import com.cdac.exceptions.BillNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BillRespDto createBillByUserId(BillReqDto dto, Long userId) {
		User user = userDao.findById(userId)
		        .orElseThrow(() -> new BillNotFoundException("User not found"));

		    Bill bill = modelMapper.map(dto, Bill.class);
		    bill.setUser(user);
		    bill.setDate(new Date()); // Set current date

		    Bill savedBill = billDao.save(bill);
		    return modelMapper.map(savedBill, BillRespDto.class);
	}

	@Override
	public BillRespDto getBillById(Long id) {
		Bill bill = billDao.findById(id).orElseThrow(() -> new BillNotFoundException("Bill not found"));
		return modelMapper.map(bill, BillRespDto.class);
	}

	@Override
	public List<BillRespDto> getAllBills() {
		return billDao.findAll().stream().map(bill -> modelMapper.map(bill, BillRespDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteBill(Long id) {
		if (!billDao.existsById(id)) {
			throw new BillNotFoundException("Bill not found");
		}
		billDao.deleteById(id);

	}

	@Override
	public List<BillRespDto> getBillsByUserId(Long userId) {
		List<Bill> bills = billDao.findByUserId(userId);
        return bills.stream()
                .map(bill -> modelMapper.map(bill, BillRespDto.class))
                .collect(Collectors.toList());
	}

}
