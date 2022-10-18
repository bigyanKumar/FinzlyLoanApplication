import { Component, OnInit } from '@angular/core';
import { FormControl, FormBuilder, FormGroup,Validator, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServicesService } from '../services/api-services.service';
import { AppConstant } from '../app.constant';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-loan',
  templateUrl: './loan.component.html',
  styleUrls: ['./loan.component.scss']
})
export class LoanComponent implements OnInit {

  constructor(private fb:FormBuilder,public router:Router,public apiservice:ApiServicesService,private toastr: ToastrService) { }

  PaymentFrequency=AppConstant.paymentFrequency;
  PaymentType=AppConstant.paymentType;
  IntrestRate=AppConstant.INTREST_RATE;
  form !:FormGroup;
  ngOnInit(): void {
    if(localStorage.getItem("token")){
    this.form = this.fb.group({
      loanAmount:[null,[Validators.required]],
      paymentFrequency:[null,[Validators.required]],
      paymentType:[null,[Validators.required]],
      intrestRate:[null,[Validators.required]]
  })
  }else{
    this.router.navigateByUrl("login")
    this.toastr.error("First You Have to create Accout or Login");
  }
  
  }
  createLoan(){
    console.log(this.form.value)
    this.apiservice.genrateLoan(this.form.value,localStorage.getItem("token")).subscribe((data:any)=>{
      console.log(data)
      this.router.navigateByUrl("dashboard")
      this.toastr.success("Loan Granted!")
    })
  }

}
