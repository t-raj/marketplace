package main.java.util;

import java.util.ArrayList;
import java.util.List;

import main.java.model.bean.CustomerBean;
import main.java.model.bean.OrderBean;
import main.java.model.bean.OrderLineBean;
import main.java.model.bean.PartnerBean;
import main.java.model.bean.ProductBean;
import main.java.model.entity.Customer;
import main.java.model.entity.Order;
import main.java.model.entity.OrderLine;
import main.java.model.entity.Partner;
import main.java.model.entity.Product;
import main.java.service.model.OrderModel;
import main.java.service.model.PartnerModel;
import main.java.service.model.ProductModel;

public class ElementUtil {
	
	public static final Customer buildCustomer(CustomerBean customerBean) {
		Customer customer = new Customer();
		
		if (customerBean != null) {
			customer.setActive(true);
			customer.setCity(customerBean.getAddress().getCity());
			customer.setFirstName(customerBean.getFirstName());
			customer.setLastName(customerBean.getLastName());
			customer.setLogin(customerBean.getLogin());
			customer.setState(customerBean.getAddress().getState());
			customer.setStreetAddress(customerBean.getAddress().getStreetAddress());
			customer.setZip_code(customerBean.getAddress().getZipCode());
			customer.setId(customerBean.getId());
        }

        return customer;
	}
	
	public static final CustomerBean buildCustomerBean(Customer customer) {
		CustomerBean customerBean = new CustomerBean();
		
		if (customer != null) {
			customerBean.setId(customer.getId());
			customerBean.getAddress().setCity(customer.getCity());
			customerBean.setFirstName(customer.getFirstName());
			customerBean.setLastName(customer.getLastName());
			customerBean.setLogin(customer.getLogin());
			customerBean.getAddress().setState(customer.getState());
			customerBean.getAddress().setStreetAddress(customer.getStreetAddress());
			customerBean.getAddress().setZipCode(customer.getZip_code());
			customerBean.setActive(customer.getActive());
        }

        return customerBean;
	}

	public static List<CustomerBean> buildCustomerBeanList(List<Customer> customers) {
		List<CustomerBean> customerBeans = new ArrayList<CustomerBean>();
		for (Customer customer : customers) {
			customerBeans.add(buildCustomerBean(customer));
		}
		return customerBeans;
	}
	
	public static final Product buildProduct(ProductBean productBean){
		
		Product product = new Product();
		
		if(productBean !=null){
			product.setId(productBean.getId());
			product.setDescription(productBean.getDescription());
			product.setPrice(productBean.getPrice());
			product.setActive(productBean.isActive());
		}
		return product;
	}
	
	public static final ProductBean buildProductBean(Product product){
		ProductBean productBean = new ProductBean();
		
		if(product !=null){
			productBean.setId(productBean.getId());
			productBean.setDescription(productBean.getDescription());
			productBean.setPrice(productBean.getPrice());
			productBean.setActive(productBean.isActive());
		}
		return productBean;
		
	}
	
	public static final Partner buildPartner(PartnerBean partnerBean) {
		Partner partner = new Partner();
		
		if (partnerBean != null) {
			partner.setActive(true);
			partner.setCity(partnerBean.getCity());
			partner.setFirstName(partnerBean.getFirstName());
			partner.setLastName(partnerBean.getLastName());
			partner.setLogin(partnerBean.getLogin());
			partner.setState(partnerBean.getState());
			partner.setStreetAddress(partnerBean.getStreetAddress());
			partner.setZip_code(partnerBean.getZip_code());
			partner.setId(partnerBean.getId());
        }

        return partner;
	}
	
	public static final PartnerBean buildPartnerBean(Partner partner) {
		PartnerBean partnerBean = new PartnerBean();
		
		if (partner != null) {
			partnerBean.setId(partner.getId());
			partnerBean.setCity(partner.getCity());
			partnerBean.setFirstName(partner.getFirstName());
			partnerBean.setLastName(partner.getLastName());
			partnerBean.setLogin(partner.getLogin());
			partnerBean.setState(partner.getState());
			partnerBean.setStreetAddress(partner.getStreetAddress());
			partnerBean.setZip_code(partner.getZip_code());
			partnerBean.setActive(partner.isActive());
        }

        return partnerBean;
	}
	
	public static final PartnerBean buildPartnerBean(PartnerModel partnerModel) {
		PartnerBean partnerBean = new PartnerBean();
		
		if (partnerModel != null) {
			partnerBean.setId(partnerModel.getId());
			partnerBean.setCity(partnerModel.getCity());
			partnerBean.setFirstName(partnerModel.getFirstName());
			partnerBean.setLastName(partnerModel.getLastName());
			partnerBean.setLogin(partnerModel.getLogin());
			partnerBean.setState(partnerModel.getState());
			partnerBean.setStreetAddress(partnerModel.getStreetAddress());
			partnerBean.setZip_code(partnerModel.getZip_code());
			partnerBean.setActive(partnerModel.isActive());
        }

        return partnerBean;
	}


