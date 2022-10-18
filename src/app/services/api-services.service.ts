import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiServicesService {

  constructor(private http: HttpClient) { }
    
    getPing(){
      return this.http.get('http://localhost:8088/ping');
    }
    public login(data:any){
      return this.http.post('http://localhost:8088/login',data);
    }
    public signUp(data:any){
      return this.http.post('http://localhost:8088/customers/create',data);
    }
    public getLoans(data:any){
      return this.http.get(`http://localhost:8088/findloans?token=${data}`);
    }
    public getLoanSechduler(token:any,loanId:any){
      return this.http.get(`http://localhost:8088/findgenratedloan/${loanId}?token=${token}`);
    }
    public genrateLoan(data:any,token:any){
      return this.http.post(`http://localhost:8088/createloan?token=${token}`,data);
    }
    public pay(token:any,schId:any){
      return this.http.put(`http://localhost:8088/payment/${schId}?token=${token}`,"");
    }
}
