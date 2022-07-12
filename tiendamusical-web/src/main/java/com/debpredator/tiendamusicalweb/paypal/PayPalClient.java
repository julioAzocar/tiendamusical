package com.debpredator.tiendamusicalweb.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;


//cliente id
//secret
//cliente id: AZPg1T11HbYLBEu3cpdD50OQj1Q-ksfYh6eShueWbyF10EePrzX-wkwQZCvS16uFHGvAZUQoZhhVCE0F
//secret: EFWIdm_z-_G7JTy6h4RG17AVw4GCGDVBkcDpRPvvD2LicLecLN-53feqijgsV3yU7xu_63MWGiKwTPRv

public class PayPalClient {
	private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			"AZPg1T11HbYLBEu3cpdD50OQj1Q-ksfYh6eShueWbyF10EePrzX-wkwQZCvS16uFHGvAZUQoZhhVCE0F", 
			"EFWIdm_z-_G7JTy6h4RG17AVw4GCGDVBkcDpRPvvD2LicLecLN-53feqijgsV3yU7xu_63MWGiKwTPRv");
	
//	  private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
//			    "ATEir5IbJm02uOzMfNPZw48iYRuJsoScedFxjThUfQnB1eh5Soal3FAWUQsfFettOs11DDURnRxXiQBo",
//			    "EN2296CBA-8sjbQHNkNp1INJDbJcpXPzpoHQUqXVKZm0f9ycr6qZrkKJnddHCIepugFXic7RPJHOMBzc");

	  
	PayPalHttpClient client = new PayPalHttpClient(environment);
	
	public PayPalHttpClient client() {
		return this.client;
	}
	
}
