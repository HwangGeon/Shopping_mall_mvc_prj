package kr.co.shopping_mall.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderInfo_DAO {
	@Autowired(required = false)
	private JdbcTemplate jt;
}
