export class AppConstant{
   public static User = {
        "email": null,
       "mobile": null,
       "name": null,
       "password": null,
       "address": {
         "at": null,
         "city": null,
         "state": null,
         "pinCode": null
       }
       
    }
  public static userSession =	{
    "token": null,
    "email": null,
    "date": null
  }
  public static genrateLoan={
    "loanAmount": null,
    "paymentFrequency": "HALFYEARLY",
    "paymentType": "EVEN_PRINCIPAL",
    "intrestRate": 10
  }
  public static paymentFrequency=["MONTHLY","QUARTERLY","HALFYEARLY","YEARLY"]
  public static paymentType=["EVEN_PRINCIPAL","INTREST_PRINCIPAL"]
  public static INTREST_RATE=[5,6,7,12,14]
  public static SCHDULER= {
    "amount":null,
    "id": null,
    "loan": null,
    "paymentDate": null,
    "principal": null,
    "projectedIntrest": null,
    "status":null
    
  }


}