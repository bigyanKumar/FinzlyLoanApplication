import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiServicesService } from '../services/api-services.service';
import { AppConstant } from '../app.constant';
import { scheduled } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(public router:Router,public apiservice:ApiServicesService,private toastr: ToastrService) { }
  public LOANSDATA:any
  public SCHDULEDATA:any;
  ngOnInit(): void {
    if(localStorage.getItem("token")){
      this.displaytable=true
    this.apiservice.getLoans(localStorage.getItem("token")).subscribe((data:any)=>{
        this.LOANSDATA=data;
        if(this.LOANSDATA)
        localStorage.setItem("user",JSON.stringify(this.LOANSDATA[0].customer))
    },err=>{
      console.log(err)
    })
  }else{
    this.router.navigateByUrl("login")
  }
  }
  displaytable=true;
  onLoan(){
    this.ngOnInit();
  }
  onSchedule(ele:any){
      this.displaytable=false
        this.apiservice.getLoanSechduler(localStorage.getItem('token'),ele).subscribe((data:any)=>{
              
              data.sort((a:any,b:any)=>{
                return b.principal-a.principal
              })
              this.SCHDULEDATA=data
              console.log(this.SCHDULEDATA)
        },err=>{
          console.log(err)
        })
  }
  pay(ele:any){
      this.apiservice.pay(localStorage.getItem("token"),ele.id).subscribe((data:any)=>{
         console.log(data)
         this.toastr.success("Payment made sucessfuly!")
         this.onSchedule(data.loan)
      },err=>{
        console.log(err)
      })
  }

}
