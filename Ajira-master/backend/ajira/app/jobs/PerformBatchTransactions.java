package jobs;

public class PerformBatchTransactions {
/* PAYPAL EXPRESS CHECKOUT
 String api = "https://api-3t.sandbox.paypal.com/nvp";
 Map params = new HashMap(); 
// Add login infos 
params.put("METHOD", method); 
params.put("VERSION", "62.0"); 
params.put("USER", username); 
params.put("PWD", password); 
params.put("SIGNATURE", signature); 
// 
params.put("EMAIL", account.email); 
params.put("RETURNURL", ok); 
params.put("CANCELURL", cancel); 
params.put("LOCALECODE", "US"); 
params.put("NOSHIPPING", "1"); // No shipping address 
params.put("SOLUTIONTYPE", "Sole"); // No paypal account needed 
params.put("LANDINGPAGE", "Billing"); // Non login page 
params.put("CHANNELTYPE", "Merchant"); 
params.put("PAYMENTREQUEST_0_AMT", amount); 
params.put("PAYMENTREQUEST_0_CURRENCYCODE", "EUR"); 
params.put("PAYMENTREQUEST_0_CUSTOM", "For account " + account.id); 
params.put("PAYMENTREQUEST_0_PAYMENTACTION", "Sale"); 
params.put("GIFTMESSAGEENABLE", "0"); 
params.put("GIFTRECEIPTENABLE", "0"); 
params.put("GIFTWRAPENABLE", "0"); 
params.put("L_PAYMENTREQUEST_0_NAME0", "Credit your prepaid account"); 
params.put("L_PAYMENTREQUEST_0_AMT0", amount); 
params.put("L_PAYMENTREQUEST_0_QTY0", "1"); 
// Call paypal 
String back = WS.url(api).params(params).post().getString(); 
// Parse result 
Map<String,String[]> r = UrlEncodedParser.parse(back); 
Map<String,String> result = new HashMap<String,String>(); 
for(String k : r.keySet()) { 
    result.put(k, r.get(k)[0]); 
} 

if(!("Success".equals(result.get("ACK")))) { 
    Logger.error("SetExpressCheckout has failed: %s", result); 
} 

String token = result.get("TOKEN"); 

*/

}
