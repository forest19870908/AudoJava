package createPreOut;


public class CreatePreOut {

	public static void main(String[] args){
		login();
//		createSale();
	}
	//登录
	public static void login(){
		String urlString = "http://localHost:8080/admin/login.jsp";
		try {
			String result = HttpConnectionUtil.sendPostRequestByForm(urlString, "username=何志明&password=12345678a");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//创建销售合同   (代销的数据)
	public static void createSale(){
		String urlString = "http://localhost:8080/admin/jxc/sale/save";
		String param="isDecision=1&type=2&productType=1&isFinance=0&isBidding=0&saleContract.isDisplayQrcode=1&sellerCompanyId=1&signTime=2016-09-29&buyerCompanyName=AMCOR上海办事处&fax=123456&buyerContactId=122918&saleContract.period=&giveMethod=1&giveTime=2016&province=广东&provinceId=440000&city=广州&cityId=440100&areaId=&giveAddress=123456&saleContract.isDisplayAddress=1&payMethod=1&payMethodName=&payLimitTime=2016年09月29日&payLimitTimeHidden=2016年09月29日&sellerAccountId=1&trackerId=77&followId=110&saleContract.remark=&detail[0].id=&detail[0].cate=ABS&detail[0].cateId=482&detail[0].manufacturer=比利时BASF&detail[0].manufacturerId=3736&detail[0].material=GP-22&detail[0].materialId=10915&detail[0].brand=77&detail[0].number=5.000000&detail[0].price=6800.00&detail[0].logisticsPrice=0.00&detail[0].financePrice=0.00&detail[0].financeGuidePrice=0.00&detail[0].realPrice=6800.00&detail[0].total=34000.00&detail[0].deliveryLimitTime=&detail[0].pack=25&detail[0].payMethod=0&detail[0].payMethodValue=现金&detail[0].remark=&buyerMemberId=&id=&assocId=&buyerCompanyId=53&token=3a0c2280-862c-4045-8a99-f097cfe71a93&JSESSIONID=8AB5FA99181CB7EF9DBBA94B560F652D";
		try {
			String result = HttpConnectionUtil.sendPostRequestByForm(urlString, param);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
