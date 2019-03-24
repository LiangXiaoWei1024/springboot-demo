package com.imooc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.imooc.pojo.Resource;
import com.imooc.utils.WeatherReportByCity;

@RestController
@RequestMapping("/hello")
public class HelloController {
 
	@Autowired
	private Resource res;
	
	 
		
	
	@RequestMapping("/getHello")
	public Object hello(HttpServletResponse httpResponse) throws IOException{
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016092500590302","MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCWeVbk4p6/g42rmFytrmSwEkqVESHt6stqFQsO1efhlEJNgty77D97qou3TxdCt//WhKdyNJ6rF/W7/0YDIys53lAv+TaUHzhmJiIbo4K/QVEwYQfs2rlr22A1mZ1z+AOHCDP6UyYR7PQTdzl0+977LDqinYCFXMOlK33NUjBKjwyC7eShM2YpBCHgvoMul0hFh5el5uhYRlE5v3YLA7UY4qymZUCo2RFhQDjTULzB3rY+280FUisPc1fE3hO/whmiCLk+EpF4XuBumufjxsZirRUVAsM0gAtYDiCdNB5Is7QWEAvc/tUz6WNqp33XNoehMGx9Ib2xZNdhawwI7WF5AgMBAAECggEBAIhFVQstrGR2J9upvOzRTYAi4IZFILwM8bCdZAlIcJnaoXz+sRZC6m79UVBuAPu8FKZaNiZ4PuVlanaZxPbcZ64dhfMcN3BHIdVoXWU4XBUGexdcGEhJALkL0AJanrxCdDdal2/w0z5Sn+vtAgzOeO5w7J0OFDEyhD/VtjU2ZGNv6eBsCQhU9+Ts0+bnYp3Lv6P4iLde9Z7zrYzekiMtlPCxIHwzOa6HjVhnwOjbrssm6DcH2/ps/ruNlTpykTXyV32DxtA2fNJHIe7TBThMhinSYKl3cj0GjMtfnESF11VPai1TweoAMmCsq6VfN6MpWorWiXPFs4JKq8PjaHG5zf0CgYEA1/FpIKAFFaUT0W48sjMc5J658bogTccBJcnBaFex3zkRrc3u5OOTpddtNvDFrG3p7t1CmLVsLvtt1p3suJiHl/fp9CFU5LeumQWnSSZ53nKv6zhwZR00jUtQJLAPLIDMOO8OtWLY5YpoQjufiF0WMaYAPnV0n4E1ONdiXZCXgSsCgYEAsmL4OQKx5DVUYlKm54OsidNgXfz2IvoGzRpNrFC7NIDKDxRZiZDx+9PsIZBLUrBq93L9TJy0sPw3BKBZy8fOjQAk2a/WPBb32nJuufa5iy2PK/Vj4CGeGha/TjYFkM7gKd+L9DmaaD3aMJG0B7zvdn+eqyqw7f/0I2m59A1P7esCgYAIUC/m5KIteiRwLJlDlvomxwGLj8nMlLuzk7fRZ4befBj1IvducmBtwwReWBiUEcsuue/lZx6aFG3fFEdXt++IZy/niRz2jSe4lmIhS8znRm0FVi/lhKFQSzTzpJ+A7BhwnKtpSjvlYlWvLxJuMn83PBfIT8dEmLnOzWPpDDb47wKBgQCNsqPt1UAtQFdpSrIdkLCLQoZ34io6GlUwssYkLHSn/e2DEO0WozY0jWAyQQY0BsLREFw4cPnu5ElnuV/ayTo/MnATIuOQE8aJlQBzY5J7BZOGnYPaU5ncq1kG7U56enoBiPsI4oDOcONSVUqmIuQsQq9s05p7UydAwgjd3X0yRQKBgFydNCv4JF3W8TXnNP/4e+/ZnrkKWahO2eiYZ7KcCagy1t6EjactaQNWBUdOX5rCThYu85KBBJkdE1BhE05fQak73DTMhMIUXGiwZez6ubXxOjFfFRHJuGeUA3fsd4BE7qbcQPQbTDk4OhCb2PeM5eaFyvwVacsz75heCvhNa5v0","json","utf-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlnlW5OKev4ONq5hcra5ksBJKlREh7erLahULDtXn4ZRCTYLcu+w/e6qLt08XQrf/1oSncjSeqxf1u/9GAyMrOd5QL/k2lB84ZiYiG6OCv0FRMGEH7Nq5a9tgNZmdc/gDhwgz+lMmEez0E3c5dPve+yw6op2AhVzDpSt9zVIwSo8Mgu3koTNmKQQh4L6DLpdIRYeXpeboWEZROb92CwO1GOKspmVAqNkRYUA401C8wd62PtvNBVIrD3NXxN4Tv8IZogi5PhKReF7gbprn48bGYq0VFQLDNIALWA4gnTQeSLO0FhAL3P7VM+ljaqd91zaHoTBsfSG9sWTXYWsMCO1heQIDAQAB","RSA2");
		    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
		    //alipayRequest.setReturnUrl("http://lxw666.top:8989/sys/index");
		   // alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
		    alipayRequest.setBizContent("{" +
		        "    \"out_trade_no\":\"20150320010101001\"," +
		        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		       // "    \"timeout_express\":15d," +
		        "    \"total_amount\":1," +
		        "    \"subject\":\"Iphone6 16G\"," +
		        "    \"body\":\"Iphone6 16G\"," +
		        "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
		        "    \"extend_params\":{" +
		        "    \"sys_service_provider_id\":\"2088102177152134\"" +
		        "    }"+
		        "  }");//填充业务参数
		    String form="";
		    try {
		        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		    }
		   /* httpResponse.setContentType("text/html;charset=utf-8");
		    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
		    httpResponse.getWriter().flush();
		    httpResponse.getWriter().close();*/
			return form;
	}
	
	@RequestMapping("/getRes")
	public Resource getRes(){
		Resource resource = new Resource();
		BeanUtils.copyProperties(res, resource);
		return resource;
	}
	
	
	
    
}
