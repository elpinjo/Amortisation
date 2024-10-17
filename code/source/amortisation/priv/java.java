package amortisation.priv;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void calculateAmortisation (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateAmortisation)>> ---
		// @sigtype java 3.5
		// [i] field:0:required principle
		// [i] field:0:required interest
		// [i] field:0:required periodsInMonths
		// [o] field:0:required monthlyPayments
		IDataCursor myInputCursor = pipeline.getCursor();
		
		String principle = IDataUtil.getString(myInputCursor, "principle");
		String interest = IDataUtil.getString(myInputCursor, "interest");
		String periods = IDataUtil.getString(myInputCursor, "periodsInMonths");
		
		myInputCursor.destroy();
		
		int loanAmount = Integer.parseInt(principle);
		int numberMonths = Integer.parseInt(periods);
		
		double monthlyrate= Double.parseDouble(interest)/1200;
		double monthlyPayment = loanAmount*monthlyrate/(1 -1/Math.pow(1+monthlyrate,numberMonths));
		
		String myPayments = String.format("%6.2f", monthlyPayment);
		
		IDataCursor myOutputCursor = pipeline.getCursor();
		
		IDataUtil.put(myOutputCursor, "monthlyPayments", myPayments);
		myOutputCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

