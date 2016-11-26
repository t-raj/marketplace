package main.java.service.representation;

/**
 * This class is a representation of a payment method, i.e. credit card
 * @author lbo
 *
 */
public class PaymentRepresentation extends AbstractRepresentation {
	
	private String cardNumber;
	private String expirationDate;
	private String securityCode;
	private String cardholderName;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
}
