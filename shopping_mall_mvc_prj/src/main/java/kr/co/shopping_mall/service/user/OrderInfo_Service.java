package kr.co.shopping_mall.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.shopping_mall.dao.user.OrderInfo_DAO;

@Service
public class OrderInfo_Service {
	@Autowired
	private OrderInfo_DAO oid;
}
