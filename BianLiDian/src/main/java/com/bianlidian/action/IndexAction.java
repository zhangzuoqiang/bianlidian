package com.bianlidian.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bianlidian.Constant;
import com.bianlidian.model.ItemsCategory;
import com.bianlidian.service.ItemsCategoryService;

@Controller
public class IndexAction {
	@Autowired
	private ItemsCategoryService categoryService;

	@RequestMapping("index")
	public String index(
			@RequestParam(required = false) String openId,
			@CookieValue(value = Constant.COOKIE_OPEN_ID, defaultValue = "test") String cookieOpenId,
			HttpServletResponse response, Model model) {
		List<ItemsCategory> categories = categoryService.findAll();

		model.addAttribute("categories", categories);

		if (StringUtils.isNotBlank(openId)) {
			response.addCookie(new Cookie(Constant.COOKIE_OPEN_ID, openId));
		} else {
			response.addCookie(new Cookie(Constant.COOKIE_OPEN_ID, cookieOpenId));
		}

		return "index";
	}
}
