package common;

import javax.servlet.http.HttpServletRequest;

public class NotifierHandler {

	public static void setNotifierMessage(HttpServletRequest request, String message) {
		if(request.getAttribute("notifier")==null) {
			String notifier = "<div class='notifier'><i class='gg-danger'></i>" + message + "</div>";
			request.setAttribute("notifier", notifier);
		}
		else {
			String notifier = (String)request.getAttribute("notifier");
			if(notifier.indexOf("</a></div>")!=-1) {
				notifier = notifier.substring(0, notifier.indexOf("</div>")-1) + message + "</div>";
				request.setAttribute("notifier", notifier);
			}
			else {
				request.setAttribute("notifier", null);
				setNotifierMessage(request, notifier + " | " + message );
			}
		}
	}
	
}
