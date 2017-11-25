package com.modular.framework.POM;

import org.apache.log4j.Logger;

import com.modular.framework.Functional_TestSuites.DemoTest;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class InternalPanel {
	static Logger log = LoggerHelper.writeLog(InternalPanel.class);
	public static void login()throws Throwable{
		
		log.info("Logging to internal panel");
		InitDriver.driver.get("http://internal.dev.msupply.com");
		Thread.sleep(3000);
		
		WebDriverCommonFunctions.element_EnterValuesToTextField("InternalPanel_LoginTxtBox", "anshuman@msupply.com", "InternalPanel_LoginTxtBox");
		WebDriverCommonFunctions.element_EnterValuesToTextField("InternalPanel_PasswordTxtBox", "123456", "InternalPanel_PasswordTxtBox");
		WebDriverCommonFunctions.element_Click("InternalPanel_LoginButton", "InternalPanel_LoginButton");
		WebDriverCommonFunctions.element_EnterValuesToTextField("InternalPanel_OTPtextBox", "1234", "InternalPanel_OTPtextBox");
		WebDriverCommonFunctions.element_Click("InternalPanel_OTPverifyButton", "InternalPanel_OTPverifyButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_sidePanel_PaymentsLink", "InternalPanel_sidePanel_PaymentsLink");
		WebDriverCommonFunctions.element_Click("InternalPanel_receivedPaymentButton", "InternalPanel_receivedPaymentButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_receivedConfirmButton", "InternalPanel_receivedConfirmButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_receivedOKButton", "InternalPanel_receivedOKButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_disbursePaymentButton", "InternalPanel_disbursePaymentButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_disburseConfirmButton", "InternalPanel_disburseConfirmButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_disburseOKButton", "InternalPanel_disburseOKButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_remittedPaymentButton", "InternalPanel_remittedPaymentButton");
		WebDriverCommonFunctions.element_EnterValuesToTextField("InternalPanel_NEFTtransactionTextBox", "dghbfvh1234", "InternalPanel_NEFTtransactionTextBox");
		WebDriverCommonFunctions.element_Click("InternalPanel_NEFT_UTRdatePicker", "InternalPanel_NEFT_UTRdatePicker");
		WebDriverCommonFunctions.element_Click("InternalPanel_NEFT_UTRdate", "InternalPanel_NEFT_UTRdate");
		WebDriverCommonFunctions.element_Click("InternalPanel_remittedConfirmButton", "InternalPanel_remittedConfirmButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_remittedOKButton", "InternalPanel_remittedOKButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_PaymentAcknowledgeButton", "InternalPanel_PaymentAcknowledgeButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_PaymentPOA_browseButton", "InternalPanel_PaymentPOA_browseButton");
		
		WebDriverCommonFunctions.element_Click("InternalPanel_PaymentPOA_sumitButton", "InternalPanel_PaymentPOA_sumitButton");
		WebDriverCommonFunctions.element_Click("InternalPanel_AcknowledgeOKbutton", "InternalPanel_AcknowledgeOKbutton");
	}
	
	
}
