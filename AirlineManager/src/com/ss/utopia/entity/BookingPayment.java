package com.ss.utopia.entity;

public class BookingPayment {
	private Booking bookingId;
	private String stripeId;
	private boolean refund; //0 or 1
	
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
		this.stripeId = stripeId;
	}
	public boolean getRefund() {
		return refund;
	}
	public void setRefund(boolean refund) {
		this.refund = refund;
	}
	
	
}
