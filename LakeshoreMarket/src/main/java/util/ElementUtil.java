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
import main.java.service.representation.CustomerRepresentation;
import main.java.service.representation.OrderRepresentation;
import main.java.service.representation.PartnerRepresentation;
import main.java.service.representation.ProductRepresentation;
import main.java.service.service.OrderService;

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
	
	public static List<CustomerRepresentation> buildCustomerModelList(List<CustomerBean> customerBeanList) {
		List<CustomerRepresentation> customerRepresentations = new ArrayList<CustomerRepresentation>();
		for (CustomerBean customerBean : customerBeanList) {
			customerRepresentations.add(buildCustomerModel(customerBean));
		}
		return customerRepresentations;
	}
	
	public static CustomerRepresentation buildCustomerModel(CustomerBean customerBean) {
		CustomerRepresentation customerRepresentation = new CustomerRepresentation();
		
		if (customerBean != null) {
			customerRepresentation.setId(customerBean.getId());
			customerRepresentation.getAddress().setCity(customerBean.getAddress().getCity());
			customerRepresentation.setFirstName(customerBean.getFirstName());
			customerRepresentation.setLastName(customerBean.getLastName());
			customerRepresentation.setLogin(customerBean.getLogin());
			if (customerBean.getAddress() != null) {
				customerRepresentation.getAddress().setState(customerBean.getAddress().getState());
				customerRepresentation.getAddress().setStreetAddress(customerBean.getAddress().getStreetAddress());
				customerRepresentation.getAddress().setZipCode(customerBean.getAddress().getZipCode());
			}
        }

        return customerRepresentation;
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
			productBean.setId(product.getId());
			productBean.setDescription(product.getDescription());
			productBean.setPartnerID(product.getPartnerID());
			productBean.setPrice(product.getPrice());
			productBean.setActive(product.isActive());
			productBean.setNumAvailable(product.getQuantity());
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
			partner.setPassword(partnerBean.getPassword());
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
			partnerBean.setPassword(partner.getPassword());
        }

        return partnerBean;
	}
	
	public static final PartnerBean buildPartnerBean(PartnerRepresentation partnerModel) {
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
			partnerBean.setPassword(partnerModel.getPassword());
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
		if(orderBean != null){
			order.setId(orderBean.getId());
			order.setCustomerId(orderBean.getCustomerId());
			order.setPartnerId(orderBean.getPartnerId());
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
			orderBean.setStatus(Enum.valueOf(OrderService.Status.class, order.getStatus()));
			
        }
        return orderBean;
	}
	
	public static List<OrderBean> buildOrderBeanList(List<Order> orders) {
		List<OrderBean> orderBeans = new ArrayList<OrderBean>();
		if (orders != null && !orders.isEmpty()) {
			for (Order order :orders) {
				orderBeans.add(buildOrderBean(order));
			}
		}

		return orderBeans;
	}
	
	public static final OrderLine buildOrderLine(OrderLineBean orderLineBean){
		OrderLine orderLine = new OrderLine();
		if(orderLineBean !=null){
			orderLine.setOrderId(orderLineBean.getId());
			orderLine.setLineNumber(orderLineBean.getLineNumber());
			orderLine.setQuantity(orderLineBean.getQuantity());
		}
		return orderLine;
	} 
	
	public static final OrderLineBean buildOrderLineBean(OrderLine orderLine) {
		OrderLineBean orderLineBean = new OrderLineBean();
		if (orderLine != null) {
			orderLineBean.setId(orderLine.getOrderId());
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

	public static ProductRepresentation buildProductModel(ProductBean productBean) {
		ProductRepresentation productModel = new ProductRepresentation();
		if (productBean == null) {
			return null;
		}
		else{
		productModel.setproductId(productBean.getId());
		productModel.setPartnerId(productBean.getPartnerID());
		productModel.setDescription(productBean.getDescription());
		productModel.setPrice(productBean.getPrice());
		productModel.setNumberAvailable(productBean.getNumAvailable());
		}
		return productModel;
	}

	public static OrderRepresentation buildOrderModel(OrderBean orderBean) {
		if(orderBean == null){
			return null;
		}
		OrderRepresentation orderModel = new OrderRepresentation();
		orderModel.setOrderId(orderBean.getId());
		orderModel.setPartnerId(orderBean.getPartnerId());
		orderModel.setCustomerId(orderBean.getCustomerId());
		orderModel.setStatus(orderBean.getStatus());
		orderModel.setProductIds(orderBean.getProductIds());
		
		return orderModel;
	}

	public static OrderBean buildOrderBean(OrderRepresentation order) {
		OrderBean orderBean = new OrderBean();
		
		if (order != null) {
			orderBean.setId(order.getOrderId());
			orderBean.setCustomerId(order.getCustomerId());
			orderBean.setPartnerId(order.getPartnerId());
			orderBean.setStatus(order.getStatus());
			orderBean.setProductIds(order.getProductIds());
        }
        return orderBean;
	}

	public static List<OrderRepresentation> buildOrderModelList(List<OrderBean> orderBeans) {
		List<OrderRepresentation> orderModels = new ArrayList<OrderRepresentation>();
		for (OrderBean orderBean :orderBeans) {
			orderModels.add(buildOrderModel(orderBean));
		}
		return orderModels;
	}

	public static ProductBean buildProductBean(ProductRepresentation product) {
		ProductBean productBean = new ProductBean();
		if (product != null) {
			productBean.setId(product.getproductId());
			productBean.setDescription(product.getDescription());
			productBean.setPartnerID(product.getPartnerId());
			productBean.setNumAvailable(product.getNumberAvailable());
			productBean.setPrice(product.getPrice());
			productBean.setActive(true);
        }
		return productBean;
	}

	public static PartnerRepresentation buildPartnerModel(PartnerBean partnerBean) {
		if (partnerBean == null){
			return null;
		}
		
		PartnerRepresentation partnerModel = new PartnerRepresentation();
		partnerModel.setId(partnerBean.getId());
		partnerModel.setFirstName(partnerBean.getFirstName());
		partnerModel.setLastName(partnerBean.getLastName());
		partnerModel.setLogin(partnerBean.getLogin());
		partnerModel.setStreetAddress(partnerBean.getStreetAddress());
		partnerModel.setCity(partnerBean.getCity());
		partnerModel.setState(partnerBean.getState());
		partnerModel.setZip_code(partnerBean.getZip_code());
		partnerModel.setPassword(partnerBean.getPassword());
		
		return partnerModel;
	}

	/**
	 * This method saves the order line data from the order 
	 * @param orderBean
	 * @return
	 */
	public static List<OrderLine> buildOrderLineList(OrderBean orderBean) {
		List<OrderLine> orderLineList = new ArrayList<OrderLine>();
		if (orderBean != null) {
			List<Integer> productIds = orderBean.getProductIds();
			int lineNumber = 0;
			for (Integer productId : productIds) {
				OrderLine orderLine = new OrderLine();
				orderLine.setOrderId(orderBean.getId());
				orderLine.setLineNumber(lineNumber);
				orderLine.setProductId(Integer.parseInt(productId.toString()));
				orderLine.setQuantity(1); //default quantity
				orderLineList.add(orderLine);
			}
        }
        return orderLineList;
	}

	public static CustomerBean buildCustomerBean(CustomerRepresentation customer) {
		CustomerBean customerBean = new CustomerBean();
		if (customer != null) {
			customerBean.setActive(true);
			customerBean.getAddress().setCity(customer.getAddress().getCity());
			customerBean.getAddress().setState(customer.getAddress().getState());
			customerBean.getAddress().setStreetAddress(customer.getAddress().getStreetAddress());
			customerBean.getAddress().setZipCode(customer.getAddress().getZipCode());
			customerBean.setEmail(customer.getEmail());
			customerBean.setFirstName(customer.getFirstName());
			customerBean.setId(customer.getId());
			customerBean.setLastName(customer.getLastName());
			customerBean.setLogin(customer.getLogin());
			customerBean.setPassword(customer.getPassword());
		}
		
		return customerBean;
	}

}