	public static List<PartnerBean> buildPartnerBeanList(List<Partner> partners) {
		List<PartnerBean> partnerBeans = new ArrayList<PartnerBean>();
		for (Partner partner :partners) {
			partnerBeans.add(buildPartnerBean(partner));
		}
		return partnerBeans;
	}
	
	public static final Order buildOrder(OrderBean orderBean){
		Order order = new Order();
		if(orderBean !=null){
			order.setId((int) orderBean.getId());
			order.setCustomerId((int) orderBean.getCustomerId());
			
			if (orderBean.getStatus() != null) {
				order.setStatus(orderBean.getStatus().toString());
			}
		}
		return order;
	} 
	
	public static final OrderBean buildOrderBean(Order order) {
		OrderBean orderBean = new OrderBean();
		
		if (order != null) {
			orderBean.setId(order.getId());
			orderBean.setCustomerId(order.getCustomerId());
			orderBean.setPartnerId(order.getPartnerId());
			orderBean.setStatus(orderBean.getStatus());
			
        }
        return orderBean;
	}
	
	public static List<OrderBean> buildOrderBeanList(List<Order> orders) {
		
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		for (Order order :orders) {
			orderBeans.add(buildOrderBean(order));
		}
		return orderBeans;
	}
	
	public static final OrderLine buildOrderLine(OrderLineBean orderLineBean){
		OrderLine orderLine = new OrderLine();
		if(orderLineBean !=null){
			orderLine.setId(orderLineBean.getId());
			orderLine.setLineNumber(orderLineBean.getLineNumber());
			orderLine.setQuantity(orderLineBean.getQuantity());
		}
		return orderLine;
	} 
	
	public static final OrderLineBean buildOrderLineBean(OrderLine orderLine) {

		OrderLineBean orderLineBean = new OrderLineBean();
		if (orderLine != null) {
			orderLineBean.setId(orderLine.getId());
			orderLineBean.setLineNumber(orderLine.getLineNumber());
			orderLineBean.setQuantity(orderLine.getQuantity());
        }
        return orderLineBean;
	}
	
	public static List<OrderLineBean> buildOrderLineBeanList(List<OrderLine> orderLines) {
		
		List<OrderLineBean> orderLineBeans = new ArrayList<OrderLineBean>();
		for (OrderLine orderLine : orderLines) {
			orderLineBeans.add(buildOrderLineBean(orderLine));
		}
		return orderLineBeans;
	}

	public static ProductModel buildProductModel(ProductBean productBean) {
		if (productBean == null) {
			return null;
		}
		
		ProductModel productModel = new ProductModel();
		productModel.setpId(productBean.getId());
		productModel.setDescription(productBean.getDescription());
		productModel.setPrice(productBean.getPrice());
		return productModel;
	}

	public static OrderModel buildOrderModel(OrderBean orderBean) {
		if(orderBean == null){
			return null;
		}
		OrderModel orderModel = new OrderModel();
		orderModel.setOrderId((int) orderBean.getId());
		orderModel.setCustomerId(orderBean.getCustomerId());
		orderModel.setStatus(orderBean.getStatus());
		orderModel.setProductIds(orderBean.getProductIds());
		
		return orderModel;
	}

	public static OrderBean buildOrderBean(OrderModel order) {
		OrderBean orderBean = new OrderBean();
		
		if (order != null) {
			orderBean.setId(order.getOrderId());
			orderBean.setCustomerId(order.getCustomerId());
			orderBean.setStatus(orderBean.getStatus());
			orderBean.setProductIds(order.getProductIds());
        }
        return orderBean;
	}

	public static List<OrderModel> buildOrderModelList(List<OrderBean> orderBeans) {
		List<OrderModel> orderModels = new ArrayList<OrderModel>();
		for (OrderBean orderBean :orderBeans) {
			orderModels.add(buildOrderModel(orderBean));
		}
		return orderModels;
	}

	public static ProductBean buildProductBean(ProductModel product) {
		ProductBean productBean = new ProductBean();
		if (product != null) {
			productBean.setId(product.getpId());
			productBean.setDescription(product.getDescription());
			productBean.setPartnerID(product.getPartnerId());
			productBean.setNumAvailable(product.getNumberAvailable());
			productBean.setPrice(product.getPrice());
			productBean.setActive(true);
        }
		return productBean;
	}

	public static PartnerModel buildPartnerModel(PartnerBean partnerBean) {
		// TODO Auto-generated method stub
		
		if(partnerBean ==null){
			return null;
		}
		PartnerModel partnerModel = new PartnerModel();
		partnerModel.setId(partnerBean.getId());
		partnerModel.setFirstName(partnerModel.getFirstName());
		partnerModel.setLastName(partnerBean.getLastName());
		partnerModel.setLogin(partnerBean.getLogin());
		partnerModel.setStreetAddress(partnerBean.getStreetAddress());
		partnerModel.setCity(partnerBean.getCity());
		partnerModel.setState(partnerBean.getState());
		partnerModel.setActive(partnerBean.isActive());
		
		
		return null;
	}

}
