package com.dalsul.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dalsul.common.login.vo.UserVO;
import com.dalsul.user.main.vo.ProductVO;
import com.dalsul.user.pay.vo.PayVO;


@Mapper
public interface ManagerDAO {
	public List<ProductVO> managerMainChart(ProductVO pvo);
	public List<UserVO> getUserList(UserVO uvo);
	public int userListCnt(UserVO uvo);
	public int dropUsers(List<Integer> user_no);
	public int appointManager(List<Integer> user_no);
	public List<PayVO> orderListViewM(PayVO pvo);
	public int updateOrderStatus(List<PayVO> selectedOrders);
	public List<PayVO> getProductQuantity(PayVO pvo);
	
}
