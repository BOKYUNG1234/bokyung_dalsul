package com.dalsul.user.cart.service;

import java.util.List;

import com.dalsul.common.login.vo.UserVO;
import com.dalsul.user.cart.vo.CartVO;

public interface CartService {

	public int cartDelete(CartVO cvo);
	public void cartInsert(CartVO cvo);
	public int cartPlus(CartVO cvo);
	public int cartMinus(CartVO cvo);
	public List<CartVO> cartList(UserVO uvo);
	public UserVO getUserInfo(UserVO uvo);
}
