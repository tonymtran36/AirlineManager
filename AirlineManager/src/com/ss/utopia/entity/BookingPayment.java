package com.ss.utopia.entity;

public class BookingPayment {
	private Booking bookingId;
	private String stripeId;
	private boolean refund; //0 or 1
	
	private static final Integer STRIPE_MAX_LENGTH = 255;
	
	public Booking getBookingId() {
		return bookingId;
	}
	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}
	public String getStripeId() {
		return stripeId;
	}
	public void setStripeId(String stripeId) {
		StringBuilder temp = new StringBuilder(stripeId);
		temp.setLength(STRIPE_MAX_LENGTH);
		this.stripeId = temp.toString();
		//this.stripeId = stripeId;
	}
	public boolean getRefund() {
		return refund;
	}
	public void setRefund(boolean refund) {
		this.refund = refund;
	}
	
	
}
