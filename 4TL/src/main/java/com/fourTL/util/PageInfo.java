package com.fourTL.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

import jakarta.servlet.ServletException;


public class PageInfo {
	public static Map<PageType, PageInfo> pageRoute = new HashMap<>();

	static {
		// User
		pageRoute.put(PageType.HOMEPAGE, new PageInfo("TRANG CHỦ", "index", "home/home.jsp"));
		pageRoute.put(PageType.SITE_SHOP, new PageInfo("SHOP", "index", "shop/shop.jsp"));
		pageRoute.put(PageType.SITE_PRODUCT, new PageInfo("CHI TIẾT", "index", "ChiTiet-SanPham/ChiTietSanPham.jsp"));
		pageRoute.put(PageType.SITE_SHOPPINGCART, new PageInfo("GIỎ HÀNG", "index", "Giohang/Giohang.jsp"));
		pageRoute.put(PageType.SITE_CHECKOUT, new PageInfo("THANH TOÁN", "index", "ThanhToan/ThanhToan.jsp"));
		pageRoute.put(PageType.SITE_LOGIN, new PageInfo("ĐĂNG NHẬP", "index", "Login/login-register.jsp"));
		pageRoute.put(PageType.SITE_LOGIN_PARTNER, new PageInfo("PARTNER", "index", "Login/login-partner.jsp"));
		pageRoute.put(PageType.SITE_USERPROFILE, new PageInfo("User_Profile", "index", "user_profile/user_profile.jsp"));
		pageRoute.put(PageType.SITE_PARTNER, new PageInfo("CỬA HÀNG", "index", "Partner/partner_form.jsp"));
		pageRoute.put(PageType.SITE_ABOUTUS, new PageInfo("VỀ CHÚNG TÔI", "index", "about_us/about_us.jsp"));
		pageRoute.put(PageType.SITE_CONTACT, new PageInfo("LIÊN HỆ", "index", "contact/contact.jsp"));
		
		// Admin
		pageRoute.put(PageType.ADMIN_CHART, new PageInfo("THỐNG KÊ", "admin/chart.jsp", null));
		pageRoute.put(PageType.ADMIN_CUSTOMER, new PageInfo("KHÁCH HÀNG", "admin/customer.jsp", null));
		pageRoute.put(PageType.ADMIN_WAREHOUSE, new PageInfo("KHO", "admin/warehouse.jsp", null));
	}

	public static String goAdmin(Model model, PageType pageTyge)
			throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageTyge);

		model.addAttribute("page", page);
		
		return page.JSPName;
	}
	
	public static String goSite(Model model, PageType pageTyge)
			throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageTyge);

		model.addAttribute("page", page);
		
		return page.JSPName;
	}

	
	private String title;
	private String JSPName;
	private String linkFile;
	
	
	public PageInfo(String title, String JSPName, String linkFile) {
		super();
		this.title = title;
		this.JSPName = JSPName;
		this.linkFile = linkFile;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getJSPName() {
		return JSPName;
	}
	public void setJSPName(String JSPName) {
		this.JSPName = JSPName;
	}
	public String getlinkFile() {
		return linkFile;
	}
	public void setlinkFile(String linkFile) {
		this.linkFile = linkFile;
	}
}
