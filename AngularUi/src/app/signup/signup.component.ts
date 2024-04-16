import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validator } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiServicesService } from '../services/api-services.service';
import { AppConstant } from '../app.constant';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  form !:FormGroup;

  constructor(private fb:FormBuilder,public router:Router,public apiservice:ApiServicesService,private toastr: ToastrService) { }

  ngOnInit(): void {
    if(localStorage.getItem('token')){
      this.router.navigateByUrl("dashboard")
    }
    this.form = this.fb.group({
      email:[null],
      mobile:[null],
      name:[null],
      password:[null],
      at:[null],
      city:[null],
      state:[null],
      pinCode:[null]

  })
  }
  public user=AppConstant.User

  signUpUser(){

    if(this.form.value.email!=null && this.form.value.password!=null && this.form.value.name!=null
      && this.form.value.mobile!=null && this.form.value.at!=null && this.form.value.state!=null
      && this.form.value.pinCode!=null){
        this.user.email=this.form.value.email
        this.user.name=this.form.value.name
        this.user.mobile=this.form.value.mobile
        this.user.password=this.form.value.password
        this.user.address.at=this.form.value.at
        this.user.address.city=this.form.value.city
        this.user.address.state=this.form.value.state
        this.user.address.pinCode=this.form.value.pinCode
        this.apiservice.signUp(this.user).subscribe((res)=>{
              localStorage.setItem("user",JSON.stringify(this.user))
              this.router.navigateByUrl("login")
              this.toastr.success("Account Created Now You Can Login!")
        },err=>{
          console.log(err)
          this.toastr.error("Server Error",err)
        })
    }
  }

}
